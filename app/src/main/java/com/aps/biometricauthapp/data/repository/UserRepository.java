package com.aps.biometricauthapp.data.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aps.biometricauthapp.data.api.JsonPlaceHolderService;
import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.db.UserDao;
import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.model.Post;
import com.aps.biometricauthapp.data.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class UserRepository {

    private final JsonPlaceHolderService placeHolderService;
    private final ViaCepService viaCepService;
    private LiveData<List<User>> getAllUsers;
    private final UserDao userDao;

    @Inject
    public UserRepository(ViaCepService viaCepService, JsonPlaceHolderService placeHolderService, UserDao userDao) {
        this.viaCepService = viaCepService;
        this.placeHolderService = placeHolderService;
        this.userDao = userDao;
        getAllUsers = userDao.getAllUsers();
    }

    public Call<Address> getAddress(String cep) {
        return viaCepService.getAddress(cep);
    }

    public Call<List<Post>> getPostList() {
        return placeHolderService.getPostList();
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getGetAllUsers() {
        return getAllUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }
}
