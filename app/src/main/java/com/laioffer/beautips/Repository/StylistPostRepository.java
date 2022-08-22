package com.laioffer.beautips.Repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.Network.RetrofitClient;
import com.laioffer.beautips.Network.StylistProfileApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StylistPostRepository {

    private final StylistProfileApi StylistProfileApi;

    Stylist stylistInfo = null;

    public StylistPostRepository(Context context) {
        StylistProfileApi = RetrofitClient.newInstance(context).create(StylistProfileApi.class);

    }

    public MutableLiveData<Stylist> getStylistProfile(String name) {
        final MutableLiveData<Stylist> result = new MutableLiveData<>();
        Stylist stylistName = new Stylist(name);
        Log.i("test", stylistName.toString());
        Call<Stylist> callResult = StylistProfileApi.getStylistInfo(stylistName);

        callResult.enqueue(new Callback<Stylist>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<Stylist> call, Response<Stylist> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                    Log.i("Successful", response.body().toString());
                } else {
                    Log.i("Failure status code:", String.valueOf(response.errorBody()));
                    result.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Stylist> call, Throwable t) {
                Log.i("Sth is wrong", t.toString());
            }
        });
        return result;
    }


    public LiveData<List<Post>> getStylistPost(String name) {
        final MutableLiveData<List<Post>> result = new MutableLiveData<>();

        Call<List<Post>> callResult = StylistProfileApi.getStylistInfo(name);
        callResult.enqueue(new Callback<List<Post>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {

                    result.setValue(response.body());
                    Log.i("Successful", response.body().toString());
                } else {
                    Log.i("Failure status code for image:", String.valueOf(response));
                    result.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i("Sth is wrong", t.toString());
            }
        });
        return result;
    }



}
