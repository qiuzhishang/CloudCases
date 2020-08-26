package com.xd.controller;

import com.xd.pojo.RequestMessage;
import com.xd.pojo.Sign;
import com.xd.service.UserService;
import com.xd.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class SignController {
    @Autowired
    private UserService userService;
    //登陆
    @RequestMapping(value = "/sign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage DoSign(@RequestBody RequestMessage message) {
        Sign signInfo = message.getSign();
        String token = message.getToken();
        ResponseMessage response = userService.userSign(signInfo, token);

        return response;

    }

}
