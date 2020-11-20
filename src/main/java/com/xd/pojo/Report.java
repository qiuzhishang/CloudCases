package com.xd.pojo;

import java.util.List;

public class Report {
    private Long id;
    private String date;
    private String hospital;
    private String report_info;
    private String result;
    private Long user_id;
    private int flag;

    private List<String> address;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", hospital='" + hospital + '\'' +
                ", report_info='" + report_info + '\'' +
                ", result='" + result + '\'' +
                ", user_id=" + user_id +
                ", flag=" + flag +
                ", address=" + address +
                '}';
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getReport_info() {
        return report_info;
    }

    public void setReport_info(String report_info) {
        this.report_info = report_info;
    }

}
