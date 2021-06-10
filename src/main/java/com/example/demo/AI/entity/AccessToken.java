package com.example.demo.AI.entity;

/**
 * Copyright 2021 bejson.com
 */

/**
 * Auto-generated: 2021-05-24 11:11:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AccessToken {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    public void setAccess_token(String access_token) {
      this.access_token = access_token;
    }
    public String getAccess_token() {
      return access_token;
    }

    public void setToken_type(String token_type) {
      this.token_type = token_type;
    }
    public String getToken_type() {
      return token_type;
    }

    public void setExpires_in(String expires_in) {
      this.expires_in = expires_in;
    }
    public String getExpires_in() {
      return expires_in;
    }

    public void setRefresh_token(String refresh_token) {
      this.refresh_token = refresh_token;
    }
    public String getRefresh_token() {
      return refresh_token;
    }

  @Override
  public String toString() {
    return "AccessToken{" +
      "access_token='" + access_token + '\'' +
      ", token_type='" + token_type + '\'' +
      ", expires_in='" + expires_in + '\'' +
      ", refresh_token='" + refresh_token + '\'' +
      '}';
  }
}
