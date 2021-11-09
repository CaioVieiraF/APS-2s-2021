package com.aps.biometricauthapp.ui.fragments.signup;

import androidx.lifecycle.ViewModel;

import com.aps.biometricauthapp.data.repository.AddressRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignUpViewModel extends ViewModel {

    private final AddressRepository repository;

    @Inject
    public SignUpViewModel(AddressRepository repository) {
        this.repository = repository;
    }
}
