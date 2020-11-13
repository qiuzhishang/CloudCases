package com.xd.pojo;

public class WatchPatientsInfo {

    private String name;
    private String id_num;
    private String phone_num;

    @Override
    public String toString() {
        return "WatchPatientsInfo{" +
                "name='" + name + '\'' +
                ", id_num='" + id_num + '\'' +
                ", phone_num='" + phone_num + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}
