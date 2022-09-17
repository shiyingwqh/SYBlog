package com.wuqihang.syblog.controller;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping({"/", "/index"})
    String getId(HttpServletResponse response, Model model) throws IOException {
        List<Account> allAccount = accountService.getAllAccount();
        Account account = allAccount.get(0);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute("account", account);
        return "index";
    }

    @GetMapping("/blogs")
    String blogs(@Nullable @RequestParam Integer page, HttpServletResponse response, Model model) throws Exception {
        if (page == null) {
            page = 1;
        }
        if (page <= 0 || page > blogService.getMaxPage()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        List<Blog> blogList = blogService.getPageAllBlogs(page);
        model.addAttribute("page", page);
        model.addAttribute("maxPage", blogService.getMaxPage());
        model.addAttribute(blogList);
        return "blogs";
    }

    @RequestMapping("/blogs/{id}")
    public String blog(@PathVariable("id") String id, HttpServletResponse response, Model model) throws IOException {
        Blog blog = blogService.getBlog(id);
        Account account = accountService.getAllAccount().get(0);
        model.addAttribute("account", account);
        if (blog == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute(blog);
        return "blog";
    }
}
