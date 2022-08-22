package com.laioffer.beautips.Fragments.startup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.Fragments.StylistPage.StylistProfileFragment;
import com.laioffer.beautips.MainActivity;
import com.laioffer.beautips.Models.User;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory_User;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.Repository.UserRepository;
import com.laioffer.beautips.databinding.FragmentOnb5Binding;
import com.laioffer.beautips.setUpActivity;

public class onb5Fragment extends Fragment implements View.OnClickListener {
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    private ImageButton vector;
    private TextView shape;
    private TextView age;
    private TextView top_size;
    private TextView bottom_size;
    private ImageButton sign_up;
    private TextView see_tips;
    FragmentOnb5Binding binding;
    setUpViewModel viewModel;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        binding = binding.inflate(inflater, container, false);
        preferences = getActivity().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
        myEdit = preferences.edit();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserRepository repository = new UserRepository(getContext());
        viewModel = new ViewModelProvider(this, new BeautipsViewModelFactory_User(repository))
                .get(setUpViewModel.class);

        shape = (TextView)binding.shape;
        age = (TextView)binding.age;
        top_size = (TextView)binding.topSize;
        bottom_size = (TextView)binding.bottomSize;
        Log.d("test shape", preferences.getString("shape",""));
        shape.setText(preferences.getString("shape","") + " Shape");
        age.setText("Age " + preferences.getString("age", ""));
        top_size.setText("Top " + preferences.getString("topSize","") + " Size");
        bottom_size.setText("Bottom " + preferences.getString("bottomSize",  "")+ " Size");

        vector = view.findViewById(R.id.vector4);
        vector.setOnClickListener(this);

        sign_up = view.findViewById(R.id.signup_1);
        sign_up.setOnClickListener(this);

        see_tips = (TextView) binding.seeStyleTips;
        see_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(preferences.getString("email", ""));
                user.setName(preferences.getString("name",""));
                user.setPassword(preferences.getString("password",""));
                user.setBodyShape(preferences.getString("shape", ""));
                user.setAge(preferences.getString("age", ""));
                user.setTopSize(preferences.getString("topSize", ""));
                user.setBottomSize(preferences.getString("bottomSize", ""));


                Intent intent  = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);

            }
        });

    }


//    public User setDefaultUser (){
//        user = new User();
//        user.setEmail(preferences.getString("email", ""));
//        user.setName(preferences.getString("name",""));
//        user.setPassword(preferences.getString("password",""));
//        user.setBodyShape(preferences.getString("shape", ""));
//        user.setAge(preferences.getString("age", ""));
//        user.setTopSize(preferences.getString("topSize", ""));
//        user.setBottomSize(preferences.getString("bottomSize", ""));
//
//        return user;
//    }



    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.vector4:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb4Fragment()).commit();
                break;
            case R.id.signup_1:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new signUpFragment()).commit();
                break;

            default:
                break;
        }
    }

}