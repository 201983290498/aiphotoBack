# 运行前
  软件需求：
  - mysql
  - maven
  - idea

  参数设置:
  - 在运行时需要配置本地数据库的信息，配置文件位置为src/main/resource/application.yml
  - 分别是mysql数据库，mysql用户名和密码 
```yml
#数据库配置:
database:
  url: jdbc:mysql://localhost:3306/aiphoto
  username: root
  password: 123456    
```  

## 待验证的功能

- 正确添加图片，并获得多值属性categy
- 正确使用 categy，获取第一属性和通过第二属性全局搜素图片


# 软件杯学习

## 1.mysql语句的使用

### isnull,ifnull,nullif

- isnull(a),如果a是null返回true,否则返回false;
- ifnull(a,b),如果a是null,输出b，否则输出a;
- nullif(a，b),如果a=b返回null,否则输出a

