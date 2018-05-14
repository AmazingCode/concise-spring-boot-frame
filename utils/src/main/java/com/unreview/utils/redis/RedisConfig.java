package com.unreview.utils.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@ComponentScan
@Data
public class RedisConfig {
    //静态字段获取配置文件的值
    @Value("${redis.Host}")
    private  String redisHost;

    @Value("${redis.Port}")
    private  Integer redisPort;

    @Value("${redis.MaxTotal}")
    private  Integer redisMaxTotal;

    @Value("${redis.MaxWaitMillis}")
    private Integer redisMaxWaitMillis;

    @Value("${redis.MaxIdle}")
    private Integer redisMaxIdle;

    private  JedisPoolConfig config;
    private  JedisPool pool;

    public   String getRedisHost() {
        return redisHost;
    }

    public   Integer getRedisPort() {
        return redisPort;
    }

    public   Integer getRedisMaxTotal() {
        return redisMaxTotal;
    }

    public   Integer getConfig() {
        return redisPort;
    }

    public JedisPool getPool() {
        if(pool==null)
        {
            config=new JedisPoolConfig();
            config.setMaxTotal(redisMaxTotal);
            config.setMaxWaitMillis(redisMaxWaitMillis);
            config.setMaxIdle(redisMaxIdle);
            pool=new JedisPool(config,redisHost,redisPort);
        }
        return pool;
    }
}
