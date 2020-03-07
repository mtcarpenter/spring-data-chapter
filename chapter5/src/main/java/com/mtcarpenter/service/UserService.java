package com.mtcarpenter.service;

import com.mtcarpenter.entity.User;

/**
 * @author 山间木匠
 * @Date 2020/3/2
 */
public interface UserService {

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
