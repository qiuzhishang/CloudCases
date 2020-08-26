package com.xd.pojo;

import java.util.List;

public class Patient {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private String id_num;
    private int sex;
    private String race;
    private String birthplace;
    private String postal_addr;
    private String now_addr;
    private String pre_addr1;
    private String pre_addr2;
    private List<Emerge> emerge;
    private Long user_id;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_num='" + id_num + '\'' +
                ", sex=" + sex +
                ", race='" + race + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", postal_addr='" + postal_addr + '\'' +
                ", now_addr='" + now_addr + '\'' +
                ", pre_addr1='" + pre_addr1 + '\'' +
                ", pre_addr2='" + pre_addr2 + '\'' +
                ", emerge=" + emerge +
                ", user_id=" + user_id +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Emerge> getEmerge() {
        return emerge;
    }

    public void setEmerge(List<Emerge> emerge) {
        this.emerge = emerge;
    }

    public String getPre_addr2() {

        return pre_addr2;
    }

    public void setPre_addr2(String pre_addr2) {
        this.pre_addr2 = pre_addr2;
    }

    public String getNow_addr() {
        return now_addr;
    }

    public void setNow_addr(String now_addr) {
        this.now_addr = now_addr;
    }

    public String getPre_addr1() {
        return pre_addr1;
    }

    public void setPre_addr1(String pre_addr1) {
        this.pre_addr1 = pre_addr1;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getPostal_addr() {
        return postal_addr;
    }

    public void setPostal_addr(String postal_addr) {
        this.postal_addr = postal_addr;
    }

}
