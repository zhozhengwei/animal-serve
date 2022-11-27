package com.zzw.animalserve.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description(redis工具类，为了更加方便的使用redis的方法)
 * @autor: zhouzhengwei
 * @date: 2022/8/27__13:32
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {
    @Autowired(required = false)
    public RedisTemplate redisTemplate;

    /**
    * @Description: 缓存基本的对象，Integer、String、实体类
    * @Param: [key, value] 缓存的键值、缓存的值
    * @return: void
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key,final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
    * @Description: 设置有效时间
    * @Param: [key, value, timeout, timeUnit]
    * @return: void
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key,final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    /**
    * @Description: 设置有效时间
    * @Param: [key, timeout]
    * @return: boolean
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param key redis键
     * @param timeout  超时时间
     * @return true = 设置成功； false = 设置失败
     */
    public boolean expire(final String key, final long timeout){
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
    * @Description: 设置有效时间
    * @Param: [key, timeout, unit]
    * @return: boolean
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param key redis键
     * @param timeout  超时时间
     * @param unit 时间单位
     * @return true = 设置成功； false = 设置失败
     */
    public boolean expire(final String key, final long timeout, TimeUnit unit){
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
    * @Description:
    * @Param: [key]
    * @return: T
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
    * @Description: 删除单个对象
    * @Param: [key]
    * @return: boolean
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    public boolean deleteObject(final String key){
        return redisTemplate.delete(key);
    }



    /**
    * @Description: 删除集合对象
    * @Param: [collection]
    * @return: long
    * @Author: zhouzhengwei
    * @Date: 2022/8/27
    */
    /**
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection){
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList){
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     *获得缓存键值对应的数据
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key){
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     * @param key 缓存的键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while(it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     * @param key 缓存的键值
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap){
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     *获得缓存的Map
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往hash中存入数据
     * @param key  缓存的键值
     * @param hKey hash键值
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key,final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     * @param key 缓存的键值
     * @param hKey hash键值
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     * @param key
     * @param hKey
     */
    public void delCacheMapValue(String key, String hKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hKey);
    }

    /**
     * 获取对各Hash中的数据
     * @param key
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys){
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     * @param pattern 字符串
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
