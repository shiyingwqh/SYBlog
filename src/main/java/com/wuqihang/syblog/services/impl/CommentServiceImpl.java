package com.wuqihang.syblog.services.impl;

import com.wuqihang.syblog.mapper.CommentMapper;
import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.Comment;
import com.wuqihang.syblog.services.CommentService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author WuQihang
 */
@Component
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int addComment(String blogId, Comment comment) {
        if (blogId == null){
            return -1;
        }
        comment.setBlogId(blogId);
        try {
            return commentMapper.insert(comment);
        }catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<Comment> getComments(Blog blog) {
        return commentMapper.queryByBlogId(blog.getId());
    }

    @Override
    public List<Comment> getComments(String blogId) {
        return commentMapper.queryAll();
    }

    @Override
    public void deleteComment(int id) {
        commentMapper.delete(id);
    }

    @Override
    public boolean updateComment(Comment comment) {
        try {
            commentMapper.update(comment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Integer> getAllId() {
        return commentMapper.queryAllId();
    }

    @Override
    public List<Comment> getAllComments() {
        return commentMapper.queryAll();
    }
}
