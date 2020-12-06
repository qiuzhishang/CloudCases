package com.xd.service;

import com.xd.mapper.RegisterMapper;
import com.xd.pojo.Register;
import com.xd.pojo.RequestMessage;
import com.xd.utils.GenerateToken;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    GenerateToken generateToken = new GenerateToken();

    //用户注册
    public ResponseMessage userRegister(RequestMessage message){
        ResponseMessage response = new ResponseMessage();

        Register register = message.getRegister();

        if (register == null){

            response.setStatus_code(100);
            return response;
        }

        register.setTime();

        Register user = registerMapper.selectUserByPhoneNum(register.getPhone_num(), register.getUser_type());

        if (user != null){
            System.out.println(user+"===========user");
            response.setStatus_code(0);//注册失败
            return response;
        }
        String token = generateToken.generateToken(UUID.randomUUID().toString());
        register.setToken(token);

        int err =  registerMapper.insertUserInfo(register);
        user = registerMapper.selectUserByPhoneNum(register.getPhone_num(), register.getUser_type());

        System.out.println(user);
        register.setUser_id(user.getId());
        System.out.println(user.getId());

        registerMapper.updateUserIdByPhone(user.getId(),register.getPhone_num());
        System.out.println(err);
        response.setStatus_code(1);//注册成功
        response.setUserId(user.getId());
        return response;
    }
}
