package com.hbgc.personblog.repository;

import com.hbgc.personblog.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends BaseRepository<Users,Integer> {     //第二个参数为主键的包装类型

    /**
     * 用户登录
     *
     * 注意findByUsernameAndPassword的拼写要严格按照 驼峰标准
     *
     * @param username
     * @param password
     * @return
     */
    Users findByUsernameAndPassword(String username, String password);


    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Users> findAll(Pageable pageable);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Users findByUsername(String username);

    /**
     * 根据用户名更新用户信息
     * @return
     */
    @Modifying
    @Query("update Users set username = ?2,gender=?3,birthday=?4,profession=?5,region=?6,introduction=?7,modifyTime=?8 where username = ?1")
    int updateByUsername(String username, String updateUsername, String gender, String birthday, String profession, String region, String introduction, String modifyTime);
}
