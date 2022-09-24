package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.Token;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api/token")
public class TokenController {
    private final UserService userService;
    private final TokenManager tokenManager;

    public TokenController(UserService userService, TokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("get-token")
    public ResponsePKG getToken(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponsePKG.error(-1, "User Not Found!");
        }
        if (!userService.checkPassword(username, password)) return ResponsePKG.error(-1, "Password is Wrong");
        Token token = tokenManager.createToken(user);
        return ResponsePKG.returnData("Success!", token);
    }

    @PostMapping("check-token")
    public ResponsePKG checkToken(@RequestParam String token) {
        if (tokenManager.checkToken(token)) {
            return ResponsePKG.ok();
        }
        return ResponsePKG.error(-1, "Token Invalid!");
    }

    @PostMapping("delete-token")
    public ResponsePKG deleteToken(@RequestParam String token) {
        tokenManager.deleteToken(token);
        return ResponsePKG.ok();
    }
}
