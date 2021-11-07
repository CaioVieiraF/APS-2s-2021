package com.aps.biometricauthapp.data.repository.datasourceimpl;

import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.repository.datasource.AddressRemoteDataSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class AddressRemoteDataSourceImpl implements AddressRemoteDataSource {

    private final ViaCepService viaCepService;
    private final String cep;

    @Inject
    public AddressRemoteDataSourceImpl(ViaCepService viaCepService, String cep) {
        this.viaCepService = viaCepService;
        this.cep = cep;
    }

    @Override
    public Flowable<Address> getAddress() {
        return viaCepService.getAddress(cep);
    }
}
