package com.wuqihang.syblog;

import com.wuqihang.syblog.config.SYConfiguration;
import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import com.wuqihang.syblog.services.FileService;
import com.wuqihang.syblog.utils.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

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
    @Autowired
    BlogService blogService;
    @Autowired
    FileService fileService;
    @Autowired
    FileUtil fileUtil;

    @Test
    void contextLoads() {
//        Account account = new Account();
//        account.setUser(new User(String.valueOf(System.currentTimeMillis()), "admin", "password"));
//        account.setName("吴琪杭");
//        account.setAddress("浙江省绍兴市群贤中路东浦街道2799号");
//        account.setEmail("wqhshiying@163.com");
//        account.setTel("18815191658");
//        account.setRemarks("一名平平无奇的大学生");
//        accountService.insert(account);
//        Blog blog = new Blog();
//        long l = System.currentTimeMillis();
//        String id = String.valueOf(l);
//        blog.setId(id);
//        blog.setLike(0);
//        blog.setDislike(0);
//        blog.setTitle("第一");
//        blog.setText("你好啊");
//        blog.setUpTime(l);
//        blog.setModifyTime(l);
//        blogService.insert(blog);
//        List<String> allFilePath = fileService.getAllFilePath();
//        System.out.println(allFilePath);
//        File root = fileUtil.getRoot();
//        File file = new File(root, "img\\Figure_2.png");
//        String path = "/img/Figure_2.png";
////        fileService.delete(path);
//        fileService.getFileWithPath(path);
//        System.out.println(file);
//        List<Account> allAccount = accountMapper.getAllAccount();
//        System.out.println(allAccount);
        System.out.println(new File("C:\\Users\\shiying\\SYBlog\\theme\\commons\\foot.html").exists());
    }

}
