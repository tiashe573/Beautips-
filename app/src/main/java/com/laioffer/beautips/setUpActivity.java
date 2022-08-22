package com.laioffer.beautips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
//import android.app.Fragment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.laioffer.beautips.Fragments.startup.logInFragment;
import com.laioffer.beautips.Fragments.startup.onb1Fragment;
import com.laioffer.beautips.Fragments.startup.setUpViewModel;

public class setUpActivity extends AppCompatActivity {
    FragmentManager fragmentManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        if (getIntent().getExtras() != null) {
        String intentFragment = getIntent().getExtras().getString("frgToLoad");
        Log.d("tagging here", intentFragment);
        switch (intentFragment) {
            case "stylistProfile":
                // Load corresponding fragment
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_main, new logInFragment())
                        .setReorderingAllowed(true)
                        .commit();
                break;
            default:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_main, new onb1Fragment())
                        .setReorderingAllowed(true)
                        .commit();
                break;

        }


        }else{
            setContentView(R.layout.activity_set_up);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fl_main, new onb1Fragment())
                    .setReorderingAllowed(true)
                    .commit();

        }
    }
}


//}