package com.flexible.sws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-28
 * Time: 11:16
 */
@SpringBootApplication
public class SpringWebApplication {
    public static void main(String[] args) {
//        ServerProperties

        SpringApplication.run(SpringWebApplication.class,args);
    }
}
