package com.aps.biometricauthapp.data.api;

import com.aps.biometricauthapp.data.model.Address;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("ws/{cep}/json/")
    Flowable<Address> getAddress(@Path("cep") String cep);
}
