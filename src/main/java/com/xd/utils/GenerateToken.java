package com.xd.utils;

public class GenerateToken {
    public String generateToken(String token){
        return token + System.currentTimeMillis();
    }

}
