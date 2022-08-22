package com.laioffer.beautips.Fragments.StylistPage.StylistReview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.laioffer.beautips.databinding.StylistPostBinding;

public class StylistReviewFragment extends Fragment {


    // bind the stylist_post xml;
    private StylistPostBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
        // Inflate the layout for this fragment
    }
}
