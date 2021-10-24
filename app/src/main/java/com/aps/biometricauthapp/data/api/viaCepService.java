package com.aps.biometricauthapp.data.api;

import com.aps.biometricauthapp.data.model.Address;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface viaCepService {
    @GET("ws/{cep}/json/")
    Observable<Address> getAddress(@Path("cep") String cep);
}
