package com.unreview.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.unreview.model.bo.enums.TimeUnit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisHelper T:value类型
 */
public class RedisHelper<T> {

    @Autowired
    private static   RedisConfig redisConfig;


    @Autowired
    private SpringContextAware contextAware;

    private String key;
    private TimeUnit timeUnit;
    private Long timeValue;

    static {
       redisConfig= SpringContextAware.getBean(RedisConfig.class);
    }
    /**
     * 隐藏入口
     */
    private RedisHelper() {
    }

    /**
     * builder入口
     * @param t
     * @param <T>
     * @return
     */
    public static <T> RedisHelper<T> builder(TypeReference<T> t) {

        //RedisConfig redisConfig= SpringContextAware.getBean(RedisConfig.class);
        RedisHelper helper = new RedisHelper<T>();//这里是new出来的  应该没法使用bean


        return helper;
    }


    /**
     * 查询key
     * @param key
     * @return
     */
    public RedisHelper key(String key) {
        this.key = key;
        return this;
    }

    /**
     * key过期时间类型
     * @param timeUnit
     * @return
     */
    public RedisHelper timeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * key过期时间值
     * @param timeValue
     * @return
     */
    public RedisHelper timeValue(Long timeValue) {

        this.timeValue = timeValue;
        return this;
    }

    /**
     * 执行逻辑
     * @param r 执行逻辑
     * @return T redis value的类型
     * @throws Exception
     */
    public T CallAsync(run<T> r) throws Exception {
        //查询redis
        System.out.println(redisConfig.getRedisHost());

        Jedis jedis=redisConfig.getPool().getResource();
        String redisStr = jedis.get(key);
        if (redisStr == null) {

            T data=  r.run();
            //存储
            jedis.set(key, JSON.toJSONString(data), "NX", "PX", TimeHelper.TimeConverToMillSeconds(timeUnit, timeValue));
            if(jedis!=null)
                redisConfig.getPool().returnResource(jedis);
            return data;
        }
        return JSON.parseObject(redisStr, new com.alibaba.fastjson.TypeReference<T>() {});

    }

    /**
     * 定义内部泛型接口
     * @param <M>
     */
    public interface run<M> {
       M  run();
    }

}

