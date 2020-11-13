package com.xd.pojo;

public class Sign {
    private String phone_num;
    private String pass_word;
    private String ver_code;
    private int user_type;

    @Override
    public String toString() {
        return "Sign{" +
                "phone_num='" + phone_num + '\'' +
                ", pass_word='" + pass_word + '\'' +
                ", ver_code='" + ver_code + '\'' +
                ", user_type=" + user_type +
                '}';
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getVer_code() {
        return ver_code;
    }

    public void setVer_code(String ver_code) {
        this.ver_code = ver_code;

    }

    public Sign clone() {
        Sign new_sign = new Sign();
        new_sign.phone_num = this.phone_num;
        new_sign.pass_word = this.pass_word;
        new_sign.ver_code = this.ver_code;
        return new_sign;
    }
}
