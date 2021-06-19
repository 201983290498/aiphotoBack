package com.example.demo.config;

import com.example.demo.AI.entity.FaceSet;
import com.example.demo.AI.tool.AIClassify;
import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.entity.ImageClassfication;
import com.example.demo.entity.Picture;
import com.example.demo.entity.User;
import com.example.demo.entity._Base64Picture;
import com.example.demo.properties.Properties;
import com.example.demo.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库初始化类
 */
@Configuration
public class chartsConfiguration {



  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Properties properties;

  /**
   * 初始化数据库
   *  返回一个user
   */
  @Bean("user")
  public User getUser(){
    User user = new User();
    userRepository.createTable();
    return user;
  }

  @Bean("faceHandler")
  public FaceHandler getFaceHandler(){
    FaceHandler faceHandler  = new FaceHandler();
    faceHandler.init();
    return faceHandler;
  }

  @Bean("aiClassify")
  public AIClassify getAiClassify(){
    AIClassify aiClassify = new AIClassify();
    aiClassify.setUrl(properties.getAIURL());
    return aiClassify;
  }
}
