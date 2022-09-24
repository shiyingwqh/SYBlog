package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wuqihang
 */
@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO COMMENTS (BLOGID, NAME, TEXT, TIME) VALUES (#{blogId}, #{name}, #{text}, #{time})")
    int insert(Comment comment);

    @Delete("DELETE FROM COMMENTS WHERE ID = #{id}")
    void delete(int id);

    @Update("UPDATE COMMENTS SET NAME = #{name}, TEXT = #{text}, TIME = #{time}, BLOGID = #{blogId} WHERE ID = #{id}")
    void update(Comment comment);

    @Select("SELECT * FROM COMMENTS WHERE ID = #{id}")
    Comment query(String id);

    @Select("SELECT * FROM COMMENTS WHERE BLOGID = #{id}")
    List<Comment> queryByBlogId(String id);

    @Select("SELECT * FROM COMMENTS")
    List<Comment> queryAll();

    @Select("SELECT ID FROM COMMENTS ORDER BY TIME DESC")
    List<Integer> queryAllId();
}
