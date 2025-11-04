package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层的实现类
 */
@Service //@Service注解：将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User resUser = userMapper.findByUsername(username);
        if (resUser != null) {//若用户已经存在，抛异常
            throw new UsernameDuplicatedException("用户名已经存在！");
        }
        //若用户不存在，补全相应的信息存表
        user.setIsDelete(0);
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //插入之前需将明文密码进行加密处理：MD5(盐值+密码+盐值)三次加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("用户数据插入时发生错误，数据未成功插入！");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("登录时发生错误：用户不存在！");
        }
        String salt = result.getSalt();
        String oldPassword = result.getPassword();
        String newPassword = getMD5Password(password, salt);
        if (!oldPassword.equals(newPassword)) {
            throw new PasswordNotMatchException("密码错误，登录失败！");
        }
        //前端需要的用户数据：username,uid,avatar
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());

        return user;
    }

    /**
     * 修改密码
     * @param uid
     * @param currentUser  当前操作人
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     */
    @Override
    public void resetPwd(Integer uid, String currentUser, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在！");
        }
        //用户数据存在，进行旧密码的比较
        String salt = result.getSalt();
        String oldMD5Password = getMD5Password(oldPassword, salt);
        if (!oldMD5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("输入的原密码有误！");
        }
        //将新密码加密更新到表中
        String newMD5Password = getMD5Password(newPassword, salt);
        Integer rows = userMapper.updatePwdByUid(uid, newMD5Password, new Date(), currentUser);
        if (rows != 1) {
            throw new UpdateException("数据修改时发生错误，修改失败！");
        }
    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
