package com.xd.pojo;

public class DoctorAddr {

    private String id_photo;
    private String face_photo;
    private String reverse_photo;
    private String doctor_certificate;
    private String doctor_license;
    private String title_certificate;
    private Long doctor_id;

    @Override
    public String toString() {
        return "DoctorAddr{" +
                "id_photo='" + id_photo + '\'' +
                ", face_photo='" + face_photo + '\'' +
                ", reverse_photo='" + reverse_photo + '\'' +
                ", doctor_certificate='" + doctor_certificate + '\'' +
                ", doctor_license='" + doctor_license + '\'' +
                ", title_certificate='" + title_certificate + '\'' +
                ", doctor_id=" + doctor_id +
                '}';
    }

    public String getId_photo() {
        return id_photo;
    }

    public void setId_photo(String id_photo) {
        this.id_photo = id_photo;
    }

    public String getFace_photo() {
        return face_photo;
    }

    public void setFace_photo(String face_photo) {
        this.face_photo = face_photo;
    }

    public String getReverse_photo() {
        return reverse_photo;
    }

    public void setReverse_photo(String reverse_photo) {
        this.reverse_photo = reverse_photo;
    }

    public String getDoctor_certificate() {
        return doctor_certificate;
    }

    public void setDoctor_certificate(String doctor_certificate) {
        this.doctor_certificate = doctor_certificate;
    }

    public String getDoctor_license() {
        return doctor_license;
    }

    public void setDoctor_license(String doctor_license) {
        this.doctor_license = doctor_license;
    }

    public String getTitle_certificate() {
        return title_certificate;
    }

    public void setTitle_certificate(String title_certificate) {
        this.title_certificate = title_certificate;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }
}
