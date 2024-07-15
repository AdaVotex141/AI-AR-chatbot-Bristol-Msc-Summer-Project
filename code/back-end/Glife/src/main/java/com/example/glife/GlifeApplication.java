package com.example.glife;

import com.example.glife.service.impl.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ServletComponentScan
@MapperScan("com.example.glife.mapper")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GlifeApplication {

    public static void main(String[] args) throws MessagingException {
        ApplicationContext context = SpringApplication.run(GlifeApplication.class, args);
        log.info("-------------project start success-----------");

/*        UserServiceImp userService = context.getBean(UserServiceImp.class);
        HttpServletRequest request = null;
        userService.sendCode(request, "liangjingtimes@gmail.com");*/
    }

}
