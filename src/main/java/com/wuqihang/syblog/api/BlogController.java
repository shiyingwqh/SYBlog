package com.wuqihang.syblog.api;

import com.wuqihang.syblog.mapper.BlogMapper;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("upload-blog")
    public ResponsePKG uploadBlog(@RequestBody String text, @RequestParam String title, @Nullable @RequestParam String token, HttpServletRequest request) {
        if (tokenManager.checkToken(token, request)) return ResponsePKG.error(-1, "Token Invalid!");
        long time = System.currentTimeMillis();
        Blog blog = new Blog(String.valueOf(time), text, title, 0, 0, time, time);
        if (blogMapper.insert(blog)) {
            return ResponsePKG.ok("Upload Success!");
        } else {
            return ResponsePKG.error(-1, "Upload Failed!");
        }
    }

    @PostMapping("get-blog")
    public ResponsePKG getBlog(@RequestParam String id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return ResponsePKG.error(-1, "");
        }
        return ResponsePKG.returnData("", blog);
    }

    @PostMapping("get-blog-list")
    public ResponsePKG getBlogList() {
        return new ResponsePKG(blogMapper.getAllId());
    }

    @PostMapping("delete-blog")
    public ResponsePKG delete(@RequestParam String id, @RequestParam String token, HttpServletRequest request) {
        if (tokenManager.checkToken(token, request)) return ResponsePKG.error(-1, "Token Invalid!");
        blogMapper.delete(id);
        return ResponsePKG.ok("Delete Success!");
    }


    @PostMapping("modify-blog")
    public ResponsePKG modifyBlog(@RequestParam String id, @RequestParam String token, @RequestBody String text, @Nullable @RequestParam String title, HttpServletRequest request) {
        if (tokenManager.checkToken(token, request)) return ResponsePKG.error(-1, "Token Invalid!");
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return ResponsePKG.error(-1, "Blog Not Exist!");
        }
        blog.setText(text);
        blog.setTitle(title == null ? blog.getTitle() : title);
        blog.setModifyTime(System.currentTimeMillis());
        if (blogMapper.update(blog)) {
            return ResponsePKG.ok("Blog Update Success!");
        }
        return ResponsePKG.error(0, "Blog Update Failed!");
    }
}
