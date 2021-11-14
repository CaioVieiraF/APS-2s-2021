package com.aps.biometricauthapp.data.repository;

import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.model.Address;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class UserRepository {

    private final ViaCepService viaCepService;

    @Inject
    public UserRepository(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    public Call<Address> getAddress(String cep) {
        return viaCepService.getAddress(cep);
    }
}
