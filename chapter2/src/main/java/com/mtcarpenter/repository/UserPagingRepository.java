package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
public interface UserPagingRepository extends PagingAndSortingRepository<User, Long> {

    // 通过姓名查找
    List<User> findByName(String name);

    // 通过姓名查找
    List<User> queryByName(String name);

    // 通过姓名或者邮箱查找
    List<User> findByNameOrEmail(String name,String email);

    // 计算某一个 age 的数量
    int countByAge(int age);


    // 通过姓名条件查询
    List<User> findByName(String name, Pageable pageable);




}
