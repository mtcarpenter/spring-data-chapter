package com.mtcarpenter.repository;


import com.mtcarpenter.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserQueryRepositoryTest {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserQueryRepositoryTest.class);

    @Autowired
    private UserQueryRepository userQueryRepository;


    @Before
    public void save() {
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("小米", 9, "a@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("张三", 16, "b@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("三哥", 12, "c@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("米二", 13, "e@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("阿三", 12, "f@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("张三", 12, "g@qq.com")));
        logger.info("新增数据 result = {}", userQueryRepository.save(new User("米二", 8, "h@qq.com")));
    }

    /**
     * 基本使用
     */
    @Test
    public void test() {
        logger.info("@query 查询张三 result = {}", userQueryRepository.findByName("张三"));
        logger.info("根据姓名模糊查询排序 result = {}", userQueryRepository.findByNameAndSort("米", new Sort(Sort.Direction.ASC,"age")));
        logger.info("修改 id = 1 的name ，result ={ }", userQueryRepository.updateById("红米", 1L));
    }

    /**
     *  param 参数使用
     */
    @Test
    public void paramTest(){
        logger.info("@param 使用方法  result = {}",userQueryRepository.queryParamByNameAndAge("张三", 12));
        User user = new User();
        user.setName("张三");
        user.setAge(12);
        logger.info("@Param 对象 result = {}", userQueryRepository.queryObjectParamByNameAndAge(user));
    }

    /**
     * SpEl 使用
     */
    @Test
    public void spELTest(){
        logger.info("SpEL 使用方法  result = {}",userQueryRepository.queryELByName("张三"));
    }

    /**
     * 原生查询
     */
    @Test
    public void nativeTest(){
        logger.info("原生查询 使用方法  result = {}",userQueryRepository.queryNativeByName("张三"));

    }
}
