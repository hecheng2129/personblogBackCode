package com.hbgc.personblog.service;

import com.hbgc.personblog.domain.Admin;
import com.hbgc.personblog.repository.AdminRepository;

public interface AdminService extends BaseService<Admin,Integer, AdminRepository> {
    /**
     * 用户登录
     * @return
     */
    public Admin login(String username, String password);
}
