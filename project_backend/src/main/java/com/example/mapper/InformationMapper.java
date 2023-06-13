package com.example.mapper;

import com.example.entity.FraudInformation;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface InformationMapper {

    @Select("select * from fraud_information where sid = #{sid}")
    FraudInformation getFraudInformation(String sid);

    @Select("select * from fraud_information")
    List<FraudInformation> getAllFraudInformation();

    @Insert("insert into fraud_information(sid, sname, scollege, phone, type, amount, fraudTime, time, description)"
            + " values(#{sid}, #{sname}, #{scollege}, #{phone}, #{type}, #{amount}, #{fraudTime}, #{time}, #{description})")
    void addFraudInformation(String sid,
                             String sname,
                             String scollege,
                             String phone,
                             String type,
                             Double amount,
                             Timestamp fraudTime,
                             Timestamp time,
                             String description);

    @Select("select count(*) from fraud_information")
    Long getInformationCount();

    @Select("select count(distinct sid) from fraud_information")
    Long getDifferentPersonCount();

    @Select("select sum(amount) from fraud_information")
    Double getFraudAmount();

    @Delete("delete from fraud_information where id=#{id}")
    void deleteFraudInformation(int id);

    @Update("update fraud_information set sid=#{sid}, sname=#{sname}, scollege=#{scollege}, " +
            "phone=#{phone}, type=#{type}, amount=#{amount}, fraudtime=#{fraudTime}, time=#{time}, description=#{description} where id = #{id}")
    boolean updateFraudInformation(int id,
                                   String sid,
                                   String sname,
                                   String scollege,
                                   String phone,
                                   String type,
                                   Double amount,
                                   Timestamp fraudTime,
                                   Timestamp time,
                                   String description);

}
