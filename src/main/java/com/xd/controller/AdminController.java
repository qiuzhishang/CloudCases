package com.xd.controller;

import com.xd.pojo.RequestMessage;
import com.xd.service.AdminService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://39.100.100.198:8082")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/adminSign", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseMessage AdminSign(@RequestBody RequestMessage message){

        return adminService.AdminSign(message);
    }

    @RequestMapping(value = "/admin",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage Admin(@RequestBody RequestMessage message){

        return adminService.Admin(message);


    }

}
