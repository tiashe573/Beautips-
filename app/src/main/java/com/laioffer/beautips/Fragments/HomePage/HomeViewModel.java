package com.laioffer.beautips.Fragments.HomePage;

import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private final ImageRepository repository;

    public HomeViewModel(ImageRepository ImageRepository) {
        this.repository = ImageRepository;
    }

}
