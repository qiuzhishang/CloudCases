package com.xd.controller;

import com.xd.pojo.Register;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController

//用户注册；患者和医生
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public String Config(@RequestBody Register request) {

        System.out.println(request.toString());
        System.out.println("Config--------------1");

        String result = userService.userRegister(request);

        return result;
    }



}
