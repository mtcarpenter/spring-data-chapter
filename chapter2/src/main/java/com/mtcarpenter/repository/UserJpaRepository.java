package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
public interface UserJpaRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor {
}
