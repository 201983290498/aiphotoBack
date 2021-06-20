# 运行前
  软件需求：
  - mysql
  - maven
  - idea

  参数设置:
  - 找到源文件下的src\main\java\com\example\demo\properties中的Properties.java类
  - 设置,其中三个@value中的值，分别是mysql数据库，mysql用户名和密码 
```java
//数据库配置:
      @Value("#{'jdbc:mysql://localhost:3306/aiphoto'}")
      private String jbdcUrl;
      @Value("#{'root'}")
      private String user;
      @Value("#{'123456'}")
      private String password;
```  

## 待验证的功能

- 注册时添加独立密码
- 正确添加图片，并获得多值属性categy
- 能正确登入独立密码
- 正确使用 categy，获取第一属性和通过第二属性全局搜素图片
         

