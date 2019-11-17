package com.pirates.frts.domain;

/**
 * Contains all the user details
 * name : describes name of the person
 * email: describes email of the person
 * phone: describes the phone number of the person
 * address: describes the address of the person
 *
 * **/
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String address;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
