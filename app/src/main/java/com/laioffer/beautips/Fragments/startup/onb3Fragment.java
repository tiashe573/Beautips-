package com.laioffer.beautips.Fragments.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

//import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentOnb3Binding;

public class onb3Fragment extends Fragment implements View.OnClickListener{
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    private ImageButton vector;
    private ImageButton next;
    private Button under_18;
    private Button between18_22;
    private Button between22_29;
    private Button between30_39;
    private Button moreThan_40;
    Context context;
    setUpViewModel viewModel;
    FragmentOnb3Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        binding = binding.inflate(inflater, container, false);
        preferences = getActivity().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
        myEdit = preferences.edit();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vector = view.findViewById(R.id.vector2);
        vector.setOnClickListener(this);


        under_18 = (Button)binding.under18;
        under_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under_18.setActivated(true);
                between18_22.setActivated(false);
                between22_29.setActivated(false);
                between30_39.setActivated(false);
                moreThan_40.setActivated(false);
                myEdit.putString("age", "Under 18").apply();
            }
        });

        between18_22 = (Button)binding.age22;
        between18_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under_18.setActivated(false);
                between18_22.setActivated(true);
                between22_29.setActivated(false);
                between30_39.setActivated(false);
                moreThan_40.setActivated(false);
                myEdit.putString("age", "18-22").apply();
            }
        });


        between22_29 = (Button)binding.age29;
        between22_29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under_18.setActivated(false);
                between18_22.setActivated(false);
                between22_29.setActivated(true);
                between30_39.setActivated(false);
                moreThan_40.setActivated(false);
                myEdit.putString("age", "22-29").apply();
            }
        });

        between30_39 = (Button)binding.age39;
        between30_39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under_18.setActivated(false);
                between18_22.setActivated(false);
                between22_29.setActivated(false);
                between30_39.setActivated(true);
                moreThan_40.setActivated(false);
                myEdit.putString("age", "30-39").apply();
            }
        });


        moreThan_40 = (Button)binding.age40;
        moreThan_40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                under_18.setActivated(false);
                between18_22.setActivated(false);
                between22_29.setActivated(false);
                between30_39.setActivated(false);
                moreThan_40.setActivated(true);
                myEdit.putString("age", "40+").apply();
            }
        });

        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);
    }








    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.vector2:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb2Fragment()).commit();
                break;
            case R.id.next:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb4Fragment()).commit();
                break;
            default:
                break;
        }
    }

}