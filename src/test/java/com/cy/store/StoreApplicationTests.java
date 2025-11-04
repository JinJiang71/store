package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired  //自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }
    @Test
    void getConnection() throws SQLException {
        //Hikari是SpringBoot默认整合的数据库连接池
        //HikariProxyConnection@2000855670 wrapping com.mysql.cj.jdbc.ConnectionImpl@66a472b9
        System.out.println(dataSource.getConnection());
    }
}
