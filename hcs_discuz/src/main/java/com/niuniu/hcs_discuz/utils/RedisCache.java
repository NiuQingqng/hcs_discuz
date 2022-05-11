package com.niuniu.hcs_discuz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author niuniu
 * @date 2022/2/28
 */
@Component
public class RedisCache {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String
     */
    public void set(String key, Object value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    public void set(String key, Object value, int expiredTime) {
        redisTemplate.boundValueOps(key).set(value, expiredTime, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        Object value = redisTemplate.boundValueOps(key).get();
        return value;
    }

    //设置失效时间
    public void expire(String key, int expiredTime) {
        redisTemplate.boundValueOps(key).expire(expiredTime, TimeUnit.SECONDS);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}
