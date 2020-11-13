package com.xd.controller;

import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.service.RegisterService;
import com.xd.service.UserService;
import com.xd.utils.ResponseMessage;
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
    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage Config(@RequestBody RequestMessage message) {

        ResponseMessage responseMessage = new ResponseMessage();

        System.out.println(message.toString());
        System.out.println("Config--------------1");

        responseMessage = registerService.userRegister(message);

        return responseMessage;
    }



}
