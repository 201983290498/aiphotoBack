package com.example.demo.repository;


import com.example.demo.entity.User;
import com.example.demo.entity.UserEx;
import org.springframework.stereotype.Repository;


public interface UserRepository {
  /*数据库表格的名称*/
  static final String tableName = "tbl_user";
  boolean insert(User user);
  boolean insertEx(UserEx userEx);
  boolean find(String name,String password);
  boolean findEx(String name, String pripassword);
  User getUser(String username);

  void createTable();
  void Init();


}
