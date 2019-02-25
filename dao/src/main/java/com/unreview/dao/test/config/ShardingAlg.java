package com.unreview.dao.test.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ShardingAlg implements SingleKeyTableShardingAlgorithm<Integer> {
    private static final int TABLE_COUNT = 2;

    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {

        int tableIndex = shardingValue.getValue() % TABLE_COUNT;
        String logicTableName = shardingValue.getLogicTableName();

        return getRealTableName(collection, String.format("%s%s", logicTableName, tableIndex));

    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {

        String logicTableName = shardingValue.getLogicTableName();
        return shardingValue.getValues().stream().map(it ->
                getRealTableName(collection, String.format("%s%s", logicTableName, it % TABLE_COUNT)))
                .collect(Collectors.toSet());

    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Set<String> physicalTables = new HashSet();
        String logicTable = shardingValue.getLogicTableName();
        int lowerEndpoint = shardingValue.getValueRange().lowerEndpoint();
        int upperEndpoint = shardingValue.getValueRange().upperEndpoint();

        for (Integer usableSuffix = lowerEndpoint; usableSuffix <= upperEndpoint; usableSuffix = usableSuffix + 1) {
            int needSuffix = usableSuffix % TABLE_COUNT;
            physicalTables.add(this.getRealTableName(collection, String.format("%s%s", logicTable, needSuffix)));
        }

        return physicalTables;
    }

    private String getRealTableName(Collection<String> usableTables, String queryTableName) {

        Optional<String> result = usableTables.stream()
                .filter(it ->
                        it.equals(queryTableName)
                ).findFirst();
        if (result.isPresent())
            return result.get();
        throw new RuntimeException("sharding fail");
    }
}
