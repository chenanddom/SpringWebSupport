package com.flexible.sws;

import com.flexible.sws.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
