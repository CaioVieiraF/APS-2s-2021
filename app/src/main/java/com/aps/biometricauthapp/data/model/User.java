package com.aps.biometricauthapp.data.model;

import androidx.room.Entity;

import com.aps.biometricauthapp.util.AccessLevel;

import java.util.Date;

@Entity
public class User {
    private String name;
    private Date birthday;
    private Address address;
    private String password;
    private Date loginActivity;
    private AccessLevel accessLevel;
    private String accessLevelKey;

    public User(String name,
                Date birthday,
                Address address,
                String password,
                Date loginActivity,
                AccessLevel accessLevel,
                String accessLevelKey) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.password = password;
        this.loginActivity = loginActivity;
        this.accessLevel = accessLevel;
        this.accessLevelKey = accessLevelKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(Date loginActivity) {
        this.loginActivity = loginActivity;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevelKey() {
        return accessLevelKey;
    }

    public void setAccessLevelKey(String accessLevelKey) {
        this.accessLevelKey = accessLevelKey;
    }
}
