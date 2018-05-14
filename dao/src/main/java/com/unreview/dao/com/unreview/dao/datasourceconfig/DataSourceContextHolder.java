package com.unreview.dao.com.unreview.dao.datasourceconfig;

/**
 * 定义一个类，保存当前线程使用的数据源名称
 */
public class DataSourceContextHolder {
    public static final String default_DS="master";

    private static final ThreadLocal<String> contextHolder=new ThreadLocal<>();

    //设置数据源名称
    public static void setDS(String dsName)
    {
        contextHolder.set(dsName);
    }

    //获取当前线程的数据源名称
    public static String getDS()
    {
        return contextHolder.get();
    }

    //清除当前线程的数据源名
    public static void clearDS()
    {
        contextHolder.remove();
    }

































}
