package com.xd.pojo;

import java.util.List;

//病症自拍
public class DiseasePicture {
    private Long id;
    private String file_addr;
    private String date;
    private String information;
    private int picture_type;
    private Long user_id;
    private int flag;

    private List<String> address;

    @Override
    public String toString() {
        return "DiseasePicture{" +
                "id=" + id +
                ", file_addr='" + file_addr + '\'' +
                ", date='" + date + '\'' +
                ", info='" + information + '\'' +
                ", picture_type=" + picture_type +
                ", user_id=" + user_id +
                ", flag=" + flag +
                ", address=" + address +
                '}';
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_addr() {
        return file_addr;
    }

    public void setFile_addr(String file_addr) {
        this.file_addr = file_addr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPicture_type() {
        return picture_type;
    }

    public void setPicture_type(int picture_type) {
        this.picture_type = picture_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
