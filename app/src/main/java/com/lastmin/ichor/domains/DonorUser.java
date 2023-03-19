package com.lastmin.ichor.domains;

import java.io.Serializable;

public class DonorUser implements Serializable {

    private String name;
    private int age;
    private String email;
    private String phone;
    private String address;
    private String bloodgroup;

    @Override
    public String toString() {
        return "DonorUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", bloodgroup='" + bloodgroup + '\'' +
                '}';
    }

    public DonorUser(){

    }

    public DonorUser( String name, int age, String email, String phone, String address, String bloodgroup) {

        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bloodgroup = bloodgroup;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
}
