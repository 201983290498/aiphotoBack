package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserEx;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
  //二次身份核验
  @RequestMapping(value = "/login/Ex", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
  public boolean LoginEx(@RequestBody UserEx user){
    log.info(user.getUsername()+user.getPripassword()+"二次独立验证");
    return userService.check_in_Ex(user.getUsername(),user.getPripassword());
  }

  @PostMapping("/register")
  public boolean register(@RequestBody Map<String,Object>data){
    return userService.register(new User(data))&&userService.registerEx(new UserEx(data));
  }
//  @PostMapping("/register")//添加用户
//  public boolean addUser(@RequestBody User user){
//    log.info(user.getUsername());
//    return userService.register(user);
//  }
}
