# 运行前
  软件需求：
  - mysql
  - maven
  - idea

  参数设置:
  - 找到源文件下的src\main\java\com\example\demo\properties中的Properties.java类
  - 设置,其中三个@value中的值，分别是mysql数据库，mysql用户名和密码
      /**
       * 数据库
       */
      @Value("#{'jdbc:mysql://localhost:3306/aiphoto'}")
      private String jbdcUrl;
      @Value("#{'root'}")
      private String user;
      @Value("#{'123456'}")
      private String password;
