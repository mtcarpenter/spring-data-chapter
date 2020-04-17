package com.mtcarpenter.repository;

import com.mtcarpenter.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 山间木匠
 * @Date 2020/2/29
 */
public interface UserQueryRepository extends JpaRepository<User, Long> {

    /**
     *  语句中 User 查询数据表的类名，?1 括号代表第一个参数
     */
    @Query(name = "select  * from User where name = ?1")
    List<User> findByName(String name);

    /**
     *  根据姓名模糊查询排序
     */
    @Query("select u from User u where u.name like ?1%")
    List<User> findByNameAndSort(String name, Sort sort);

    /**
     * @Transactional 事务的支持 ，@Modifying 用于修改查询
     * @param name 对应 ?1
     * @param id 对应 ?2
     * @return
     */
    @Transactional
    @Modifying
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int updateById(String  name, Long id);


    /**
     *  param 对象
     * @param name
     * @param age
     * @return
     */
    @Query(value = "select  u from User u where u.name = :name and u.age = :age")
    List<User> queryParamByNameAndAge(@Param("name") String name,@Param("age") Integer age);


    /**
     *  传一个对象
     * @param user
     * @return
     */
    @Query(value = "select  u from User u  where u.name = :#{#user.name} and u.age = :#{#user.age}")
    List<User> queryObjectParamByNameAndAge(@Param("user") User user);

    /**
     * spel
     * @param name
     * @return
     */
    @Query("select u from #{#entityName} u where u.name = ?1")
    List<User> queryELByName(String name);

    /**
     * 原生 sql
     * @param name
     * @return
     */
    @Query(value = "select * from t_user  where u_name = :name",nativeQuery = true)
    List<User> queryNativeByName(@Param("name") String name);

}
