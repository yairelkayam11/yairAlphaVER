package com.example.yairalphaver;

import android.provider.ContactsContract;

public class User {
    private String password, email, phone;

    public User (){}
    public User (String password, String email, String phone) {
        this.password=password;
        this.email=email;
        this.phone=phone;

    }

    public String getName() {
        return password;
    }

    public void setName(String password) {
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

}