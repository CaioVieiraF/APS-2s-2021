package com.aps.biometricauthapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aps.biometricauthapp.data.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();
}
