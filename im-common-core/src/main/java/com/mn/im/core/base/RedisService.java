package com.mn.im.core.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiaomengnan
 * @ClassName: RedisServiceImpl
 * @Description:
 * @date 2018/2/25
 */
@Service
public class RedisService {

    @Autowired
    private RedisRepository redisRepository;

    /**
     * @Title:
     * @Description:   保存值进入redis
     * @param key
     * @param value
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/02/25 02:39:03
     */
    public void save(Object key, Object value){
        redisRepository.save(key,value);
    }

    /**
     * @Title:
     * @Description:   值存入redis同时限制时间
     * @param key
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/02/25 02:52:24
     */
    public void save(Object key, Object value,int time){
        redisRepository.save(key, value, time);
    }

    /**
     * @Title:
     * @Description:   通过key获取redis值
     * @param key
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/02/25 02:54:05
     */
    public Object get(Object key){
        return redisRepository.get(key);
    }

    /**
     * @Title:
     * @Description: 删除某个值
     * @param key
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/01/14 03:41:13
     */
    public void delete(Object key){
        redisRepository.delete(key);
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
        redisRepository.expire(key,time);
    }

}
