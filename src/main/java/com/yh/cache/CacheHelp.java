package com.yh.cache;

import java.util.Date;

public interface CacheHelp {

    boolean add(String key, Object value);
    boolean add(String key, Object value, Date expiry);
    boolean delete(String key);
    boolean flushAll();
    /**
     * ************************************
     * 更新一个指定的值到缓存中
     * @param key
     * @param value
     * ************************************
     */
    boolean replace(String key, Object value);
    /**
     * ************************************
     * 更新一个指定的值到缓存中(带有效期)
     * @param key
     * @param value
     * @param expiry
     * ************************************
     */
    boolean replace(String key, Object value, Date expiry);
    /**
     * ************************************
     * 根据指定的关键字获取对象
     * @param key
     * ************************************
     */
    Object get(String key);
}
