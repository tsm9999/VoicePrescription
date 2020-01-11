package com.example.tanush.sih_20;

public class DoctorUser {
    String name;
    String lic_no;
    String ph_no;
    String email;
    String photo;
    String sign;
    String degree_photo;
    String password;
    String dob;
    String degree;
    String hospital;
    String address;
    String aadhar_card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLic_no() {
        return lic_no;
    }

    public void setLic_no(String lic_no) {
        this.lic_no = lic_no;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDegree_photo() {
        return degree_photo;
    }

    public void setDegree_photo(String degree_photo) {
        this.degree_photo = degree_photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadhar_card() {
        return aadhar_card;
    }

    public void setAadhar_card(String aadhar_card) {
        this.aadhar_card = aadhar_card;
    }

    public DoctorUser(String name, String lic_no, String ph_no, String email, String photo, String sign, String degree_photo, String password, String dob, String degree, String hospital, String address, String aadhar_card) {
        this.name = name;
        this.lic_no = lic_no;
        this.ph_no = ph_no;
        this.email = email;
        this.photo = photo;
        this.sign = sign;
        this.degree_photo = degree_photo;
        this.password = password;
        this.dob = dob;
        this.degree = degree;
        this.hospital = hospital;
        this.address = address;
        this.aadhar_card = aadhar_card;
    }
}
