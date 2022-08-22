package com.laioffer.beautips.Fragments.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
//import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentOnb2Binding;
import com.laioffer.beautips.databinding.FragmentOnb4Binding;

public class onb4Fragment extends Fragment implements View.OnClickListener{
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    private ImageButton vector;
    private Button top_XS;
    private Button top_S;
    private Button top_M;
    private Button top_L;
    private Button top_XL;
    private Button top_2XL;
    private Button top_3XL;
    private Button top_3XLL; // This is 3XL+
    private Button bottom_XS;
    private Button bottom_S;
    private Button bottom_M;
    private Button bottom_L;
    private Button bottom_XL;
    private Button bottom_2XL;
    private Button bottom_3XL;
    private Button bottom_3XLL; // This is 3XL+
    private ImageButton next;
    FragmentOnb4Binding binding;
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
        vector = view.findViewById(R.id.vector3);
        vector.setOnClickListener(this);



        top_XS = (Button)binding.XS;
        top_XS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(true);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","XS").apply();
            }
        });

        top_S = (Button)binding.S;
        top_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(true);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","S").apply();
            }
        });

        top_M = (Button)binding.M;
        top_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(true);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","M").apply();
            }
        });


        top_L = (Button)binding.L;
        top_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(true);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","L").apply();
            }
        });

        top_XL = (Button)binding.XL;
        top_XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(true);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","XL").apply();
            }
        });

        top_2XL = (Button)binding.XL2;
        top_2XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(true);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","2XL").apply();
            }
        });


        top_3XL = (Button)binding.XL3;
        top_3XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(true);
                top_3XLL.setActivated(false);
                myEdit.putString("topSize","3XL").apply();
            }
        });

        top_3XLL = (Button)binding.XLL3;
        top_3XLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top_XS.setActivated(false);
                top_S.setActivated(false);
                top_M.setActivated(false);
                top_L.setActivated(false);
                top_XL.setActivated(false);
                top_2XL.setActivated(false);
                top_3XL.setActivated(false);
                top_3XLL.setActivated(true);
                myEdit.putString("topSize","3XL+").apply();
            }
        });




        bottom_XS = (Button)binding.bXS;
        bottom_XS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(true);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","XS").apply();
            }
        });

        bottom_S = (Button)binding.bS;
        bottom_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(true);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","S").apply();
            }
        });

        bottom_M = (Button)binding.bM;
        bottom_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(true);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","M").apply();
            }
        });


        bottom_L = (Button)binding.bL;
        bottom_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(true);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","L").apply();
            }
        });

        bottom_XL = (Button)binding.bXL;
        bottom_XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(true);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","XL").apply();
            }
        });

        bottom_2XL = (Button)binding.bXL2;
        bottom_2XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(true);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","2XL").apply();
            }
        });


        bottom_3XL = (Button)binding.bXL3;
        bottom_3XL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(true);
                bottom_3XLL.setActivated(false);
                myEdit.putString("bottomSize","3XL").apply();
            }
        });

        bottom_3XLL = (Button)binding.bXLL3;
        bottom_3XLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottom_XS.setActivated(false);
                bottom_S.setActivated(false);
                bottom_M.setActivated(false);
                bottom_L.setActivated(false);
                bottom_XL.setActivated(false);
                bottom_2XL.setActivated(false);
                bottom_3XL.setActivated(false);
                bottom_3XLL.setActivated(true);
                myEdit.putString("bottomSize","3XL+").apply();
            }
        });

        next = (ImageButton) view.findViewById(R.id.next);//重置按钮
        next.setOnClickListener(this);
    }



    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.vector3:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb3Fragment()).commit();
                break;
            case R.id.next:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb5Fragment()).commit();
                break;
            default:
                break;
        }


    }

}