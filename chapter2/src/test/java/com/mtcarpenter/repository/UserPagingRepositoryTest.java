package com.mtcarpenter.repository;


import com.mtcarpenter.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPagingRepositoryTest {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserPagingRepositoryTest.class);

    @Autowired
    private UserPagingRepository userPagingRepository;


    @Before
    public void save() {
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("小米", 9,"a@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("张三", 16,"b@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("三哥", 12,"c@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("米二", 13,"e@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("阿三", 12,"f@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("张三", 12,"g@qq.com")));
        logger.info("新增数据 result = {}", userPagingRepository.save(new User("米二", 8,"h@qq.com")));
    }


    @Test
    public void find(){
        logger.info("通过姓名查找(findByName) result = {}", userPagingRepository.findByName("张三"));
        logger.info("通过姓名查找(queryByName) result = {}", userPagingRepository.queryByName("张三"));
        logger.info("通过姓名或者邮箱(findByNameOrEmail)  查找 result = {}", userPagingRepository.findByNameOrEmail("张三","f@qq.com"));
        logger.info("通过某一个 age 的数量(countByAge) result = {}", userPagingRepository.countByAge(12));
    }


    @Test
    public void pageAndSort(){
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, sort);
        logger.info("条件查询 result = {}", userPagingRepository.findByName("张三",pageable));
        logger.info("---------------------------------");
        logger.info("根据年龄排序 result = {}", userPagingRepository.findAll(sort));
    }

}
