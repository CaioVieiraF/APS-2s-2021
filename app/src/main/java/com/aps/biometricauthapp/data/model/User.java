package com.aps.biometricauthapp.data.model;

import androidx.room.Entity;

import com.aps.biometricauthapp.util.AccessLevel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

@Entity
public class User {
    @Inject
    Address address;
    private String name;
    private String email;
    private Date birthday;
    private String password;
    private List<Date> loginActivity;
    private String accessLevelKey;
    private AccessLevel accessLevel;

    public User() {
    }

    public User(Address address,
                String name,
                String email,
                Date birthday,
                String password,
                List<Date> loginActivity,
                String accessLevelKey,
                AccessLevel accessLevel) {
        this.address = address;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.loginActivity = loginActivity;
        this.accessLevelKey = accessLevelKey;
        this.accessLevel = accessLevel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Date> getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(List<Date> loginActivity) {
        this.loginActivity = loginActivity;
    }

    public String getAccessLevelKey() {
        return accessLevelKey;
    }

    public void setAccessLevelKey(String accessLevelKey) {
        this.accessLevelKey = accessLevelKey;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
