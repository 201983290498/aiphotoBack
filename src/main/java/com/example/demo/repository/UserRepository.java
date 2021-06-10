package com.example.demo.repository;


import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;


public interface UserRepository {
  /*数据库表格的名称*/
  static final String tableName = "tbl_user";
  boolean insert(User user);
  boolean find(String name,String password);
  User getUser(String username);
  void createTable();
  void Init();
}
