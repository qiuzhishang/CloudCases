package com.xd.pojo;

public class Examine {
    private Long id;
    private Long user_id;
    private String date;
    private String examine_info;
    private int flag;

    @Override
    public String toString() {
        return "Examine{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", date='" + date + '\'' +
                ", examine_info='" + examine_info + '\'' +
                ", flag=" + flag +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getExamine_info() {
        return examine_info;
    }

    public void setExamine_info(String examine_info) {
        this.examine_info = examine_info;
    }
}
