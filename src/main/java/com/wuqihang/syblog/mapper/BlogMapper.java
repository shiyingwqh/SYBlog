package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.Blog;
import com.wuqihang.syblog.pojo.User;
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
    void insert(Blog blog);

    @Update("UPDATE BLOG SET TEXT = #{text},TITLE = #{title} ,`LIKE` = #{like}, DISLIKE = #{dislike},MODIFYTIME = #{modifyTime} WHERE id = #{id}")
    void update(Blog blog);

    @Select("SELECT * FROM BLOG ORDER BY MODIFYTIME DESC LIMIT #{len} OFFSET #{offset}")
    List<Blog> getBlogsLimit(int offset, int len);

    @Select("SELECT ID FROM BLOG ORDER BY MODIFYTIME DESC LIMIT #{len} OFFSET #{offset}")
    List<String> getBlogsID(int offset, int len);
}
