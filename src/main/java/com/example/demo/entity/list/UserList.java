package com.example.demo.entity.list;

import com.example.demo.entity.User;

import java.util.List;


public class UserList{
  public List<User> userList;

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  public UserList(List<User> userList) {
    this.userList = userList;
  }

  public UserList() {
  }
}
