package com.xd.pojo;

public class Medicine {
    private Long id;
    private String medicine_name;
    private String medicine_method;
    private String time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_method() {
        return medicine_method;
    }

    public void setMedicine_method(String medicine_method) {
        this.medicine_method = medicine_method;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", medicine_name='" + medicine_name + '\'' +
                ", medicine_method='" + medicine_method + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
