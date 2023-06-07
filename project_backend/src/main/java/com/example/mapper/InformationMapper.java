package com.example.mapper;

import com.example.entity.FraudInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InformationMapper {

    @Select("select * from fraud_information where sid = #{sid}")
    FraudInformation getFraudInformation(String sid);
}
