package com.unreview.dao.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库名为test的db配置
 */
@Configuration
@MapperScan(basePackages = testConfig.basePackage, sqlSessionFactoryRef = "")
public class testConfig {
    public static final String basePackage = "com.unreview.dao.test";
    public static final String mapperLocation = "classpath*:mapper/test/*.xml";
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "testTransactionManager")
    public DataSourceTransactionManager payTransactionManager() {
        return new DataSourceTransactionManager(testDataSource());
    }

    @Bean(name = "testSqlSessionFactory")
    public SqlSessionFactory paySqlSessionFactory(@Qualifier("testDataSource") DataSource payDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(payDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        return sessionFactory.getObject();
    }

    @Bean(name = "testDataSource")
    public DataSource testDataSource() {
        try {

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(driverClass);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setMaxActive(20);//设置线程池最大连接数
            dataSource.setInitialSize(5);//设置线程池初始化连接数
            dataSource.setMinIdle(15);//服务启动后会创建initialSize个连接，等业务压力上来后，线程池里的initialSize数不够使用时；剩余的连接会被依次创建；
            dataSource.setValidationQuery("select 1");//检测连接是否有效
            dataSource.setTestWhileIdle(true);//检测连接是否被空闲连接回收，如果检测失败，将从连接池中删除这个连接
            dataSource.setTestOnBorrow(false);//在从连接池中取出连接时，检查连接是否有效，如果无效，将删除这个连接;这个很影响性能，所以设置成false
            dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30);//每隔30分钟运行一次空闲连接回收器
            dataSource.setMinEvictableIdleTimeMillis(1);//连接池中的连接空闲1ms就会被空闲连接器回收
            Map<String, DataSource> dataSourceMap = new HashMap<>();
            {
                dataSourceMap.put("testSource", dataSource);
            }
            DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "testSource");

            List<String> studentTables = new ArrayList<>();
            for (int i = 0; i <= 1; i++) {
                studentTables.add(String.format("student%s", i));

            }

            TableRule payUserCouponTableRule = TableRule.builder("student")
                    .actualTables(studentTables)
                    .dataSourceRule(dataSourceRule)
                    .tableShardingStrategy(new TableShardingStrategy("uid", new ShardingAlg()))
                    .build();

            ShardingRule rule = ShardingRule.builder()
                    .dataSourceRule(dataSourceRule)
                    .tableRules(Collections.singletonList(payUserCouponTableRule))
                    .build();
            return ShardingDataSourceFactory.createDataSource(rule);
        } catch (SQLException e) {
            return null;
        }
    }
}
