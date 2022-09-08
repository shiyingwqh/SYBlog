package com.wuqihang.syblog.api;

import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.Token;
import com.wuqihang.syblog.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    TokenManager tokenManager;
    @Autowired
    UserMapper userMapper;

    @PostMapping("get-token")
    public ResponsePKG getToken(@RequestParam String username, @RequestParam String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            return ResponsePKG.error(-1, "Failed!");
        }
        Token token = tokenManager.createToken(user);
        return ResponsePKG.returnData("Success!", token);
    }

    @PostMapping
    public ResponsePKG checkUser(@RequestParam String username, @RequestParam String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        return user != null ? ResponsePKG.ok() : ResponsePKG.error(-1, "Username Not Exist or Password is Wrong");
    }

    @PostMapping("update-user")
    public ResponsePKG updateUser(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            return ResponsePKG.error(-1, "Username Not Exist or Password is Wrong");
        }
        userMapper.update(user);
        return ResponsePKG.ok();
    }

    @PostMapping("create-user")
    public ResponsePKG createUser(@RequestParam String username, @RequestParam String password, @RequestParam String token) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            return ResponsePKG.error(-1, "User Exist!");
        }
        user = new User();
        user.setId(String.valueOf(System.currentTimeMillis()));
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insert(user);
        return ResponsePKG.returnData("OK", user.getId());
    }
}
