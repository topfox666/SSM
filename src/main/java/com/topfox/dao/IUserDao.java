package com.topfox.dao;

import com.topfox.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 插入某个用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新某用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 找到user通过Id
     * @param id
     * @return User
     */
    User findById(Integer id);

    /**
     * 找到user通过名称
     * @param name
     * @return  用户列表
     */
    List<User> findByName(String name);

    /**
     * 返回总的记录数
     * @return
     */
    int userCount();
}
