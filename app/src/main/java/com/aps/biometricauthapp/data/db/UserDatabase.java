package com.aps.biometricauthapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aps.biometricauthapp.data.model.User;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
