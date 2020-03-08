package com.mtcarpenter.service.impl;

import com.mtcarpenter.entity.User;
import com.mtcarpenter.repository.UserJpaRepository;
import com.mtcarpenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 山间木匠
 * @Date 2020/3/3
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserJpaRepository userJpaRepository;


    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<User> list() {
        return userJpaRepository.findAll();
    }

    /**
     * 根据 id 查找
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        logger.info("id = {} 数据库获取数据 ", id);
        return userJpaRepository.findById(id).orElse(null);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        logger.info("user = {} 保存数据到数据库 ", user);
        return userJpaRepository.save(user);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        logger.info("user = {} 更新数据到数据库 ", user);
        return  userJpaRepository.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        logger.info("id = {} 数据库数据删除 ", id);
        userJpaRepository.deleteById(id);
    }
}
