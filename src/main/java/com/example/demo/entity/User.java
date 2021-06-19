package com.example.demo.entity;


import java.util.Map;

//@Data
//@Component("user")
public class User {

  private String username;
  private String password;
  private String truName;
  private String email;
  private Long faceSet;
  public User() {

  }

  public User(String username, String password, String truName, String email, Long faceSet) {
    this.username = username;
    this.password = password;
    this.truName = truName;
    this.email = email;
    this.faceSet = faceSet;
  }
  public User(Map<String,Object> data) {
    this.username = (String) data.get("username");
    this.password = (String) data.get("password");
    this.truName = (String) data.get("truName");
    this.email = (String) data.get("email");
  }

  public User(String username, String password, String truName, String email) {
    this.username = username;
    this.password = password;
    this.truName = truName;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTruName() {
    return truName;
  }

  public void setTruName(String truName) {
    this.truName = truName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User{" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", truName='" + truName + '\'' +
      ", email='" + email + '\'' +
      ", faceSet=" + faceSet +
      '}';
  }

  public Long getFaceSet() {
    return faceSet;
  }

  public void setFaceSet(Long faceSet) {
    this.faceSet = faceSet;
  }

}
