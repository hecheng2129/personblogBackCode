package com.hbgc.personblog.controller;

import com.hbgc.personblog.domain.Admin;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.AdminService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("login")
    public Map<String,Object> login(@Param("username") String username,@Param("password") String password){
        Admin admin=null;
        try {
            admin = adminService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("登录失败!");
        }
        return Json.success(admin,"登录成功！");
    }
}
