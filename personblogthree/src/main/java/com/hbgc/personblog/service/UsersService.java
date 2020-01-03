package com.hbgc.personblog.service;

import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.repository.UsersRepository;

import java.util.List;

public interface UsersService extends BaseService<Users,Integer, UsersRepository>{

    //用户登录
    Users login(String usersanme, String password);

    //分页查询
    List<Users> findAllByPager(int pageNumber, int pageSize);

    //根据用户名查找用户
    Users findByUsername(String username);

    //根据用户名更新用户信息
    Users updateByUsername(String username, String updateUsername, String gender, String birthday, String profession, String region, String introduction);

    //根据id更新用户头像
    Users updateAvatar(int uid, String avatar);

    //根据id更新用户名
    Users updateUsername(int uid, String username);

    //根据id更新性别
    Users updateGender(int uid, String gender);

    //根据id更新生日
    Users updateBirthday(int uid, String birthday);

    //根据id更新地区
    Users updateRegion(int uid, String region);

    //根据id更新简介
    Users updateIntroduction(int uid, String introduction);
}
