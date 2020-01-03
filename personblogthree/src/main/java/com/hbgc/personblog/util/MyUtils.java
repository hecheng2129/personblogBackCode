package com.hbgc.personblog.util;


import cn.hutool.extra.mail.MailUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MyUtils {

    //生成验证码
    private static char[] ops = new char[]{'+', '-', '*'};

    //调用阿里云发送短信的接口实现用户注册时给用户发送验证码
    //阿里云短信调用接口
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIeqvGyIgrlqGV";
    static final String accessKeySecret = "KnHoSj6cYKOABMfu36XNjdy8cWpil0";


    //使用阿里云发送短信验证码
    /*
     * randomCode,表示要发送的验证码
     * mobile,表示要发送的手机号码
     * */
    public static void sendMessageCodeByAliyun(String randomCode, String telephone) {
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", telephone);
            request.putQueryParameter("SignName", "西蒙牛Java自学网");
            request.putQueryParameter("TemplateCode", "SMS_167961470");
            request.putQueryParameter("TemplateParam", "{\"code\":\""+randomCode+"\"}");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //给某个邮箱发送验证码
    public static void sendEmailValidateCode(String toEmail,String validateCode){
        MailUtil.send(toEmail, "欢迎注册个人博客", validateCode, false);
    }

    //获得上传成功后返回的UUID的文件名，避免用户之间重名的文件覆盖问题
    public static String createUUIDFileName(String fileName){
        String uuid =  UUID.randomUUID().toString().replace("-","");
        return uuid+fileName;
    }

    //实现文件上传
    public static String uploadFile(MultipartFile file, String path) throws Exception{
        String fileName = file.getOriginalFilename(); //获得上传文件的文件名
        //使用UUID生成一个不重复的文件名字。
        String uploadFileName = createUUIDFileName(fileName);

        //先判断文件的父目录是否存在，如果不存在先创建目录
        File uploadFile = new File(path,uploadFileName);
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        //在判断文件是否存在，如果存在先删除，再创建。
        if(uploadFile.exists()){
            uploadFile.delete();
        }
        uploadFile.createNewFile();

        file.transferTo(uploadFile);
        return uploadFile.getName();
    }

    //获取操作系统的名称
    public static String getOperateSysName(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "windows";
        }else{
            return "linux";
        }
    }

    //实现文件上传
    public static String uploadFileToQiniuCloud(MultipartFile file, String path){
        String fileName = file.getOriginalFilename(); //获得上传文件的文件名
        //使用UUID生成一个不重复的文件名字。
//        String uploadFileName = createUUIDFileName(fileName);

        //先判断文件的父目录是否存在，如果不存在先创建目录
        File uploadFile = new File(path,fileName);
        try{
            uploadFile.getParentFile().exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
//        System.out.println("新文件名1："+uploadFileName);
        //在判断文件是否存在，如果存在先删除，再创建。
        if(uploadFile.exists()){
           uploadFile.delete();
        }
        try {
            uploadFile.createNewFile();
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadFile.getName();
    }

    //test
    public static void main(String[] args) {

        MyUtils.sendMessageCodeByAliyun("6379","18832074761");
    }

}
