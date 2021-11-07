package com.aps.biometricauthapp.domain.repository;

import com.aps.biometricauthapp.data.model.Address;

import io.reactivex.rxjava3.core.Flowable;

public interface AddressRepository {
    Flowable<Address> getAddress();
}
