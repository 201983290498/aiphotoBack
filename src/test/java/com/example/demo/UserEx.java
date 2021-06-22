package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEx {

    @Autowired
    UserRepository userDao;

    @Test
    void test(){
        userDao.insertEx(new com.example.demo.entity.UserEx("grayson1","111111"));
    }
}
