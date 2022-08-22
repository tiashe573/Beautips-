package com.laioffer.beautips.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.Fragments.startup.setUpViewModel;

public class BeautipsViewModelFactory_User implements ViewModelProvider.Factory {

//    private final StylistPostRepository repository;
//
//    public BeautipsViewModelFactory(StylistPostRepository repository) {
//        this.repository = repository;
//    }
//
//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(StylistPostViewModel.class)) {
//            return (T) new StylistPostViewModel(repository);
//        }else {
//            throw new IllegalStateException("Unknown ViewModel");
//        }
//    }
    private final UserRepository repository;

    public BeautipsViewModelFactory_User(UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(setUpViewModel.class)) {
            return (T) new setUpViewModel(repository);
        }
        else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }

}
