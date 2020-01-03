package com.hbgc.personblog.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.util.MyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("commons")
public class CommonsController extends BaseController{

    @Value("${upload.win.path}")
    private String uploadWinPath;

    @Value("${upload.linux.path}")
    private String  uploadLinuxPath;

    //给注册手机发送验证码
    @GetMapping("mrcode")
    public Map<String, Object> sendMobileRandomCode(String telephone) {
        String randomCode = null;
        try {
            randomCode = RandomUtil.randomNumbers(6);
            //把这个验证码保存到session;做app这样是行不通的。
            String token = UUID.fastUUID().toString(true);
            MyUtils.sendMessageCodeByAliyun(randomCode, telephone);

            //把验证码保存到redis中,默认半个小时内有效。
            redisTemplate.opsForValue().set(token, randomCode, 5, TimeUnit.MINUTES);

            return Json.success(token, "验证码发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("验证码发送失败！");
        }

    }

    //注册时给注册邮箱发送验证码动作
    @GetMapping("ercode")
    public Map<String, Object> sendEmailValidateCode(String toEmail, HttpServletRequest request, HttpSession session) {
        System.out.println("发送邮箱是：" + toEmail);
        try {
            String randomCode = RandomUtil.randomNumbers(6);
            MyUtils.sendEmailValidateCode(toEmail, randomCode);
            String token = UUID.fastUUID().toString(true);
            //把验证码保存到redis中,默认半个小时内有效。
            redisTemplate.opsForValue().set(token, randomCode, 5, TimeUnit.MINUTES);
            //使用了redis保存token，就不要再使用session保存了。
            System.out.println("本次生成的验证码是：" + randomCode);

            return Json.success(token, "验证码发送成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            return Json.fail("验证码发送失败！");
        }
    }

    //如果是使用ElementUI的upload组件要求必须是post请求实现上传
    @PostMapping("upload")
    public Map<String, Object> loadFiles(MultipartFile file, HttpServletRequest request) {
        System.out.println("执行上传...");
        //Map<String, Object> map = new HashMap<String, Object>();
        //获得上传的路径
        //String path = request.getSession().getServletContext().getRealPath("/upload");

        String path = null;
        //先判断一下当前是什么操作

        if("windows".equals(MyUtils.getOperateSysName())){
            path = this.uploadWinPath;
        }else{
            path = this.uploadLinuxPath;
        }

        try {
            String uploadSuccessFileName = MyUtils.uploadFile(file, path);
            return Json.success(uploadSuccessFileName, "文件上传成功！");
        } catch (Exception ex) {
            ex.printStackTrace();
            return Json.fail("文件上传失败！");
        }
    }

}
