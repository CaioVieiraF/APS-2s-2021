package com.aps.biometricauthapp.di;

import android.content.Context;

import androidx.room.Room;

import com.aps.biometricauthapp.data.api.ViaCepService;
import com.aps.biometricauthapp.data.db.UserDao;
import com.aps.biometricauthapp.data.db.UserDatabase;
import com.aps.biometricauthapp.data.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
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
    static UserRepository provideUserRepository(ViaCepService viaCepService, UserDao userDao) {
        return new UserRepository(viaCepService, userDao);
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

    @Provides
    @Singleton
    static UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }

    @Provides
    @Singleton
    static UserDatabase provideUserDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, "user_database").build();
    }
}
