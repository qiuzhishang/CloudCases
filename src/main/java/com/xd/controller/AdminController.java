package com.xd.controller;

import com.xd.pojo.RequestMessage;
import com.xd.service.AdminService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/adminSign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage AdminSign(@RequestBody RequestMessage message){

        return adminService.AdminSign(message);
    }

    @RequestMapping(value = "/admin",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Admin(@RequestBody RequestMessage message){

        return adminService.Admin(message);


    }

}
