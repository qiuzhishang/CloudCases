package com.xd.pojo;

public class Register {
    private Long id;
    private String phone_num;
    private String pass_word;
    private String ver_code;
    private int user_type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", phone_num='" + phone_num + '\'' +
                ", pass_word='" + pass_word + '\'' +
                ", ver_code='" + ver_code + '\'' +
                ", user_type=" + user_type +
                ", token='" + token + '\'' +
                ", user_id=" + user_id +
                ", time=" + time +
                '}';
    }

    private String token;
    private Long user_id;
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setTime(){
        this.time = System.currentTimeMillis();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getVer_code() {
        return ver_code;
    }

    public void setVer_code(String ver_code) {
        this.ver_code = ver_code;
    }

    public Register clone() {
        Register register = new Register();
        register.pass_word = this.pass_word;
        register.phone_num = this.phone_num;
        register.ver_code = this.ver_code;
        register.user_type = this.user_type;
        register.token = this.token;
        return register;
    }
}
