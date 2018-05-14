package com.unreview.model.bo.statics;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *  数据源名称配置 在配置文件中添加数据源配置后，在这里添加名称即可完成多数据源配置
 */
@Data
public class DataSourceName {

    private static final String masterSource="master";

    private static final List<String> sources=new ArrayList<String>(){{

        add(masterSource);

        add("secondary");

    }};
}
