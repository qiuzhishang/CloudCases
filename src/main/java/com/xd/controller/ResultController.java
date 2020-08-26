package com.xd.controller;

import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.service.TextService;
import com.xd.service.UploadFileService;
import com.xd.service.UserService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {
    @Autowired
    TextService textService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/selectAllPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage Picture(@RequestBody RequestMessage message) {


        ResponseMessage response = userService.selectAllPicture(message);



        return response;
    }


}
