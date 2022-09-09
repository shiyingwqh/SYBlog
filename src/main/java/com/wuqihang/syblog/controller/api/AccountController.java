package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.mapper.AccountMapper;
import com.wuqihang.syblog.mapper.UserMapper;
import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class AccountController {
    final
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create-account")
    public ResponsePKG createAccount(@RequestBody Account account) {
        if (account == null || account.getUser() == null || account.getUser().getUsername() == null || account.getUser().getPassword() == null) {
            return ResponsePKG.ERROR_ILLEGAL_DATA;
        }
        accountService.create(account);
        return new ResponsePKG();
    }

    @PostMapping("bind-account-user")
    public ResponsePKG bindAccountUser(@RequestBody Account account) {
        if (account == null || account.getUser() == null || account.getUser().getUsername() == null || account.getUser().getPassword() == null) {
            return ResponsePKG.ERROR_ILLEGAL_DATA;
        }
        User user = accountService.getUser(account);
        account.setUser(user);
        accountService.update(account);
        return ResponsePKG.ok();
    }

    @PostMapping("delete-account")
    public ResponsePKG deleteAccount(@RequestParam String id) {
        accountService.delete(id);
        return ResponsePKG.OK;
    }

    @PostMapping("update-account")
    public ResponsePKG account(@RequestBody Account account) {
        return accountService.update(account) ? ResponsePKG.OK : ResponsePKG.error(-1, "Update Failed!");
    }
}
