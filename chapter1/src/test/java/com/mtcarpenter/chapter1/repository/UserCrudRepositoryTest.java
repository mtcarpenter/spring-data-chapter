package com.mtcarpenter.chapter1.repository;


import com.mtcarpenter.chapter1.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 山间木匠
 * @Date 2020/2/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCrudRepositoryTest {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserCrudRepositoryTest.class);

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Test
    public void save() {
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("小米", 9)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("张三", 16)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("三哥", 12)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("米二", 8)));
    }

    @Test
    public void edit() {
        logger.info("编辑用户 result = {}", userCrudRepository.save(new User(3L, "三三", 16)));
    }

    @Test
    public void delete() {
        userCrudRepository.deleteById(3L);
    }

    @Test
    public void findById() {
        logger.info("通过 id 查询 result = {}",userCrudRepository.findById(1L));
    }


    @Test
    public void findAll(){
        logger.info("查询所有记录   result = {}",userCrudRepository.findAll());
    }


}
