package com.xd.pojo;

import java.util.Date;
import java.util.List;

public class InstrumentPicture {
    private Long id;

    private String date;

    private Long user_id;
    private List<String> address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "InstrumentPicture{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", user_id=" + user_id +
                ", address=" + address +
                '}';
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
