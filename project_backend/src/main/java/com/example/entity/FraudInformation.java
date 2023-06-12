package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class FraudInformation {
    int id;
    String sid;
    String sname;
    String scollege;
    String phone;
    String type;
    Double amount;
    Timestamp fraudTime;
    Timestamp time;
    String description;
}
