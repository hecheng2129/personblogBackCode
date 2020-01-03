package com.hbgc.personblog.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户表
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class Users extends BaseEntity implements Serializable,Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    @Column(length = 1)
    private String gender;          //性别
    private String username;        //用户名
    private String password;        //密码
    private String birthday;        //出生日期
    private String avatar;          //头像
    private String validateCode;    //验证码
    private String telephone;       //电话
    private String email;           //电子邮箱
    private String region;          //地区
    private String profession;      //行业
    private String introduction;    //简介
}
