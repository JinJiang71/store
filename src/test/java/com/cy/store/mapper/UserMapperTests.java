package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//表示标注的当前类是一个测试类，不会随同项目一起打包
@SpringBootTest
//表示启动这个单元测试类需要传递一个SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private  UserMapper userMapper;
    /**
     * 单元测试方法：可以独立运行，不用启动整个项目，可以做单元测试，提升代码测试效率
     * 1.必须被@Test注解修饰
     * 2.返回值必须为void类型
     * 3.形参列表为空，即不能给该方法传参数
     * 4.访问修饰符必须为public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("乐乐2");
        user.setPassword("123sca");
        System.out.println(userMapper.insert(user));
    }
    @Test
    public void findByUsername(){
        System.out.println(userMapper.findByUsername("乐乐2"));
    }

    @Test
    public void findByUid(){
        User byUid = userMapper.findByUid(9);
        System.out.println(byUid);
    }
    @Test
    public void updatePwd(){
        userMapper.updatePwdByUid(9,"123",new Date(),"Admin");
    }
}
