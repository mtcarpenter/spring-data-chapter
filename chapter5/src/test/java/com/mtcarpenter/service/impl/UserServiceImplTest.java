package com.mtcarpenter.service.impl;

import com.mtcarpenter.Chapter5ApplicationTests;
import com.mtcarpenter.entity.User;
import com.mtcarpenter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 山间木匠
 * @Date 2020/3/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    /*
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        logger.info("查询 id = {}", userService.findById(2L));
    }

    @Test
    public void save() {
        logger.info("新增数据 result = {}", userService.save(new User("小米", 9,"a@qq.com")));
    }

    @Test
    public void update() {
        logger.info("修改数据 result = {}", userService.save(new User(1L,"小米", 19,"a@qq.com")));
    }

    @Test
    public void deleteById() {
        userService.deleteById(1L);
    }
}