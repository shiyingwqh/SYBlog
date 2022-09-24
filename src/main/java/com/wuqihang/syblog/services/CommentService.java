package com.wuqihang.syblog.services;

import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addComment(String blogId, Comment comment);
    List<Comment> getComments(Blog blog);
    List<Comment> getComments(String blogId);
    void deleteComment(int id);
    boolean updateComment(Comment comment);
    
    List<Integer> getAllId();

    List<Comment> getAllComments();
}
