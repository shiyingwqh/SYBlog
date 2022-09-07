package com.wuqihang.syblog.api;

import com.wuqihang.syblog.mapper.BlogMapper;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.ResponsePKG;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;

    @PostMapping("upload-blog")
    public ResponsePKG uploadBlog(@RequestBody String text, @RequestParam String token) {
        long time = System.currentTimeMillis();
        Blog blog = new Blog(String.valueOf(time), text, 0, 0, time, time);
        if (blogMapper.insert(blog)) {
            return new ResponsePKG(200, "Upload Success!", null);
        } else {
            return new ResponsePKG(-1, "Upload Failed!", null);
        }
    }

    @PostMapping("get-blog")
    public ResponsePKG getBlog(@RequestParam String id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return new ResponsePKG(-1, null, null);
        }
        return new ResponsePKG(200, null, blog);
    }

    @RequestMapping("get-blog-list")
    public ResponsePKG getBlogList() {
        return new ResponsePKG(blogMapper.getAllId());
    }

    @RequestMapping("delete-blog")
    public ResponsePKG delete(@RequestParam String id, @RequestParam String token) {
        blogMapper.delete(id);
        return new ResponsePKG(200, "Delete Success!", null);
    }

    @RequestMapping("modify-blog")
    public ResponsePKG modifyBlog(@RequestParam String id, @RequestParam String token, @RequestBody String text) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return new ResponsePKG(-1, "Blog Not Exist!", null);
        }
        blog.setText(text);
        blog.setModifyTime(System.currentTimeMillis());
        if (blogMapper.update(blog)) {
            return new ResponsePKG(200, "Blog Update Success!", null);
        }
        return new ResponsePKG(0, "Blog Update Failed!", null);
    }
}
