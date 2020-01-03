package com.hbgc.personblog.service.impl;

import com.hbgc.personblog.domain.Admin;
import com.hbgc.personblog.repository.AdminRepository;
import com.hbgc.personblog.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin,Integer, AdminRepository> implements AdminService {


    @Override
    public Admin login(String username, String password) {
        return super.dao.findByUsernameAndPassword(username,password);
    }
}
