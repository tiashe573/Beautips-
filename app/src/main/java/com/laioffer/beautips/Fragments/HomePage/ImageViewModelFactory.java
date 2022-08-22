package com.laioffer.beautips.Fragments.HomePage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.beautips.Fragments.HomePage.HomeViewModel;
import com.laioffer.beautips.Fragments.HomePage.ImageRepository;

public class ImageViewModelFactory implements ViewModelProvider.Factory {
    private final ImageRepository repository;

    public ImageViewModelFactory(ImageRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}
