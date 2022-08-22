package com.laioffer.beautips.Network;

import com.laioffer.beautips.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserLoginApi {

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<User> getUserLoginInfo(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("userinfo")
    Call<User> getUserProfile(@Body String uuid);


}
