package com.aps.biometricauthapp.data.model;

import androidx.room.Entity;

import com.aps.biometricauthapp.util.AccessLevel;

import java.util.Date;

import javax.inject.Inject;

@Entity
public class User {
    @Inject
    Address address;
    private String cpf;
    private String name;
    private String email;
    private String phone;
    private Date birthday;
    private String password;
    private Date[] loginActivity;
    private String accessLevelKey;
    private AccessLevel accessLevel;

    public User() {
    }

    public User(Address address,
                String cpf,
                String name,
                String email,
                String phone,
                Date birthday,
                String password,
                Date[] loginActivity,
                String accessLevelKey,
                AccessLevel accessLevel) {
        this.address = address;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date[] getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(Date[] loginActivity) {
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
