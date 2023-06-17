package com.example.controller;

import com.example.common.R;
import com.example.entity.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Resource
    AuthorizeService service;
    @Resource
    UserMapper mapper;

    @PostMapping("/register")
    public R registerUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email,
                          @RequestParam("type") String type) {
        if (service.validateAndRegister(username, password, email, type)) {
            return R.success("注册成功");
        } else {
            return R.error("注册失败");
        }
    }

    @PostMapping("/change-password")
    public R changePassword(@RequestParam("password") String oldPassword,
                            @RequestParam("newPassword") String newPassword,
                            @RequestParam("id") int id) {
        System.out.println(id + " " + oldPassword + " " + newPassword);
        Account account = mapper.findAccountById(id);
        if (account == null) {
            return R.fatal("Account not found");
        } else {
            if (!encoder.matches(oldPassword, account.getPassword())) {
                return R.fatal("密码错误");
            } else {
                mapper.updateAccount(account.getId(), account.getUsername(), encoder.encode(newPassword), account.getEmail(), account.getType());
                return R.success("修改密码成功", encoder.encode(newPassword));
            }
        }
    }

    @PostMapping("/change-username")
    public R changeUsername(@RequestParam("username") String newUsernName,
                            @RequestParam("id") int id) {
        Account account = mapper.findAccountById(id);
        if (account == null) {
            return R.fatal("Account not found");
        } else {
            mapper.updateAccount(account.getId(), newUsernName, account.getPassword(), account.getEmail(), account.getType());
            return R.success("修改用户名成功");
        }
    }

    @PostMapping("/change-email")
    public R changeEmail(@RequestParam("email") String newEmail,
                         @RequestParam("id") int id) {
        Account account = mapper.findAccountById(id);
        if (account == null) {
            return R.fatal("Account not found");
        } else {
            mapper.updateAccount(account.getId(), account.getUsername(), account.getPassword(), newEmail, account.getType());
            return R.success("修改邮箱成功");
        }
    }


}
