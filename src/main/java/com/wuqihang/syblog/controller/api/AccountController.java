package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class AccountController {
    final AccountService accountService;
    final UserService userService;
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

//    @PostMapping("create-account")
    public ResponsePKG createAccount(@RequestBody Account account) {
        if (account == null || account.getUser() == null || account.getUser().getUsername() == null || account.getUser().getPassword() == null) {
            return ResponsePKG.ERROR_ILLEGAL_DATA;
        }
        if (userService.exist(account.getUser())) {
            accountService.create(account);
        }
        return new ResponsePKG();
    }

//    @PostMapping("bind-account-user")
    public ResponsePKG bindAccountUser(@RequestBody Account account) {
        if (account == null || account.getUser() == null || account.getUser().getUsername() == null || account.getUser().getPassword() == null) {
            return ResponsePKG.ERROR_ILLEGAL_DATA;
        }
        User user = accountService.getUser(account);
        account.setUser(user);
        accountService.update(account);
        return ResponsePKG.ok();
    }

//    @PostMapping("delete-account")
    public ResponsePKG deleteAccount(@RequestParam String id) {
        accountService.delete(id);
        return ResponsePKG.OK;
    }

    @PostMapping("update-account")
    public ResponsePKG account(@RequestBody Account account) {
        return accountService.update(account) ? ResponsePKG.OK : ResponsePKG.error(-1, "Update Failed!");
    }
}
