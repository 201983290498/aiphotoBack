package com.example.demo.tool;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读写类
 */
@Data
@Component("prosReader")
public class PropertiesReader {
  private Properties pros;
  private static final String classpath = "C:\\Users\\coder\\Desktop\\demo\\src\\main\\resources\\";
  public void readProperties(String abspath){
     pros = new Properties();
    try {
      FileInputStream inputStream = new FileInputStream(classpath+abspath);
      pros.load(inputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Object getProperties(Object key){
    return pros.get(key);
  }
}

