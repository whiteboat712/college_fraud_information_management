package com.example.controller;


import com.example.common.R;
import com.example.entity.Account;
import com.example.entity.FraudInformation;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class AccountController {
    @Resource
    UserMapper accountMapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
    public R getAllAccounts() {
        return R.success("获取所有账户信息", accountMapper.getAllAccounts());
    }

    @RequestMapping(value = "/getAccountByUsername/{username}", method = RequestMethod.GET)
    public R getAccountByUsername(@PathVariable("username") String username) {
//        System.out.println(username);
        try {
            Account account = accountMapper.findAccountByNameOrEmail(username);
            return R.success("存在", account);
        } catch (Exception e) {
            return R.success("不存在");
        }
    }

    @RequestMapping(value = "getAllAccountsCount", method = RequestMethod.GET)
    public R getAllAccountCount() {
        return R.success("", accountMapper.getAccountsCount());
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public R deleteAccount(@RequestBody Account account) {
        accountMapper.deleteAccount(account.getId());
        return R.success("删除账户成功");
    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public R updateAccount(@RequestBody Account account) {
        accountMapper.updateAccount(account.getId(), account.getUsername(), encoder.encode(account.getPassword()), account.getEmail(), account.getType());
        return R.success("编辑账户成功");
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public R addAccount(@RequestBody Account account)  {
        accountMapper.createAccount(account.getUsername(), encoder.encode(account.getPassword()), account.getEmail(), account.getType());
        return R.success("添加账户成功");
    }

    @RequestMapping(value = "batchAddAccount", method = RequestMethod.POST)
    public R batchAddAccount(@RequestBody List<Account> accounts) {
        for (Account account : accounts) {
            accountMapper.createAccount(
                    account.getUsername(),
                    encoder.encode(account.getPassword()),
                    account.getEmail(),
                    account.getType()
            );
        }
        return R.success("添加成功");
    }
}
