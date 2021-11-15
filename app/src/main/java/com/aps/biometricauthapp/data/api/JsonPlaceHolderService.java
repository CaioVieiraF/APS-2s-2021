package com.aps.biometricauthapp.data.api;

import com.aps.biometricauthapp.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderService {
    @GET("posts")
    Call<List<Post>> getPostList();
}
