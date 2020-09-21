package com.xd.pojo;

public class Doctor {
    private Long id;
    private String name;
    private String id_num;
    private String specialty;
    private String personal_info;
    private String social_work;
    private String address;
    private Long user_id;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_num='" + id_num + '\'' +
                ", specialty='" + specialty + '\'' +
                ", personal_info='" + personal_info + '\'' +
                ", social_work='" + social_work + '\'' +
                ", address='" + address + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPersonal_info() {
        return personal_info;
    }

    public void setPersonal_info(String personal_info) {
        this.personal_info = personal_info;
    }

    public String getSocial_work() {
        return social_work;
    }

    public void setSocial_work(String social_work) {
        this.social_work = social_work;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
