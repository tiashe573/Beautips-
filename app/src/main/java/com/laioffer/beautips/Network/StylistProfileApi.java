package com.laioffer.beautips.Network;
import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StylistProfileApi {

//    @GET("jsonresponses")
//    Call<Post> getConnectionTest();
//
//    @GET("stringresponses")
//    Call<String> getStringResponse();


    @Headers("Content-Type: application/json")
    @POST("stylistsinfos")
    Call<Stylist> getStylistInfo(@Body Stylist stylist);


    @POST("stylistsposts")
    Call<List<Post>> getStylistInfo(@Body String name);

    //POST
    //Call<Boolean> string Login(@Body email, password);


//    @Headers("Content-Type: application/json")
//    @POST("users")
//    Call<String> postUserInfo( @Body Post user);


}
