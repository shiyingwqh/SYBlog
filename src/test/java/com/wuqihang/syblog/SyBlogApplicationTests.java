package com.wuqihang.syblog;

import com.wuqihang.syblog.config.UserConfiguration;
import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.Token;
import com.wuqihang.syblog.security.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@EnableConfigurationProperties(UserConfiguration.class)
class SyBlogApplicationTests {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenManager tokenManager;
    @Value("#{userConfiguration.key}")
    String key;
    @Value("#{userConfiguration.bytes}")
    private Byte[] bytes;

    @Test
    void contextLoads() {
        System.out.println(key);
        System.out.println(Arrays.toString(bytes));
    }

}
