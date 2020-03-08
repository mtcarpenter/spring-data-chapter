package com.mtcarpenter.service;

import com.mtcarpenter.entity.User;

import java.util.List;

/**
 * @author 山间木匠
 * @Date 2020/3/3
 */
public interface UserService {

    /**
     * 查询所有数据
     * @return
     */
    List<User> list();

    /**
     * 根据 id 查找
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    User update(User user);


    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);


}
