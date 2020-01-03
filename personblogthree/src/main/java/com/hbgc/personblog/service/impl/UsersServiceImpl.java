package com.hbgc.personblog.service.impl;

import cn.hutool.core.date.DateTime;
import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.repository.UsersRepository;
import com.hbgc.personblog.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl extends BaseServiceImpl<Users,Integer, UsersRepository> implements UsersService {

    @Override
    public Users login(String usersname, String password) {
        Users users = super.dao.findByUsernameAndPassword(usersname, password);
        return users;
    }


    @Override
    public List<Users> findAllByPager(int pageNumber, int pageSize) {

        Page<Users> usersPage = null;
        List<Users> usersList = null;

        //jpa方式
        pageNumber--;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        usersPage = super.dao.findAll(pageRequest);
        usersList = usersPage.getContent();

        return usersList;
    }

    @Override
    public Users findByUsername(String username) {
        return super.dao.findByUsername(username);
    }

    /**
     * 根据用户名更新用户信息
     * @param username
     * @param updateUsername
     * @param gender
     * @param birthday
     * @param profession
     * @param region
     * @param introduction
     * @return
     */
    @Override
    @Transactional
    public Users updateByUsername(String username, String updateUsername, String gender, String birthday, String profession, String region, String introduction) {
//        System.out.println(username+"--"+updateUsername+"--"+gender);
        Date d = new Date();
        DateTime dt = DateTime.of(d);

        //1.更新
        super.dao.updateByUsername(username, updateUsername, gender, birthday, profession, region,introduction,dt.toString("yyyy-MM-dd HH:mm:ss"));
        //2.查询
        Users users = findByUsername(updateUsername);
        return users;
    }


    /**
     * 根据用户id修改头像
     * @param uid
     * @return
     */
    @Override
    public Users updateAvatar(int uid,String avatar) {

        Users users = super.getOne(uid);
        users.setAvatar(avatar);

        super.update(users);

        return super.getOne(uid);
    }

    /**
     * 根据用户id修改用户名
     * @param uid
     * @param username
     * @return
     */
    @Override
    public Users updateUsername(int uid, String username) {
        Users users = super.getOne(uid);
        users.setUsername(username);
        super.update(users);

        return super.getOne(uid);
    }

    /**
     * 根据用户id修改性别
     * @param uid
     * @param gender
     * @return
     */
    @Override
    public Users updateGender(int uid, String gender) {
        Users users = super.getOne(uid);
        users.setGender(gender);
        super.update(users);

        return super.getOne(uid);
    }

    /**
     * 根据用户id修改生日
     * @param uid
     * @param birthday
     * @return
     */
    @Override
    public Users updateBirthday(int uid, String birthday) {
        Users users = super.getOne(uid);
        users.setBirthday(birthday);
        super.update(users);

        return super.getOne(uid);
    }

    /**
     * 根据用户id修改地区
     * @param uid
     * @param region
     * @return
     */
    @Override
    public Users updateRegion(int uid, String region) {
        Users users = super.getOne(uid);
        users.setRegion(region);
        super.update(users);

        return super.getOne(uid);
    }

    /**
     * 根据用户id修改简介
     * @param uid
     * @param introduction
     * @return
     */
    @Override
    public Users updateIntroduction(int uid, String introduction) {
        Users users = super.getOne(uid);
        users.setIntroduction(introduction);
        super.update(users);

        return super.getOne(uid);
    }
}
