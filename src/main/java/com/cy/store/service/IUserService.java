package com.cy.store.service;

import com.cy.store.entity.User;
import jakarta.servlet.http.HttpSession;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户对象的数据
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    User login(String username, String password);


    void resetPwd(Integer uid,String currentUser,String oldPassword,String newPassword);
}
