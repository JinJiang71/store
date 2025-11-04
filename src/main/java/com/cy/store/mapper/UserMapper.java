package com.cy.store.mapper;

import com.cy.store.entity.User;
import java.util.Date;

//@Mapper
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户的数据
     * @return 受影响的行数(增,删,改,都将受影响的行数作为返回值，可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据(User对象)，如果没有找到就返回NULL
     */
    User findByUsername(String username);


    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 修改密码
     * @return
     */
    Integer updatePwdByUid(Integer uid,String password, Date modifiedTime ,String modifiedUser);
}
