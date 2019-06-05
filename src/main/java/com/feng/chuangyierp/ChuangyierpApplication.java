package com.feng.chuangyierp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.feng.chuangyierp.mapper")
public class ChuangyierpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuangyierpApplication.class, args);
    }

}
