package com.example.jecktao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 添加扫描mapper文件夹的配置
@MapperScan("com.example.jecktao.mapper")
@SpringBootApplication
public class JecktaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JecktaoApplication.class, args);
    }

}
