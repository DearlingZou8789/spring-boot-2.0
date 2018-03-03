package com.xm.spring.demo.repository;

import com.xm.spring.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {

    /**
     * 采用内存性的存储方式->Map
     */
    private final ConcurrentMap<Integer, User> repository
            = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user {@link User}对象
     * @return 如果保存成功,返回<code>true</code>
     *          否则，返回<code>false</code>
     */
    public Boolean save(User user){
        Boolean result = false;

        /**
         * ID从1开始
         */
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    /**
     * 获取所有的用户对象
     * @return 用户对象集合
     */
    public Collection<User> findAll(){
        return repository.values();
    }
}
