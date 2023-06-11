package com.example.mapper;


import com.example.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {



    @Insert("insert into db_account(username, password, email) values(#{username}, #{password}, #{email})")
    int createAccount(String username, String password, String email);

    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Delete("delete from db_account where id = #{id}")
    void deleteAccount(String id);

    @Update("update db_account set username = #{username}, password = #{password}, email = #{email} where id = #{id}")
    boolean updateAccount(String id, String username, String password, String email);

}
