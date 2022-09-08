package com.wuqihang.syblog.mapper;

import com.wuqihang.syblog.pojo.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author Wuqihang
 */
@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM ACCOUNT WHERE ID = #{id}")
    @Result(column = "id", property = "user", one = @One(select = "com.wuqihang.syblog.mapper.UserMapper.getUserById"))
    Account getAccountById(String id);

    @Select("SELECT * FROM ACCOUNT JOIN AUSER A on A.ID = A.ID and A.USERNAME = #{username}")
    @Result(column = "username", property = "user", one = @One(select = "com.wuqihang.syblog.mapper.UserMapper.getUserByUsername"))
    Account getAccountByUsername(String username);

    @Update("UPDATE ACCOUNT SET ADDRESS = #{address}, NAME = #{name}, TEL = #{tel}, REMARKS = #{remarks} WHERE ID = #{user.id}")
    void updateAccount(Account account);

    @Delete("DELETE FROM ACCOUNT WHERE ID = #{id}")
    void deleteAccount(String id);

    @Insert("INSERT INTO ACCOUNT (ID, NAME, TEL, ADDRESS, EMAIL, REMARKS) " +
            "VALUES (#{user.id}, #{name}, #{tel}, #{address}, #{email}, #{remarks})")
    void insertAccount(Account account);
}
