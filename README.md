# SSM
## 归纳Mybatis的第一天
### 一、配置文件
#### pom.xml:配置了mybatis3.5.2; 数据库8.0.12; log4j1.2.12; junit4.10用来单元测试;
#### resource/sqlMapConfig.xml:mybatis主配置文件：
##### 1.配置了mysql环境包括事务类型、连接池、基本信息
##### 2.指定映射配置文件，就是每个dao独立的配置文件
#### resource/IUserDao.xml:相应的mapper：
  
