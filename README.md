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

## 待整理的功能

- postMapping前后端的使用方法;

# 软件杯学习

## 1.mysql语句的使用

### isnull,ifnull,nullif

- isnull(a),如果a是null返回true,否则返回false;
- ifnull(a,b),如果a是null,输出b，否则输出a;
- nullif(a，b),如果a=b返回null,否则输出a

## 2.java

### 2.1 集合的排序
#### Collections.sort()
  他的作用是给集合进行排序，例如list,set等自己定义的对象对象进行排序，如果
  同类比较
    - ArrayList.sort(),arrayList是个List列表类型的子类，sort是对象自带的方法，可以对这个队列进行排序，如果是自定义的对象，我们需要传入一个Comparator用法如下：
```java
import java.util.*;
ArrayList list = new ArrayList<User>();
list.sort();//1.user继承了Comparable接口
list.sort(new Comparator<User>(){//2.传入一个比较器
    @Override
    public int compare(User user1,User user2){
        
    }
})
```
    - Arrays.sort();是个静态工具类。对数组进行排序，例如int[]
```java
Arrays.sort(数组,比较器);
Arrays.sort(int[]);
Arrays.sort(数组,start,end);//[start,end)
```
