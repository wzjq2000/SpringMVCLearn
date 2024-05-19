package com.andy.learnJava.controller;

import com.andy.learnJava.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController implements ApplicationContextAware {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView index(HttpServletResponse response) throws IOException {
        if(1==1) throw new NullPointerException();
        byte[] data = "this is index".getBytes();
        response.setContentType("application/json");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}
