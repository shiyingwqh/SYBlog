package com.wuqihang.syblog.controller.admin;

import com.wuqihang.syblog.pojo.Account;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.services.AccountService;
import com.wuqihang.syblog.services.BlogService;
import com.wuqihang.syblog.services.FileService;
import com.wuqihang.syblog.utils.FileUtil;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wuqihang
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    private final AccountService accountService;
    private final BlogService blogService;
    private final FileService fileService;

    public AdminController(AccountService accountService, BlogService blogService, FileService fileService) {
        this.accountService = accountService;
        this.blogService = blogService;
        this.fileService = fileService;
    }

    @RequestMapping({"index", ""})
    public String index(Model model) {
        Account account = accountService.getAllAccount().get(0);
        List<Blog> blogs = blogService.getPageAllBlogs(1);
        model.addAttribute(blogs);
        model.addAttribute(account);
        return "admin/index";
    }

    @RequestMapping("list")
    public String list(@RequestParam @Nullable Integer page, Model model) {
        if (page == null) {
            page = 1;
        }
        List<Blog> pageAllBlogs = blogService.getPageAllBlogs(page);
        model.addAttribute(accountService.getAllAccount().get(0));
        model.addAttribute(pageAllBlogs);
        model.addAttribute("page", page);
        model.addAttribute("maxPage", blogService.getMaxPage());
        return "admin/list";
    }

    @RequestMapping("edit")
    public String edit(Model model) {
        model.addAttribute(accountService.getAllAccount().get(0));
        return "admin/edit";
    }

    @RequestMapping("modify")
    public String modify(@RequestParam String id, Model model, HttpServletResponse response) throws IOException {
        Blog blog = blogService.getBlog(id);
        if (blog == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        model.addAttribute(accountService.getAllAccount().get(0));
        model.addAttribute(blog);
        return "admin/modify";
    }

    @RequestMapping("file")
    public String Image(Model model) {
        List<String> filePaths = fileService.getAllFilePath();
        model.addAttribute(accountService.getAllAccount().get(0));
        model.addAttribute("filePaths", filePaths);
        return "admin/file";
    }

    @RequestMapping("setting")
    public String setting(Model model) {
        Account account = accountService.getAllAccount().get(0);
        model.addAttribute(account);
        return "admin/setting";
    }
    @Data
    class Res{
        private int success = 0;
        private String message = "Error";
        private String url = null;
    }
    @PostMapping("upload-img")
    @ResponseBody
    public Res uploadImg(@RequestParam("editormd-image-file") MultipartFile file, FileUtil fileUtil) {
        Res res = new Res();
        try {
            if (!file.isEmpty()) {
                InputStream in = file.getInputStream();
                File uploadImg = fileUtil.uploadImg(file.getOriginalFilename(), in);
                in.close();
                if (uploadImg != null && uploadImg.exists()){
                    res.success = 1;
                    res.message = "OK";
                    res.url = "/static/img/" + file.getOriginalFilename();
                }
            }
        } catch (IOException ignored) {

        }
        return res;
    }
}
