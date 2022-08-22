package com.laioffer.beautips.Fragments.startup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostAdapter;
import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.Fragments.StylistPage.StylistProfileFragment;
import com.laioffer.beautips.MainActivity;
import com.laioffer.beautips.Models.User;
import com.laioffer.beautips.Network.RetrofitClient;
import com.laioffer.beautips.Network.UserLoginApi;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory_User;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.Repository.UserRepository;
import com.laioffer.beautips.databinding.FragmentOnb2Binding;
import com.laioffer.beautips.databinding.FragmentSignUpBinding;
import com.laioffer.beautips.setUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUpFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    private EditText email;
    private EditText passowrd;
    private ImageButton delete1;
    private TextView login;
    private ImageButton sign_up;
    private String emailText;
    private String passwordText;
    Context context;
    FragmentSignUpBinding binding;
    private setUpViewModel viewModel;
    private StringBuilder result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
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
        email = view.findViewById(R.id.email);
        passowrd = view.findViewById(R.id.pwd);
        login = view.findViewById(R.id.login_2);
        login.setOnClickListener(this);
        emailText = email.getText().toString();
        passwordText = passowrd.getText().toString();
        delete1 = binding.delete1;

        delete1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                String if_stylist = preferences.getString("if_stylist","err");
                String stylistName = preferences.getString("stylistName","err");
                Log.d("tagging for stylistName in sign up", stylistName);
                if(!stylistName.equals("err")){
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }else{
                    getFragmentManager().beginTransaction().replace(R.id.fl_main, new onb5Fragment()).commit();

                }
            }
        });


        sign_up = (ImageButton)binding.signup2;
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEdit.putString("email", emailText);
                myEdit.putString("name", emailText);
                myEdit.putString("password", passwordText);

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


    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.login_2:
                getFragmentManager().beginTransaction().replace(R.id.fl_main, new logInFragment()).commit();
                break;
            default:
                break;
        }


    }


}