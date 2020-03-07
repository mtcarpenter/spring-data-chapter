package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 山间木匠
 * @Date 2020/3/2
 */
public interface UserJpaRepository extends JpaRepository<User,Long> {
}
