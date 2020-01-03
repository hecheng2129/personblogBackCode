package com.hbgc.personblog.repository;

import com.hbgc.personblog.domain.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends BaseRepository<Admin,Integer> {

    /**
     * 登录
     *
     */
    public Admin findByUsernameAndPassword(String username, String password);
}
