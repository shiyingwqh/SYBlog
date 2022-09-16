package com.wuqihang.syblog.controller.admin;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.User;
import com.wuqihang.syblog.security.Token;
import com.wuqihang.syblog.security.TokenManager;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wuqihang
 */
@Controller
@RequestMapping("admin")
public class LoginController {
    private final AccountService accountService;

    private final MessageSource messageSource;
    private final UserService userService;
    private final TokenManager tokenManager;

    public LoginController(AccountService accountService, MessageSource messageSource, UserService userService, TokenManager tokenManager) {
        this.accountService = accountService;
        this.messageSource = messageSource;
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    @RequestMapping("login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (tokenManager.checkToken("", request)) {
            return "redirect:/admin/index";
        }
        if (userService.checkPassword(username, password)) {
            User user = userService.getUser(username);
            Token token = tokenManager.createToken(user);
            Cookie cookie = new Cookie("token", token.getToken());
            cookie.setMaxAge(tokenManager.getMaxAge());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/admin/index";
        }
        String pouerror = messageSource.getMessage("pouerror", null, request.getLocale());
        model.addAttribute("msg", pouerror);
        model.addAttribute("username", username);
        return "/admin/signin";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                break;
            }
        }
        tokenManager.deleteToken(token);
        return "redirect:/admin/signin";
    }

    @PostMapping("signup-account")
    public String signup(@RequestBody Account account, Model model) {
        Account create = accountService.create(account);
        if (accountService.insert(account)) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("username", account.getUser().getUsername());
        model.addAttribute("name", account.getName());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("remarks", account.getRemarks());
        model.addAttribute("tel", account.getTel());
        model.addAttribute("address", account.getAddress());
        return "/admin/signup";
    }
}
