package com.xd.utils;

//生成token
public class GenerateToken {
    public String generateToken(String token){
        return token + System.currentTimeMillis();
    }

}
