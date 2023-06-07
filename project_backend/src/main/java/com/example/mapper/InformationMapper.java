package com.example.mapper;

import com.example.entity.FraudInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InformationMapper {

    @Select("select * from fraud_information where sid = #{sid}")
    FraudInformation getFraudInformation(String sid);

    @Select("select * from fraud_information")
    List<FraudInformation> getAllFraudInformation();

    @Insert("insert into fraud_information(id,sid,sname,scollege,phone,type,amount,fraudTime,time)"
            + " values(#{id},#{sid},#{sname},#{scollege},#{phone},#{type},#{amount},#{fraudTime},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addFraudInformation(FraudInformation fraudInformation);

}
