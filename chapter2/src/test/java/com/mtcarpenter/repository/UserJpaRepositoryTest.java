package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJpaRepositoryTest {
    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserJpaRepositoryTest.class);

    @Autowired
    private UserJpaRepository userJpaRepository;


    @Before
    public void save() {
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("小米", 9, "a@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("张三", 16, "b@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("三哥", 12, "c@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("米二", 13, "e@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("阿三", 12, "f@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("张三", 12, "g@qq.com")));
        logger.info("新增数据 result = {}", userJpaRepository.save(new User("米二", 8, "h@qq.com")));
    }

    @Test
    public void specification() {
        Specification specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                // like 模糊查询 ， root.get("name") 属性名  "%三%" 为三
                Predicate p1 = cb.like(root.get("name"), "%三%");
                // greaterThan 表示 age 大于 10
                Predicate p2 = cb.greaterThan(root.get("age"), 10);
                // cb.and(p1, p2) ，and 则表示 p1 和 p2 并且关系，除了 and 还有or, not等。点击  CriteriaBuilder 可进行查看
                return cb.and(p1, p2);
            }
        };

    }


    @Test
    public void ConditionalQuery() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        // 模拟传入的条件
        User user = new User("三", 10, "b@qq.com");
        Specification specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                // 判断传入的值是否为空
                if (!"".equals(user.getName())) {
                    predicates.add(cb.like(root.get("name"), "%" + user.getName() + "%"));
                }
                // 判断年龄是否为空
                if (user.getAge() != null) {
                    predicates.add(cb.greaterThan(root.get("age"), user.getAge()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page result = userJpaRepository.findAll(specification, pageable);
        logger.info("条件查询 result = {}", result.getContent());
    }

}