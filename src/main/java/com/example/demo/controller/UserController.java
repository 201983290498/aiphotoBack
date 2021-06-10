package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

  @Autowired
  private UserService userService;

//  @ResponseBody
  @RequestMapping(value = "/login", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
  public boolean Login(@RequestBody User user){
    log.info(user.getUsername()+user.getPassword());
    return userService.check_in(user.getUsername(),user.getPassword());
  }

  @PostMapping("/register")
  public boolean register(@RequestBody User user){
    return userService.register(user);
  }
//  @PostMapping("/register")//添加用户
//  public boolean addUser(@RequestBody User user){
//    log.info(user.getUsername());
//    return userService.register(user);
//  }
}
