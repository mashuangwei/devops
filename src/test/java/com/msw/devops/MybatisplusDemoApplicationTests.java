package com.msw.devops;

import com.msw.devops.entity.User;
import com.msw.devops.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusDemoApplicationTests {

    private Random random = new Random();

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("测试用户" + random.nextInt());
        user.setStatus("1");
        userService.addUser(user);
    }

    @Test
    public void testSelectUser() {

        List<User> list = userService.list(null);
        System.err.println(list.toString());

    }


}
