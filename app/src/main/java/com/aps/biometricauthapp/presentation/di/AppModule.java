package com.aps.biometricauthapp.presentation.di;

import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.repository.AddressRepositoryImpl;
import com.aps.biometricauthapp.data.repository.datasource.AddressRemoteDataSource;
import com.aps.biometricauthapp.data.repository.datasourceimpl.AddressRemoteDataSourceImpl;
import com.aps.biometricauthapp.domain.repository.AddressRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public final class AppModule {

    @Provides
    @Singleton
    static ViaCepService provideViaCepService() {
        return new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ViaCepService.class);
    }

    @Provides
    @Singleton
    static AddressRemoteDataSource provideAddressRemoteDataSource(ViaCepService viaCepService) {
        return new AddressRemoteDataSourceImpl(viaCepService, "02420001");
    }

    @Provides
    @Singleton
    static AddressRepository provideAddressRepository(AddressRemoteDataSource addressRemoteDataSource) {
        return new AddressRepositoryImpl(addressRemoteDataSource);
    }
    // TODO: 04/11/2021 add logging interceptor
}
