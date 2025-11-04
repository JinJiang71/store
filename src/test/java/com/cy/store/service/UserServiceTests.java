package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//表示标注的当前类是一个测试类，不会随同项目一起打包
@SpringBootTest
//表示启动这个单元测试类需要传递一个SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired//若当前项目有多个IUserService实现类，这个自动注入就需要指明实现类，不然就会语法报错
    private IUserService userService;
    /**
     * 单元测试方法：可以独立运行，不用启动整个项目，可以做单元测试，提升代码测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值必须为void类型
     * 3.形参列表为空，即不能给该方法传参数
     * 4.访问修饰符必须为public
     */
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("test2");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User test2 = userService.login("test2", "123");
        System.out.println(test2);
    }

    @Test
    public void resetPwd(){
        userService.resetPwd(11,"admin","321","123");
    }
}
