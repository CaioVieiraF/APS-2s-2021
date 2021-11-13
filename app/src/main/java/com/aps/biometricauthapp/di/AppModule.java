package com.aps.biometricauthapp.di;

import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.repository.AddressRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public final class AppModule {

    @Provides
    @Singleton
    static AddressRepository provideAddressRepository(ViaCepService viaCepService) {
        return new AddressRepository(viaCepService);
    }

    @Provides
    @Singleton
    static ViaCepService provideViaCepService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ViaCepService.class);
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
