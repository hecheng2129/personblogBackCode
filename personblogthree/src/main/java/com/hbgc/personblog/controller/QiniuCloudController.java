package com.hbgc.personblog.controller;

import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.UsersService;
import com.hbgc.personblog.util.MyUtils;
import com.hbgc.personblog.util.QiniuCloudUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("qiniuCloud")
public class QiniuCloudController {

    @Resource
    private UsersService usersService;

    @Value("${upload.win.path}")
    private String uploadWinPath;

    @Value("${upload.linux.path}")
    private String  uploadLinuxPath;

    @PostMapping("uploadImg")
    public Map<String,Object> uploadImg(MultipartFile file, HttpServletRequest request) {
        System.out.println("执行上传...");
        if (file.isEmpty()) {
            return Json.fail("null");
        }
        try {
            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID().toString();
            String url=null;
            try {
                //使用base64方式上传到七牛云
                url = QiniuCloudUtil.put64image(bytes, imageName);
               /* String name = file.getName();
                System.out.println("上传的名字："+name);
                String originalFilename = file.getOriginalFilename();
                System.out.println("originalFilename:"+originalFilename);


                String requestURI = request.getRequestURI();
                System.out.println("requestURI:"+requestURI);
                String servletPath = request.getServletPath();
                System.out.println("servletPath:"+servletPath);*/

//                QiniuCloudUtil.upload(,originalFilename);

                System.out.println("url地址:"+url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Json.success(url,"上传成功！");
        } catch (IOException e) {
            return  Json.fail("文件上传发生异常！");
        }
    }

    @PostMapping("uploadImg1")
    public Map<String,Object> uploadImg1(MultipartFile file, HttpServletRequest request) {
        System.out.println("执行上传uploadImg1...");
        if (file.isEmpty()) {
            return Json.fail("null");
        }
        String url=null;
        String path = null;
        //先判断一下当前是什么操作
        if("windows".equals(MyUtils.getOperateSysName())){
            path = this.uploadWinPath;
        }else{
            path = this.uploadLinuxPath;
        }
        try {
            String fileName =  MyUtils.uploadFileToQiniuCloud(file,path);//获得上传文件的文件名
            System.out.println("获取到文件名："+fileName);
            url = QiniuCloudUtil.upload(path+fileName,fileName);

            System.out.println("url地址:"+url);
        } catch (Exception e) {
            return  Json.fail("文件上传发生异常！");
        }
        return Json.success(url,"上传成功！");
    }

}
