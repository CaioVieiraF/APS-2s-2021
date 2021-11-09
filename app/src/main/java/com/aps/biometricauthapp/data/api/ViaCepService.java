package com.aps.biometricauthapp.data.api;

import com.aps.biometricauthapp.data.model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("ws/{cep}/json/")
    Call<Address> getAddress(@Path("cep") String cep);
}
