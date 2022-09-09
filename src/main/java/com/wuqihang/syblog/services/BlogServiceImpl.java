package com.wuqihang.syblog.services;

import com.wuqihang.syblog.config.SYConfiguration;
import com.wuqihang.syblog.mapper.BlogMapper;
import com.wuqihang.syblog.pojo.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wuqihang
 */
@Component
@EnableConfigurationProperties(SYConfiguration.class)
public class BlogServiceImpl implements BlogService {
    private final BlogMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);
    @Value("#{SYConfiguration.numOnePage}")
    private int numOnePage;

    public BlogServiceImpl(BlogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Blog getBlog(String id) {
        return mapper.getBlogById(id);
    }

    @Override
    public List<Blog> getAllBlog(String blog) {
        return mapper.getAllBlog();
    }

    @Override
    public boolean insert(Blog blog) {
        try {
            mapper.insert(blog);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean exist(String id) {
        return mapper.getBlogById(id) != null;
    }

    @Override
    public void delete(Blog blog) {
        delete(blog.getId());
    }

    @Override
    public boolean update(Blog blog) {
        try {
            mapper.insert(blog);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    @Override
    public void delete(String id) {
        mapper.delete(id);
    }

    @Override
    public List<String> getAllId() {
        return mapper.getAllId();
    }

    @Override
    public List<Blog> getAllBlog() {
        return mapper.getAllBlog();
    }

    @Override
    public List<String> getPageAllId(int page) {
        int start = (page - 1) * numOnePage;
        List<String> allBlog = getAllId();
        String[] blogs = (String[]) Arrays.copyOfRange(allBlog.toArray(), start, page + numOnePage);
        return Arrays.asList(blogs);
    }
}
