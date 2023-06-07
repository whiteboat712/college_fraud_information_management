package com.example.controller;

import com.example.common.R;
import com.example.entity.FraudInformation;
import com.example.mapper.InformationMapper;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @Resource
    InformationMapper informationMapper;

    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public R getInformation() {
        FraudInformation fraudInformation = informationMapper.getFraudInformation("123456");
        return R.success("诈骗信息获取成功", fraudInformation);
    }

    @RequestMapping(value = "/api/data/getallinformation", method = RequestMethod.GET)
    public R getAllInformation() {
        return R.success("获取所有诈骗信息", informationMapper.getAllFraudInformation());
    }

    @RequestMapping(value = "/api/data/addInformation", method = RequestMethod.POST)
    public R addInformation(@RequestBody FraudInformation fraudInformation) {
        System.out.println(fraudInformation);
        informationMapper.addFraudInformation(fraudInformation);
        return R.success("成功提交");
    }
}
