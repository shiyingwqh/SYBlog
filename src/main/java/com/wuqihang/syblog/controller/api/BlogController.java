package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.services.BlogService;
import org.springframework.web.bind.annotation.*;


/**
 * @author Wuqihang
 */
@RestController
@RequestMapping("api")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("upload-blog")
    public ResponsePKG uploadBlog(@RequestBody Blog blog) {
        long time = System.currentTimeMillis();
        blog.setId(String.valueOf(time));
        blog.setLike(0);
        blog.setDislike(0);
        blog.setUpTime(time);
        blog.setModifyTime(time);
        if (blogService.insert(blog)) {
            return ResponsePKG.OK;
        } else {
            return ResponsePKG.error(-1, "Upload Failed!");
        }
    }

    @PostMapping("get-blog")
    public ResponsePKG getBlog(@RequestParam String id) {
        Blog blog = blogService.getBlog(id);
        if (blog == null) {
            return ResponsePKG.error(-1, "");
        }
        return ResponsePKG.returnData(blog);
    }

    @PostMapping("get-blog-list")
    public ResponsePKG getBlogList() {
        return new ResponsePKG(blogService.getAllId());
    }

    @PostMapping("delete-blog")
    public ResponsePKG delete(@RequestParam String id) {
        blogService.delete(id);
        return ResponsePKG.OK;
    }


    @PostMapping("modify-blog")
    public ResponsePKG modifyBlog(@RequestBody Blog newBlog) {
        Blog blog = blogService.getBlog(newBlog.getId());
        if (blog == null) {
            return ResponsePKG.error(-1, "Blog Not Exist!");
        }
        newBlog.setId(blog.getId());
        if (blogService.update(newBlog)) {
            return ResponsePKG.ok("Blog Update Success!");
        }
        return ResponsePKG.error(0, "Blog Update Failed!");
    }
}
