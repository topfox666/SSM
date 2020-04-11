package com.topfox.test;

import com.topfox.dao.IUserDao;
import com.topfox.dao.IUserDao2;
import com.topfox.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iuserDao;
    @Before  //用于在测试方法执行之前
    public void init()throws Exception{
        //1.读取配置文件
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建builder（用来解析xml文件的）
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession=factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        iuserDao=sqlSession.getMapper(IUserDao.class);
    }
    @After   //用于测试方法执行之后
    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void findAllUser ()throws Exception {
        //5.使用代理对象执行方法
        List<User> users=iuserDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testSave(){
        User user=new User();
        user.setUsername("你谁");
        user.setSex("男");
        user.setAddress("海景");
        user.setBirthday(new Date());

        //执行方法
        iuserDao.saveUser(user);
    }
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(67);
        user.setUsername("佳晴");
        user.setSex("女");
        user.setAddress("海景");
        user.setBirthday(new Date());

        //执行方法
        iuserDao.updateUser(user);
    }

    @Test
    public void testDelete(){

        //执行方法
        iuserDao.deleteUser(68);
    }

    @Test
    public void testFindById(){

        //执行方法
        User user=iuserDao.findById(67);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){

        //执行方法
        List<User> users=iuserDao.findByName("王");
        for(User user:users)
            System.out.println(user);
    }

    @Test
    public void testUserCount(){

        System.out.println("总人数有:"+iuserDao.userCount());
    }


}
