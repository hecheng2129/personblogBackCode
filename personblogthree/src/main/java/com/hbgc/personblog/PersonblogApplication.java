package com.hbgc.personblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@MapperScan(value = "com.hbgc.springboot.repository")
public class PersonblogApplication {

    public static void main(String[] args) {
//        SpringApplication.run(PersonblogApplication.class, args);
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        new SpringApplicationBuilder(PersonblogApplication.class)//
                .main(SpringVersion.class) // 这个是为了可以加载 Spring 版本
                .bannerMode(Banner.Mode.CONSOLE)// 控制台打印
                .run(args);
    }

}
