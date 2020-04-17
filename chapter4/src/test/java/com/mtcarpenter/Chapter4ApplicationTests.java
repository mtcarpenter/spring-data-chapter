package com.mtcarpenter;


import com.mtcarpenter.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter4ApplicationTests {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(Chapter4ApplicationTests.class);

    @Test
    public void contextLoads() {
    }




    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;




    /**
     * redis String 操作
     */
    @Test
    public void redisStringTest() {
        // 1 字符串操作
        // set hello world
        stringRedisTemplate.opsForValue().set("hello", "world");
        // set user name  10 秒失效
        /**
         * TimeUnit.DAYS          //天
         * TimeUnit.HOURS         //小时
         * TimeUnit.MINUTES       //分钟
         * TimeUnit.SECONDS       //秒
         * TimeUnit.MILLISECONDS  //毫秒
         */
        stringRedisTemplate.opsForValue().set("user", "name", 10, TimeUnit.SECONDS);
        // 查询字符串
        logger.info("result = {}", stringRedisTemplate.opsForValue().get("hello"));
        // 删除
        stringRedisTemplate.delete("hello");
        // 查询删除之后的
        logger.info("result = {}", stringRedisTemplate.opsForValue().get("hello"));
    }

    /**
     * redis String 数字操作
     */
    @Test
    public void redisStringNumberTest() {
        // increment 自增
        // num 默认自增 1
        redisTemplate.opsForValue().increment("num");
        logger.info("result = {}", redisTemplate.opsForValue().get("num"));
        redisTemplate.opsForValue().increment("num", 2);
        logger.info("result = {}", redisTemplate.opsForValue().get("num"));
        // decrement 减
        redisTemplate.opsForValue().decrement("num");
        logger.info("result = {}", redisTemplate.opsForValue().get("num"));
    }

    /**
     * redis hash
     */
    @Test
    public void redisHashTest() {
        redisTemplate.opsForHash().put("userInfo", "name", "姓名");
        redisTemplate.opsForHash().put("userInfo", "age", 20);
        redisTemplate.opsForHash().put("userInfo", "sex", 0);
        // 删除
        redisTemplate.opsForHash().delete("userInfo", "sex");
        // 获取 userInfo name value
        logger.info("result = {}", redisTemplate.opsForHash().get("userInfo", "name"));
        // 查询 哈希键中的fields
        logger.info("result = {}", redisTemplate.opsForHash().keys("userInfo"));
        // 查询哈希键中的所有field的value
        logger.info("result = {}", redisTemplate.opsForHash().values("userInfo"));
    }

    /**
     * redis list
     */
    @Test
    public void redisListTest() {
        redisTemplate.opsForList().leftPush("list-user", "list1");
        redisTemplate.opsForList().leftPush("list-user", "list2");
        redisTemplate.opsForList().leftPush("list-user", "list3");
        redisTemplate.opsForList().rightPush("list-user", new User("list4", 12, "123@qq.com"));
        // 获取全部
        logger.info("result = {}", redisTemplate.opsForList().range("list-user", 0, -1));
        // 索引 为 1
        logger.info("result = {}", redisTemplate.opsForList().index("list-user", 1));
    }


    /**
     * redis set
     */
    @Test
    public void redisSetTest() {
        redisTemplate.opsForSet().add("set-key", "key1", "key2");
        redisTemplate.opsForSet().add("set-key", "key3");
        // 获取 key 的所有值
        logger.info("result = {}", redisTemplate.opsForSet().members("set-key"));
        // 判断 value 是否值是否在 key 中
        logger.info("result = {}", redisTemplate.opsForSet().isMember("set-key", "key3"));
    }


    /**
     * redis zset
     */
    @Test
    public void redisZSetTest() {
        redisTemplate.opsForZSet().add("zset-key", "key1", 90);
        redisTemplate.opsForZSet().add("zset-key", "key2", 92);
        redisTemplate.opsForZSet().add("zset-key", "key3", 82);
        // 获取 key 中的 value 的分数
        logger.info("result = {}", redisTemplate.opsForZSet().score("zset-key", "key3"));
        // 移除
        redisTemplate.opsForZSet().remove("zset-key", "key1");
        logger.info("result = {}", redisTemplate.opsForZSet().range("zset-key", 0, -1));
    }


    @Test
    public void test(){
        redisTemplate.opsForValue().set("test", "test");
        logger.info("result = {}", redisTemplate.opsForValue().get("test"));
    }

}
