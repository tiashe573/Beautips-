package com.laioffer.beautips.Fragments.startup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.beautips.MainActivity;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory_User;
import com.laioffer.beautips.Repository.UserRepository;

public class onb1Fragment extends Fragment implements View.OnClickListener {
    private ImageButton  start_now;
    private TextView login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    setUpViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
        myEdit = preferences.edit();
        myEdit.putString("if_stylist", "false").apply();
        myEdit.putString("if_stylist", "true").apply();
        myEdit.putString("stylistName", "err").apply();
        View view = inflater.inflate(R.layout.fragment_onb1, null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        start_now = (ImageButton) view.findViewById(R.id.star_now);//重置按钮
        start_now.setOnClickListener(this);
        login=(TextView)view.findViewById(R.id.login_1);
        login.setOnClickListener(this);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.star_now:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb2Fragment()).commit();
                break;
            case R.id.login_1:
                String uuid = preferences.getString("uuid", "");
                uuid ="";
                if (!uuid.equals("")){
                    //viewModel get UserInfo
                    Log.d("here","this is with shared pref");
                    UserRepository repository = new UserRepository(getContext());
                    viewModel = new ViewModelProvider(this, new BeautipsViewModelFactory_User(repository))
                            .get(setUpViewModel.class);
                    viewModel
                            .getUserProfile(uuid)
                            .observe(
                                    getViewLifecycleOwner(),
                                    response -> {
                                        if (response != null) {
                                            Log.d("shape in the viewmodel at 139 login frag", response.getBodyShape());
                                            myEdit.putString("shape", response.getBodyShape()).apply();
                                            myEdit.putString("topSize", response.getTopSize()).apply();
                                            myEdit.putString("bottomSize", response.getBottomSize()).apply();
                                            myEdit.putString("age", response.getAge()).apply();
                                            myEdit.putString("size", response.getSize()).apply();
                                            myEdit.putString("uuid", response.getId()).apply();
                                            //tiao zhuan
                                            Intent intent  = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                }else{
                    getFragmentManager().beginTransaction().replace(R.id.fl_main, new logInFragment()).commit();
                    break;
                }

            default:
                break;
        }
    }


}