package com.aps.biometricauthapp.data.repository;

import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.repository.datasource.AddressRemoteDataSource;
import com.aps.biometricauthapp.domain.repository.AddressRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class AddressRepositoryImpl implements AddressRepository {
    private final AddressRemoteDataSource addressRemoteDataSource;

    @Inject
    public AddressRepositoryImpl(AddressRemoteDataSource addressRemoteDataSource) {
        this.addressRemoteDataSource = addressRemoteDataSource;
    }

    @Override
    public Flowable<Address> getAddress() {
        return addressRemoteDataSource.getAddress();
    }
}
