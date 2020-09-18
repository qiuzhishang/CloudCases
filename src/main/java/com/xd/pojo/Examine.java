package com.xd.pojo;

public class Examine {
    private Long id;
    private Long user_id;
    private String examine_info;

    @Override
    public String toString() {
        return "Examine{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", examine_info='" + examine_info + '\'' +
                '}';
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
