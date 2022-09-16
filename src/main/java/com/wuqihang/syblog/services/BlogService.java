package com.wuqihang.syblog.services;

import com.wuqihang.syblog.pojo.Blog;

import java.util.List;

/**
 * @author Wuqihang
 */
public interface BlogService {
    Blog getBlog(String id);

    List<Blog> getAllBlog(String blog);

    boolean insert(Blog blog);

    boolean exist(String id);

    void delete(Blog blog);

    boolean update(Blog blog);

    void delete(String id);

    List<String> getAllId();

    List<Blog> getAllBlog();

    List<String> getPageAllId(int page);

    List<Blog> getPageAllBlogs(int page);

    int getMaxPage();
}
