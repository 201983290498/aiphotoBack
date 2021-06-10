package com.example.demo.config;

import com.example.demo.properties.Properties;
import com.example.demo.tool.MyJDBCTemplate;
import com.example.demo.tool.MyPictureIOS;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * 数据库连接类
 */
@Configuration
@EnableTransactionManagement
public class JDBCConfiguration {

  @Autowired
  Properties properties;

  @Bean("datasource")
  public ComboPooledDataSource getDataSource() throws PropertyVetoException {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl(properties.getJbdcUrl());
    dataSource.setUser(properties.getUser());
    dataSource.setPassword(properties.getPassword());
    return dataSource;
  }


  @Scope(value = "prototype")
  @Bean("jdbctemplate")
  public JdbcTemplate DatabaseTpl(@Autowired ComboPooledDataSource dataSource) throws PropertyVetoException {

    JdbcTemplate jdbcTemplate = new MyJDBCTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }

  @Bean("transactionManager")
  public DataSourceTransactionManager transactionManager(@Autowired ComboPooledDataSource dataSource){
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

  @Bean("myPictureIOS")
  public MyPictureIOS getMyPictureIOS(){
    return new MyPictureIOS();
  }



}
