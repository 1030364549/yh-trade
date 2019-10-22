package com.yh.cache;

import com.yh.config.RedisConfig;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RedisCached implements CacheHelp {


    private RedisTemplate<String, Object> t;

    private RedisCached() {
        t = RedisConfig.r;
    }

    private static RedisCached redisCached = new RedisCached();

    public static RedisCached getInstance() {
        return redisCached;
    }

    @Override
    public boolean add(String key, Object value) {
        try {
            t.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean add(String key, Object value, Date expiry) {
        try {
            t.opsForValue().set(key, value, expiry.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        t.delete(key);
        return true;
    }

    @Override
    public boolean flushAll() {
        try {
            t.execute((RedisCallback) connection -> {
                connection.flushDb();
                return "ok";
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean replace(String key, Object value) {
        return add(key, value);
    }

    @Override
    public boolean replace(String key, Object value, Date expiry) {
        return add(key, value, expiry);
    }

    @Override
    public Object get(String key) {
        return key == null ? null : t.opsForValue().get(key);
    }
}
