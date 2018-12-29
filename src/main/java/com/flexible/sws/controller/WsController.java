package com.flexible.sws.controller;

import com.flexible.sws.bean.DemoMessage;
import com.flexible.sws.bean.DemoResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-29
 * Time: 12:33
 */
@Controller
public class WsController {
    //当浏览器想服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@RequestMapping
    @MessageMapping("/welcome")
    //当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    @SendTo("/topic/getResponse")
    public DemoResponse handle(DemoMessage message) throws InterruptedException {

        Thread.sleep(3000);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setResponseMessage("welcome,"+message.getName()+"!");
        return demoResponse;
    }
}
