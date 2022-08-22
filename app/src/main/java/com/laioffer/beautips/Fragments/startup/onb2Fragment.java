package com.laioffer.beautips.Fragments.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentOnb2Binding;


public class onb2Fragment extends Fragment implements View.OnClickListener{

    private ImageButton vector;
    private ImageButton next;
    private ImageButton Hourglass;
    private ImageButton Round;
    private ImageButton Triangle;
    private ImageButton Rectangle;
    private ImageButton Pear;
    private ImageView shape1, shape2, shape3, shape4, shape5;
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    Context context;
    setUpViewModel viewModel;
    FragmentOnb2Binding binding;




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


        Hourglass = (ImageButton)binding.HourglassPic;
        shape1 = (ImageView)binding.shape1;
        Hourglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape1.setVisibility(View.VISIBLE);
                shape1.setImageResource(R.drawable.line1);
                shape2.setVisibility(View.INVISIBLE);
                shape3.setVisibility(View.INVISIBLE);
                shape4.setVisibility(View.INVISIBLE);
                shape5.setVisibility(View.INVISIBLE);
                myEdit.putString("shape", "Hourglass").apply();
            }
        });


        Round = (ImageButton)binding.RoundPic;
        shape2 = (ImageView)binding.shape2;
        Round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape2.setVisibility(View.VISIBLE);
                shape2.setImageResource(R.drawable.line1);
                shape1.setVisibility(View.INVISIBLE);
                shape3.setVisibility(View.INVISIBLE);
                shape4.setVisibility(View.INVISIBLE);
                shape5.setVisibility(View.INVISIBLE);
                myEdit.putString("shape", "Round").apply();
            }
        });

        Triangle = (ImageButton)binding.TrianglePic;
        shape3 = (ImageView)binding.shape3;
        Triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape3.setVisibility(View.VISIBLE);
                shape3.setImageResource(R.drawable.line1);
                shape1.setVisibility(View.INVISIBLE);
                shape2.setVisibility(View.INVISIBLE);
                shape4.setVisibility(View.INVISIBLE);
                shape5.setVisibility(View.INVISIBLE);
                myEdit.putString("shape", "Triangle").apply();
            }
        });

        Rectangle = (ImageButton)binding.RectanglePic;
        shape4 = (ImageView)binding.shape4;
        Rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape4.setVisibility(View.VISIBLE);
                shape4.setImageResource(R.drawable.line1);
                shape1.setVisibility(View.INVISIBLE);
                shape2.setVisibility(View.INVISIBLE);
                shape3.setVisibility(View.INVISIBLE);
                shape5.setVisibility(View.INVISIBLE);
                myEdit.putString("shape", "Rectangle").apply();
            }
        });

        Pear = (ImageButton)binding.PearPic;
        shape5 = (ImageView)binding.shape5;
        Pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape5.setVisibility(View.VISIBLE);
                shape5.setImageResource(R.drawable.line1);
                shape1.setVisibility(View.INVISIBLE);
                shape2.setVisibility(View.INVISIBLE);
                shape3.setVisibility(View.INVISIBLE);
                shape4.setVisibility(View.INVISIBLE);
                myEdit.putString("shape", "Pear").apply();
            }
        });


        vector = view.findViewById(R.id.vector1);
        vector.setOnClickListener(this);
        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);

    }




    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.vector1:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb1Fragment()).commit();
                break;
            case R.id.next:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb3Fragment()).commit();
                break;
            default:
                break;
        }
    }


}