package com.wuqihang.syblog.controller;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wuqihang
 */
@Controller
public class MainController {
    private final AccountService accountService;
    private final BlogService blogService;

    public MainController(AccountService accountService, BlogService blogService) {
        this.accountService = accountService;
        this.blogService = blogService;
    }

    @GetMapping("/main/{username}")
    String getId(@PathVariable("username") String username, HttpServletResponse response, Model model) throws IOException {
        Account account = accountService.getAccountByUsername(username);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("account",account);
        return "index";
    }

    @GetMapping("/main/{username}/blog")
    String userBlog(@PathVariable("username") String username, String page, HttpServletResponse response, Model model) throws Exception{
        Account account = accountService.getAccountByUsername(username);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("account",account);
        int i;
        try {
            i = Integer.parseInt(page);
        }catch (Exception e){
            i = 1;
        }
        List<Blog> blogList = blogService.getPageAllBlogs(i);
        model.addAttribute(blogList);
        return "blog";
    }
}
