package com.aps.biometricauthapp.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.aps.biometricauthapp.util.AccessLevel;

import java.util.ArrayList;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @Embedded
    private Address address;
    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String birthday;
    private String password;
    private ArrayList<String> loginActivity;
    private String accessLevelKey;
    private AccessLevel accessLevel;
    private Boolean isBiometricEnabled;

    public User(
            Address address,
            String cpf,
            String name,
            String email,
            String phone,
            String birthday,
            String password,
            ArrayList<String> loginActivity,
            String accessLevelKey,
            AccessLevel accessLevel,
            Boolean isBiometricEnabled
    ) {
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
        this.isBiometricEnabled = isBiometricEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public String getCpf() {
        return cpf;
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

    public String getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getLoginActivity() {
        return loginActivity;
    }

    public String getAccessLevelKey() {
        return accessLevelKey;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public Boolean getBiometricEnabled() {
        return isBiometricEnabled;
    }
}
