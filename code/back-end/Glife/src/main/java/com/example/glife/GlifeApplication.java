package com.example.glife;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@ServletComponentScan
@MapperScan("com.example.glife.mapper")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GlifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlifeApplication.class, args);
        log.info("-------------project start success-----------");
    }

}
