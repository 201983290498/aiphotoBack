package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserEx;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service("userService")
public class UserService {

  @Autowired
  private UserRepository userDao;

  public boolean check_in(String name,String password){
    return userDao.find(name,password);
  }
  public boolean check_in_Ex(String name,String pripassword){
    return userDao.findEx(name,pripassword);
  }

  public boolean register(User user){
    return userDao.insert(user);
  }

  public User getUser(String username){
    return userDao.getUser(username);
  };

  public boolean registerEx(UserEx userEx) {
    return userDao.insertEx(userEx);
  }

}
