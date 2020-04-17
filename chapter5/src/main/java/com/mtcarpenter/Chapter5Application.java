package com.mtcarpenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 山间木匠
 * @Date 2020/3/2
 */
@SpringBootApplication
@EnableCaching
public class Chapter5Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }

}
