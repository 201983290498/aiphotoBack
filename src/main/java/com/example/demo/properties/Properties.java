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
  @Value("${database.url}")
  private String jbdcUrl;
  @Value("${database.username}")
  private String user;
  @Value("${database.password}")
  private String password;

  /**
   * 人脸可靠度
   */
  @Value("${AIFace.confidence}")
  private double confidence;

  @Value("${AIFace.myurl}")
  private String AIURL;

  @Value("#{'人物,动物,自然风景,交通工具,建筑,others'.split(',')}")
  private List<String> imgCategy;

  @Value("${AIFace.AK}")
  private String Classify_AK;

  @Value("${AIFace.SK}")
  private String Classify_SK;

  @Value("${AIFace.url}")
  private String Classify_url;

  public Properties() {
  }
}
