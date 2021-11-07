package com.aps.biometricauthapp.data.repository.datasource;

import com.aps.biometricauthapp.data.model.Address;

import io.reactivex.rxjava3.core.Flowable;

public interface AddressRemoteDataSource {
    Flowable<Address> getAddress();
}
