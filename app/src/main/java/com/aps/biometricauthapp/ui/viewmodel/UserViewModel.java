package com.aps.biometricauthapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.data.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;

@HiltViewModel
public class UserViewModel extends ViewModel {

    private final UserRepository repository;
    private LiveData<List<User>> getAllUsers;

    @Inject
    public UserViewModel(UserRepository repository) {
        this.repository = repository;
        getAllUsers = repository.getGetAllUsers();
    }

    public Call<Address> getAddress(String cep) {
        return repository.getAddress(cep);
    }

    public void inserUser(User user) {
        repository.insertUser(user);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    public LiveData<List<User>> getGetAllUsers() {
        return getAllUsers;
    }
}
