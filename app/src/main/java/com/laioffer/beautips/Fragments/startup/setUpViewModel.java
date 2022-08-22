package com.laioffer.beautips.Fragments.startup;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.laioffer.beautips.Models.User;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.Repository.UserRepository;


public class setUpViewModel extends ViewModel {

    private UserRepository repo;
    public setUpViewModel(UserRepository repo){
        this.repo = repo;
    }
    @SuppressLint("LongLogTag")
    public MutableLiveData<User> getUserInfo(User user){
        Log.d("User model:",user.toString());
        MutableLiveData<User> result = this.repo.getUserInfo(user);
//        Log.d("tagging test viewmodel for login", result.getValue());
        return result;
    }

    public MutableLiveData<User> getUserProfile(String uuid){

        MutableLiveData<User> result = this.repo.getUserData(uuid);
//        Log.d("tagging test viewmodel for login", result.getValue());
        return result;
    }


}
