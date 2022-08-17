package com.example.springcore1.web;

import com.example.springcore1.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        System.out.println("[GET] /log-demo");
        System.out.println("myLogger = " + myLogger.getClass());

        String requestURL = request.getRequestURL().toString();

        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");

        return "OK";
    }
}
