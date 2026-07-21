package com.antoine.forum.services.impl;

import com.antoine.forum.model.User;
import com.antoine.forum.services.IUserService;
import com.antoine.forum.utils.MD5Util;
import com.antoine.forum.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private IUserService userService;

    @Test
    void createNormalUser() {
        User user = new User();
        user.setUsername("test");
        user.setNickname("test");
        String password = "123456";
        String salt = UUIDUtil.UUID_32();
        String ciphertext = MD5Util.md5Salt(password,salt);
        user.setPassword(ciphertext);
        user.setSalt(salt);
        userService.createNormalUser(user);
        System.out.println(user);
    }
}