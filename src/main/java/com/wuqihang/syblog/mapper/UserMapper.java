package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wuqihang
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM AUSER WHERE ID = #{id}")
    User getUserById(String id);

    @Select("SELECT * FROM AUSER")
    List<User> getAllUsers();

    @Update("UPDATE AUSER SET PASSWORD = #{password} WHERE ID = #{id}")
    void update(User user);

    @Delete("DELETE FROM AUSER WHERE ID = #{id}")
    void delete(String id);

    @Insert("INSERT INTO AUSER (ID, USERNAME, PASSWORD) VALUES ( #{id}, #{username}, #{password})")
    boolean insert(User user);

    @Select("SELECT * FROM AUSER WHERE USERNAME = #{username} AND PASSWORD = #{password}")
    User getUserByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM AUSER WHERE USERNAME = #{username}")
    User getUserByUsername(String username);

}
