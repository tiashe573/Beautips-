package com.laioffer.beautips.Fragments.StylistPage.StylistPost;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.Repository.StylistPostRepository;

import java.util.List;

public class StylistPostViewModel extends ViewModel {

    private final StylistPostRepository repo;

    public StylistPostViewModel(StylistPostRepository stylistRepo) {
        this.repo = stylistRepo;
    }

    public MutableLiveData<Stylist> getStylistInfo(String name){
        Log.d("test_node", "here in the info func");

        MutableLiveData<Stylist> result = this.repo.getStylistProfile(name);
        return result;
    }

    public LiveData<List<Post>> getStylistPosts(String name){
        Log.d("test_node", "here in the info func");

        LiveData<List<Post>> result = this.repo.getStylistPost(name);
        return result;
    }




}
