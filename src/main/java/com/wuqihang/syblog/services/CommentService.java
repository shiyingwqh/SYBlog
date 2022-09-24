package com.wuqihang.syblog.services;

import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Blog blog, Comment comment);
    List<Comment> getComments(Blog blog);
    List<Comment> getComments(String blogId);
    boolean deleteComment(String id);
    boolean updateComment(String id);
}
