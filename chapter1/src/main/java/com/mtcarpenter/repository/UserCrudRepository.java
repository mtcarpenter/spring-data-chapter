package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 山间木匠
 * @Date 2020/2/28
 */
public interface UserCrudRepository extends CrudRepository<User, Long> {
}
