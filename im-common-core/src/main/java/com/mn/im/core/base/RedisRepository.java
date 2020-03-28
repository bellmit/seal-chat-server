package com.mn.im.core.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by qiaohao on 17/10/23.
 */
@Slf4j
@Repository
public class RedisRepository {

	@Autowired StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate") ValueOperations<String, String> valOpsStr;

	@Autowired RedisTemplate<Object, Object> redisTemplate;

	@Resource(name = "redisTemplate") ValueOperations<Object, Object> valOps;

	
	public void save(Object key, Object value, int time) {
		try {
			valOps.set(key, value, time, TimeUnit.SECONDS);
		} catch (Throwable t) {
			log.error("缓存key[{}]", key, t);
		}
	}

	
	public BoundValueOperations<Object, Object> getOperations(String key) {
		return redisTemplate.boundValueOps(key);
	}

	
	public boolean setIfAbsent(String key, String value) {
		BoundValueOperations<Object, Object> boundValueOperations = getOperations(key);
		boolean result = boundValueOperations.setIfAbsent(value);
		if (result) {
			// 避免死锁,如果5分钟还没有解锁,5分钟后自动解锁
			boundValueOperations.expire(60 * 5, TimeUnit.SECONDS);
		}
		return result;
	}

	
	public void save(Object key, Object value) {
		try {
			valOps.set(key, value);
		} catch (Throwable t) {
			log.error("缓存key[{}]", key, t);
		}
	}

	
	public Object get(Object key) {
		try {
			return valOps.get(key);
		} catch (Throwable t) {
			log.error("获取缓存失败key[{}]", key, t);
		}
		return null;
	}

	
	public boolean containsKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Throwable t) {
			log.error("判断缓存是否存在失败key[{}]", key, t);
		}
		return false;
	}

	
	public void delete(Object key) {
		try {
			redisTemplate.delete(key);
		} catch (Throwable t) {
			log.error("删除缓存失败key[{}]", key, t);
		}
	}

	
	public List<String> getAllKeys(String pattern) {
		List<String> keyList = new ArrayList<>();
		Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys((pattern + "*").getBytes());
		Iterator<byte[]> it = keys.iterator();
		while (it.hasNext()) {
			byte[] data = (byte[]) it.next();
			keyList.add(new String(data, 0, data.length));
		}
		return keyList;
	}

	
	public void deleteByPattern(String pattern) {
		List<String> keyList = new ArrayList<>();
		Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys((pattern + "*").getBytes());
		Iterator<byte[]> it = keys.iterator();
		while (it.hasNext()) {
			byte[] data = (byte[]) it.next();
			keyList.add(new String(data, 0, data.length));
		}
		keyList.forEach(key -> {
			redisTemplate.delete(key);
		});

	}

	
	public List<Object> getObjectList(String keyName) {
		List<String> keys = this.getAllKeys("*" + keyName);
		List<Object> list = new ArrayList();
		for (String key : keys) {
			String[] keyArr = key.split(keyName);
			if (keyArr != null && keyArr.length >= 2) {
				String keyNameStr = keyName + keyArr[1];
				Object keyVal = this.get(keyNameStr);
				list.add(keyVal);
			}
		}
		return list;
	}

	/**
	 * @Title:
	 * @Description:   设置过期时间
	 * @param key time
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2019/02/27 03:52:42
	 */
	public void expire(Object key, int time){
		redisTemplate.expire(key,time, TimeUnit.SECONDS);
	}

}
