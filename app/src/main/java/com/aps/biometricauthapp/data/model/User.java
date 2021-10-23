package com.aps.biometricauthapp.data.model;

import androidx.room.Entity;

import com.aps.biometricauthapp.util.AccessLevel;

import java.util.Date;

@Entity
public class User {
    private String name;
    private Integer age;
    private Date birthDay;
    private Address address;
    private String password;
    private Date loginActivity;
    private AccessLevel accessLevel;
    private String accessLevelKey;

    public User(String name,
                Integer age,
                Date birthDay,
                Address address,
                String password,
                Date loginActivity,
                AccessLevel accessLevel,
                String accessLevelKey) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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
