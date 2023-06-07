package com.example.controller;

import com.example.common.R;
import com.example.entity.FraudInformation;
import com.example.mapper.InformationMapper;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Resource
    InformationMapper informationMapper;

    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public R getInformation() {
        FraudInformation fraudInformation = informationMapper.getFraudInformation("123456");
        System.out.println(fraudInformation);
        return R.success("诈骗信息获取成功", fraudInformation);
    }
}
