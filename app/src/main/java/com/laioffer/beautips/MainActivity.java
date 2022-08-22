package com.laioffer.beautips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.beautips.Fragments.StylistPage.StylistProfileFragment;
import com.laioffer.beautips.Fragments.startup.logInFragment;
import com.laioffer.beautips.Fragments.startup.onb1Fragment;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    FragmentManager fragmentManager;

    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;


    ListView listView;
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_placeholder);

//        preferences = this.getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
//        myEdit = preferences.edit();
//        String stylistName = preferences.getString("stylistName","err");
//        Toast.makeText(this, stylistName, Toast.LENGTH_LONG).show();


            androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
            // Sets the Toolbar to act as the ActionBar for this Activity window.
            // Make sure the toolbar exists in the activity and is not null
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            toolbar.setNavigationIcon(R.drawable.vector);

            BottomNavigationView navView = findViewById(R.id.nav_view);
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            navController = navHostFragment.getNavController();
            navView.setItemIconTintList(null);

            AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                    .Builder(R.id.topview)
                    .build();

            //controller --> fragment, navigation view
            NavigationUI.setupWithNavController(navView, navController);
//        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);


        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
            Log.d("testing",String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
            getSupportFragmentManager().popBackStackImmediate();
        }
        else {
            Log.d("testing","back button not pressed");
            super.onBackPressed();
        }
    }

}