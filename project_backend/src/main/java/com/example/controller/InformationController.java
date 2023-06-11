package com.example.controller;

import com.example.common.R;
import com.example.entity.FraudInformation;
import com.example.mapper.InformationMapper;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class InformationController {

    @Resource
    InformationMapper informationMapper;

    @RequestMapping(value = "/getallinformation", method = RequestMethod.GET)
    public R getAllInformation() {
        return R.success("获取所有诈骗信息", informationMapper.getAllFraudInformation());
    }

    @RequestMapping(value = "/getinformationcount", method = RequestMethod.GET)
    public R getInformationCount() {
        return R.success("获取所有诈骗信息数量", informationMapper.getInformationCount());
    }

    @RequestMapping(value = "/getfraudamount", method = RequestMethod.GET)
    public R getFraudAmount() {
        return R.success("获取总诈骗金额", informationMapper.getFraudAmount());
    }

    @RequestMapping(value = "/getdifferentpersoncount", method = RequestMethod.GET)
    public R getDifferentPersonCount() {
        return R.success("获取所有受骗人数", informationMapper.getDifferentPersonCount());
    }

    @RequestMapping(value = "/addInformation", method = RequestMethod.POST)
    public R addInformation(@RequestBody FraudInformation fraudInformation) {
//        System.out.println(fraudInformation);
        informationMapper.addFraudInformation(fraudInformation);
        return R.success("成功提交");
    }

    @RequestMapping(value = "/deleteInformation", method = RequestMethod.POST)
    public R deleteInformation(@RequestParam("id") String id) {
        System.out.println(id);
        informationMapper.deleteFraudInformation(Integer.parseInt(id));
        return R.success("删除成功");
    }

    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST)
    public R updateInformation(@RequestBody FraudInformation fraudInformation) {
        System.out.println(fraudInformation.toString());
        informationMapper.updateFraudInformation(
                fraudInformation.getId(),
                fraudInformation.getSid(),
                fraudInformation.getSname(),
                fraudInformation.getScollege(),
                fraudInformation.getPhone(),
                fraudInformation.getType(),
                fraudInformation.getAmount(),
                fraudInformation.getFraudTime(),
                fraudInformation.getTime());
        return R.success("更新成功");
    }


}
