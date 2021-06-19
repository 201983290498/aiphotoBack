package com.example.demo.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 配置类暂时包含各种配置
 */
@Data
@Component
public class Properties {

  /**
   * 数据库
   */
  @Value("#{'jdbc:mysql://localhost:3306/aiphoto'}")
  private String jbdcUrl;
  @Value("#{'root'}")
  private String user;
  @Value("#{'123456'}")
  private String password;

  /**
   * 人脸可靠度
   */
  @Value("#{0.85}")
  private double confidence;

  @Value("http://192.168.43.184:8899/imgCfy")
  private String AIURL;

  @Value("#{'人物,动物,自然风景,交通工具,建筑,others'.split(',')}")
  private List<String> imgCategy;

  public Properties() {
  }
}
