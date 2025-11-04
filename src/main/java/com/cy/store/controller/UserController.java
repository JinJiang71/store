package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK,"用户注册成功！");
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        //直接将HttpSession类型的对象作为请求处理方法的参数，SpringBoot会自动将全局的session对象注入到请求方法的session形参上
        User loginUser = userService.login(username, password);
        session.setAttribute("uid",loginUser.getUid());
        session.setAttribute("username",loginUser.getUsername());
        return new JsonResult<>(OK,loginUser);
    }
    @RequestMapping("resetPwd")
    public JsonResult<Void> resetPassword(String oldPassword, HttpSession session,String newPassword) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.resetPwd(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK,"密码修改成功！");
    }
}
