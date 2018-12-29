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
@Controller
@SpringBootApplication
public class SpringWebApplication {

    @RequestMapping("/")
    public String index(Model model){
        Person single = new Person("aa",11);

        List<Person> people = new ArrayList<Person>();
        Person p1 = new Person("zhangsan",11);
        Person p2 = new Person("lisi",22);
        Person p3 = new Person("wangwu",33);
        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);

        return "index";
    }

    public static void main(String[] args) {
//        ServerProperties
        SpringApplication.run(SpringWebApplication.class,args);
    }
}
