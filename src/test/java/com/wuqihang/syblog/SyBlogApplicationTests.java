package com.wuqihang.syblog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqihang.syblog.config.SYConfiguration;
import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.CommentMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Comment;
import com.wuqihang.syblog.pojo.Theme;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import com.wuqihang.syblog.services.FileService;
import com.wuqihang.syblog.services.ThemeService;
import com.wuqihang.syblog.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@EnableConfigurationProperties(SYConfiguration.class)
class SyBlogApplicationTests {

    @Test
    void contextLoads() {
    }

}
