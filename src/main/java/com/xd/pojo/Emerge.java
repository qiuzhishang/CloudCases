package com.xd.pojo;

//紧急联系人
public class Emerge {
    private String name;
    private String phone_num;
    private Long user_id;

    @Override
    public String toString() {
        return "Emerge{" +
                "name='" + name + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
