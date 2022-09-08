package com.wuqihang.syblog.api;

import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.ResponsePKG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class AccountController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;

    @PostMapping("create-account")
    public ResponsePKG createAccount(@RequestParam String username, @RequestParam String password,
                                     @Nullable @RequestParam String name, @Nullable @RequestParam String tel,@Nullable @RequestParam String address,
                                     @Nullable @RequestParam String email, @Nullable @RequestBody String remarks) {
        //TODO:Create Account
        return new ResponsePKG();
    }

    @PostMapping("bind-account-user")
    public ResponsePKG bindAccountUser(@RequestParam String username, String Password,
                                       @Nullable @RequestParam String name, @Nullable @RequestParam String tel,@Nullable @RequestParam String address,
                                       @Nullable @RequestParam String email, @Nullable @RequestBody String remarks) {
        return ResponsePKG.ok();
    }

    //TODO:AccountController
}
