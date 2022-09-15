package com.wuqihang.syblog.controller.admin;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Wuqihang
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    private final AccountService accountService;
    private final BlogService blogService;

    public AdminController(AccountService accountService, BlogService blogService) {
        this.accountService = accountService;
        this.blogService = blogService;
    }

    @RequestMapping("sign")
    public String sign() {
        return "admin/signin";
    }

    @RequestMapping("signup")
    public String signUp() {
        return "admin/signup";
    }

    @RequestMapping({"index", ""})
    public String index(Model model) {
        Account account = accountService.getAllAccount().get(0);
        List<Blog> blogs = blogService.getPageAllBlogs(1);
        model.addAttribute(blogs);
        model.addAttribute(account);
        return "admin/index";
    }

    @RequestMapping("edit")
    public String edit(Model model) {
        model.addAttribute(accountService.getAllAccount().get(0));
        return "admin/edit";
    }
}
