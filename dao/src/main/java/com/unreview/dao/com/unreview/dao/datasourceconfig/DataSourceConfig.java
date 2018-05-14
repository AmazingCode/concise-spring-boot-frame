package com.unreview.dao.com.unreview.dao.datasourceconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类（main中禁用了 默认的数据源，所以这里需要自己配置）
 */
public class DataSourceConfig {

    /**
     *  数据源1 设置为主库
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSourceMaster()
    {
        return DataSourceBuilder.create().build();
    }

    /**
     * 数据源2
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource dataSourceSecondary()
    {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     * @return
     */
    @Bean
    public DataSource dynamicDataSource()
    {
        DynamicDataSource source=new DynamicDataSource();

        //设置默认数据源
        source.setDefaultTargetDataSource(dataSourceMaster());

        Map<Object,Object> ds=new HashMap<>();

        ds.put("master",dataSourceMaster());

        ds.put("secondary",dataSourceSecondary());

        source.setTargetDataSources(ds);

        return source;
    }

    /**
     *  定义sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory factory() throws Exception {
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();

        factoryBean.setDataSource(dynamicDataSource());

        return factoryBean.getObject();
    }

}
