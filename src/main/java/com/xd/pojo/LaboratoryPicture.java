package com.xd.pojo;

import java.util.Date;
import java.util.List;

public class LaboratoryPicture {
    private Long id;

    private String date;
    private int flag;

    private Long user_id;
    private List<String> address;

    @Override
    public String toString() {
        return "LaboratoryPicture{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                ", user_id=" + user_id +
                ", address=" + address +
                '}';
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
