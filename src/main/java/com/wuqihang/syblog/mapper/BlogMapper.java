package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wuqihang
 */
@Component
@Mapper
public interface BlogMapper {
    @Select("SELECT * FROM blog where id = #{id}")
    Blog getBlogById(String id);

    @Select("SELECT * FROM BLOG")
    List<Blog> getAllBlog();

    @Select("SELECT id FROM BLOG")
    List<String> getAllId();

    @Delete("DELETE FROM BLOG WHERE id = ${id}")
    void delete(String id);

    @Insert("INSERT INTO BLOG (ID, TEXT, TITLE, `LIKE`, DISLIKE, UPTIME, MODIFYTIME)" +
            " VALUES (#{id}, #{text},#{title} ,#{like}, #{dislike}, #{upTime}, #{modifyTime})")
    boolean insert(Blog blog);

    @Update("UPDATE BLOG SET TEXT = #{text},TITLE = #{title} ,`LIKE` = #{like}, DISLIKE = #{dislike},MODIFYTIME = #{modifyTime} WHERE id = #{id}")
    boolean update(Blog blog);
}
