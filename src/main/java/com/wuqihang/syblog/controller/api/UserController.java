package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("update-user")
    public ResponsePKG updateUser(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String password) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponsePKG.error(-1, "User Not Found!");
        }
        if (!userService.checkPassword(username, oldPassword)) return ResponsePKG.error(-1, "Password is Wrong");
        user.setPassword(password);
        userService.update(user);
        return ResponsePKG.ok();
    }

    @PostMapping("create-user")
    public ResponsePKG createUser(@RequestBody User user) {
        if (userService.exist(user)) return ResponsePKG.error(-1, "User Exist!");
        userService.create(user);
        return ResponsePKG.returnData("OK", user.getId());
    }
}
