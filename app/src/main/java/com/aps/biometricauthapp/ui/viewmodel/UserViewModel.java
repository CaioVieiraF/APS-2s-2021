package com.aps.biometricauthapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.repository.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;

@HiltViewModel
public class UserViewModel extends ViewModel {

    private final UserRepository repository;

    @Inject
    public UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public Call<Address> getAddress(String cep) {
        return repository.getAddress(cep);
    }
}
