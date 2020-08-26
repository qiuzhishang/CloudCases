package com.xd.pojo;

public class isToken {

    private String token;
    private String phone_num;

    @Override
    public String toString() {
        return "isToken{" +
                "token='" + token + '\'' +
                ", phone_num='" + phone_num + '\'' +
                '}';
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
