package com.mtcarpenter.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 山间木匠
 * @Date 2020/3/3
 */
@Entity
@Table
public class User implements Serializable {

    private static final long serialVersionUID = -4686698637106117437L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    public User() {
    }

    public User(String name, Integer age,String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }


    public User(Long id,String name, Integer age,String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
