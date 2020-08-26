package com.xd.pojo;

import java.util.Date;
import java.util.List;

public class ImagePicture {
    private Long id;

    private Date date;

    private Long user_id;
    private List<String> address;

    @Override
    public String toString() {
        return "LaboratoryPicture{" +
                "id=" + id +
                ", date=" + date +
                ", user_id=" + user_id +
                ", address=" + address +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
