package com.hbgc.personblog.controller;

import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("users")
public class UsersController extends BaseController {

    @Resource
    private UsersService usersService;

    @Value("${pageSize}")
    private int pageSize;

    /**
     * 分页查询
     *
     * @param pageNumber
     * @return
     */
    @GetMapping("selectPage/{pageNumber}")
    public Map<String, Object> queryUsersByPage(@PathVariable("pageNumber") int pageNumber) {

        List<Users> userList = null;
        try {
            String key = "userList-" + pageNumber;
            if (redisTemplate.opsForValue().get(key) == null) {
                System.out.println("从mysql中查找");
                userList = usersService.findAllByPager(pageNumber, pageSize);
                redisTemplate.opsForValue().set(key, userList, 100, TimeUnit.MINUTES);
            } else {
                userList = (List<Users>) redisTemplate.opsForValue().get(key);
            }

            if (userList != null) {
                return Json.success(userList, "查询成功！");
            }
            return Json.fail("没有次条记录!");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败!");
        }
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public Map<String, Object> login(@Param("username") String username, @Param("password") String password) {
        Users user = usersService.login(username, password);

        try {
            if (user == null) {
                return Json.fail("登录失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("登录失败!");
        }
        return Json.success(user, "登录成功！");
    }

    @PostMapping("register")
    public Map<String, Object> reg(String token, @RequestBody Users user) {
        System.out.println("接收到的用户数据" + user);
        try {
            //1.检验验证码为空
            if (user.getValidateCode() == null) {
                return Json.fail("请输入验证码！");
            }
            //2.检查验证码是否正确
            if (!checkValidateCode(token, user.getValidateCode())) {
                return Json.fail("验证码不正确！");
            }
            //3.判断用户名是否存在
            Users users = usersService.findByUsername(user.getUsername());
            if (users != null) {
                return Json.fail("用户名已存在!");
            }

            //4.给新用户设置一个头像
            String url = "http://personblog.starstarcheng.cn/images/avatar/avatar.jpg";
            user.setAvatar(url);

            user.setGender("1");
            usersService.save(user);
            return Json.success(null, "注册成功！");
        } catch (Exception ex) {
            ex.printStackTrace();
            return Json.fail("注册失败！");
        }
    }

    /**
     * 更新全部（不包括头像）
     * @param username
     * @param users
     * @return
     */
    @PatchMapping("update")
    public Map<String, Object> update(@Param("username") String username,@RequestBody Users users) {
        System.out.println("要修改的用户："+username);
        System.out.println(users);
        Users users1=null;
        try {
//         users1 = usersService.updateByUsername(username,usersupdateUsername,gender,birthday,profession,region,introduction);
           users1 = usersService.updateByUsername(username,users.getUsername(),users.getGender(),users.getBirthday().substring(0,10),users.getProfession(),users.getRegion(),users.getIntroduction());
           System.out.println(users1);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users1,"更新成功！");
    }

    /**
     * 更新头像
     * @param uid
     * @param imageUrl
     * @return
     */
    @PatchMapping("updateAvatar")
    public Map<String, Object> updateAvatar(@Param("uid") int uid,@Param("imageUrl") String imageUrl) {
        System.out.println("要修改的用户："+uid);
        Users users=null;
        try {
            users = usersService.updateAvatar(uid,imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }

    /**
     * 更新用户名
     * @return
     */
    @PatchMapping("updateUsername")
    public Map<String,Object> updateUsername(@Param("uid") int uid,@Param("username") String username){
        Users users=null;
        try {
            users = usersService.updateUsername(uid,username);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }

    /**
     * 更新性别
     * @param uid
     * @param gender
     * @return
     */
    @PatchMapping("updateGender")
    public Map<String,Object> updateGender(@Param("uid") int uid,@Param("gender") String gender){
//        System.out.println(uid+":"+gender);
        Users users=null;
        try {
            users = usersService.updateGender(uid,gender);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }

    /**
     * 更新生日
     * @param uid
     * @param birthday
     * @return
     */
    @PatchMapping("updateBirthday")
    public Map<String,Object> updateBirthday(@Param("uid") int uid,@Param("birthday") String birthday){
        Users users=null;
        try {
            users = usersService.updateBirthday(uid,birthday);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }

    /**
     * 更新地区
     * @param uid
     * @param region
     * @return
     */
    @PatchMapping("updateRegion")
    public Map<String,Object> updateRegion(@Param("uid") int uid,@Param("region") String region){
        Users users=null;
        try {
            users = usersService.updateRegion(uid,region);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }


    /**
     * 更新简介
     * @param uid
     * @param introduction
     * @return
     */
    @PatchMapping("updateIntroduction")
    public Map<String,Object> updateIntroduction(@Param("uid") int uid,@Param("introduction") String introduction){
//        System.out.println(uid+":"+gender);
        Users users=null;
        try {
            users = usersService.updateIntroduction(uid,introduction);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("更新失败！");
        }
        return Json.success(users,"更新成功！");
    }

    @GetMapping("getAllUsers")
    public Map<String,Object> getAllUsers(){
        List<Users> usersList = null;
        try {
            usersList =  usersService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
          return Json.fail("查询失败！");
        }
        return Json.success(usersList,"查询成功！");
    }
}



