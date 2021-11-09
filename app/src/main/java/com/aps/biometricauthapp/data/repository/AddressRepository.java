package com.aps.biometricauthapp.data.repository;

import com.aps.biometricauthapp.data.api.ViaCepService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddressRepository {

    private final ViaCepService viaCepService;

    @Inject
    public AddressRepository(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }
}
