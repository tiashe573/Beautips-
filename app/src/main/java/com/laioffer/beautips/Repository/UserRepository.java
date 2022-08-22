package com.laioffer.beautips.Repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.beautips.Fragments.startup.setUpViewModel;
import com.laioffer.beautips.Models.User;
import com.laioffer.beautips.Network.RetrofitClient;
import com.laioffer.beautips.Network.UserLoginApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final UserLoginApi userLoginApi;
    Context context;


    public UserRepository(Context context) {
        this.userLoginApi =  RetrofitClient.newInstance(context).create(UserLoginApi.class);
        this.context = context;

    }


    public MutableLiveData<User> getUserInfo(User user){
        Log.d("user",user.toString());
        MutableLiveData<User> result = new MutableLiveData<>();
        Call<User> callResult = userLoginApi.getUserLoginInfo(user);
        callResult.enqueue(new Callback<User>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                    Log.i("Successful ", response.body().toString());
                } else {
                    Log.i("Failure status code for user profile:", String.valueOf(response.errorBody()));
                    result.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Login is wrong", t.toString());
                Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show();
            }
        });

        return result;
    }




    public MutableLiveData<User> getUserData(String uuid){
        MutableLiveData<User> result = new MutableLiveData<>();
        Call<User> callResult = userLoginApi.getUserProfile(uuid);
        callResult.enqueue(new Callback<User>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                    Log.i("Successful on user profile get ", response.body().toString());
                } else {
                    Log.i("Failure status code for user info:", String.valueOf(response.errorBody()));
                    result.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Login is wrong", t.toString());
            }
        });
        return result;

    }













}
