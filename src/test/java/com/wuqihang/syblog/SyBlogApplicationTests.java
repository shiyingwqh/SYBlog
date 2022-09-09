package com.wuqihang.syblog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqihang.syblog.config.SYConfiguration;
import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@EnableConfigurationProperties(SYConfiguration.class)
class SyBlogApplicationTests {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenManager tokenManager;
    @Value("#{SYConfiguration.key}")
    String key;
    @Value("#{SYConfiguration.bytes}")
    private Byte[] bytes;
;
    @Test
    void contextLoads() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(new User("111", "@@@", "@@@")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
