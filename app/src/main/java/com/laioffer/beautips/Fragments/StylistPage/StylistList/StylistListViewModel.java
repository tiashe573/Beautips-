package com.laioffer.beautips.Fragments.StylistPage.StylistList;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.Repository.StylistListRepository;

public class StylistListViewModel extends ViewModel {
    private final StylistListRepository repo;

    public StylistListViewModel(StylistListRepository stylistRepo) {
        this.repo = stylistRepo;
    }

    public MutableLiveData<Stylist> getStylistInfo(String name){
        Log.d("test_node", "here in the info func");

        MutableLiveData<Stylist> result = this.repo.getStylistProfile(name);
        return result;
    }
}
