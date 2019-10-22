package com.yh.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheHelp {

    @Cacheable(value = "cache", key = "#key")
    public <T> T cached(String key) {
        return null;
    }

    @CachePut(value = "cache", key = "#key")
    public <T> T cache(String key, T value) {
        return value;
    }

    @CachePut(value = "cache", key = "#key")
    public <T> T remove(String key) {
        return null;
    }
}
