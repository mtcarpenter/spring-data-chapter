package com.mtcarpenter.chapter1.repository;

import com.mtcarpenter.chapter1.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 山间木匠
 * @Date 2020/2/28
 */
public interface UserCrudRepository extends CrudRepository<User, Long> {
}
