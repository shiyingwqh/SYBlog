package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Wuqihang
 */
@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO COMMENTS (BLOGID, NAME, TEXT, TIME) VALUES (#{blogId}, #{name}, #{text}, #{time})")
    int insert(Comment comment);
//    void delete(String id);
//    void update(Comment comment);
//    Comment query(String id);
//    List<Comment> queryAll();
}
