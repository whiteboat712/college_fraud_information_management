package com.example.mapper;


import com.example.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {



    @Insert("insert into db_account(username, password, email) values(#{username}, #{password}, #{email}, #{type})")
    int createAccount(String username, String password, String email, String type);

    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Select("select * from db_account where id = #{id}")
    Account findAccountById(int id);

    @Select("select * from db_account")
    List<Account>getAllAccounts();

    @Select("select count(*) from db_account")
    Long getAccountsCount();

    @Delete("delete from db_account where id = #{id}")
    void deleteAccount(int id);

    @Update("update db_account set username = #{username}, password = #{password}, email = #{email}, type = #{type} where id = #{id}")
    void updateAccount(int id, String username, String password, String email, String type);

}
