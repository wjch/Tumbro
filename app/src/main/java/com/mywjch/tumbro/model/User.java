package com.mywjch.tumbro.model;

/**
 * Created by mywjch on 15/8/3.
 */
public class User {
    private String name;
    private int age;
    private String email;
    private String address;
    private String sex;
    private String phone;
    private String pass;
    private int avatar;

    public User(String name, String email, String phone, String sex, int avatar) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.avatar = avatar;
    }

    public User(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
