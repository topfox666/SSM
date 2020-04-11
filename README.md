# SSM
## 归纳Mybatis的第一天
### 一、配置文件
#### pom.xml:配置了mybatis3.5.2; 数据库8.0.12; log4j1.2.12; junit4.10用来单元测试;
#### resource/sqlMapConfig.xml:mybatis主配置文件：
##### 1.配置了mysql环境包括事务类型、连接池、基本信息
##### 2.指定映射配置文件，就是每个dao独立的配置文件
#### resource/IUserDao.xml:相应的mapper：
# 注意：mysql用8.0的要设置时区+8-》set global time_zone = '+8:00'
##### 3.尝试用注解开发：在IUserDao2中用注解@select,并在映射配置中采用class。
##### 4.mybatis中使用的设计模式：构建者模式，把对象的创建细节隐藏，使用者直接调用即可拿到，构建者为builder；
#####                          工厂模式，降低类之间的依赖关系，工厂为factory；
#####                          代理模式，不修改源码的基础对已有方法的增强，getMapper！
##### 5.根据配置文件创建Connection对象：注册驱动，获取连接
#####   获取预处理对象PreparedStatement,此时需要sql语句，conn.preparedStatement(sql)
#####   执行查询 ResultSet =preparedStatement.executeQuery()
#####   遍历结果集用于封装 list.add(element)  element由全限定类型来，表的类名和实体类属性一致，所以可以用反射根据名称获取属性，把值放进去
#####   所以映射信息包含两个：执行sql语句，封装结果的实体类全限定类名（这两个组合成一个对象）
##### 6.getMapper(IUserDao.class)的原理
#####   底层是Proxy.newProxyInstance(类加载器，代理对象实现的接口字节码数组，如何地理)；
#####   如何代理？ 此处是一个InvocationHandler  
##### 7.自定义mybatis: class Resource(用于读入一个xml文件)
#####                 SqlSessionFactoryBuilder;根据输入流构建一个工厂（解析xml文件）
#####                 SqlSessionFactory;用于创建新的连接session,新的操作数据库连接对象
#####                 SqlSession();核心类,用于和数据库交互，可以创建dao接口的代理对象，有getMapper
#####                 Configuration;追加mapper,把所有mapper加进去
#####                 Mapper;用于封装sql语句，读取mapper（取resource）

## 归纳Mybatis的第二天
### 为什么输入的汉字到数据库变成问号？    要修改表的字符集为utf-8，统一数据库和配置文件中的url
### 模糊查询的写法: 在配置文件中 select * from user where username like #{username} 参数占位符（这种可取）
###                              前面都一样………………       username='%${value}%'    字符串拼接