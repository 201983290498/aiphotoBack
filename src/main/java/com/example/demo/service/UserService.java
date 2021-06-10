package com.example.demo.service;

import com.example.demo.entity.User;
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

  public boolean register(User user){
    return userDao.insert(user);
  }

  public User getUser(String username){
    return userDao.getUser(username);
  };

}
