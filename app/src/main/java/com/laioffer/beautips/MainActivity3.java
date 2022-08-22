package com.laioffer.beautips;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laioffer.beautips.Fragments.ClosetPage.ClosetFragment;
import com.laioffer.beautips.Fragments.ClosetPage.ClosetImageAdapter;
import com.laioffer.beautips.Models.Makeupcolor;
import com.squareup.picasso.Picasso;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = "new pop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);
        getImage();
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar3);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toolbar.setNavigationIcon(R.drawable.vector);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                onBackPressed();
                /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        });
    }

    private void getImage () {
        if (getIntent().hasExtra("brand") && getIntent().hasExtra("category") && getIntent().hasExtra("price") && getIntent().hasExtra("name") && getIntent().hasExtra("Url")
                && getIntent().hasExtra("rating") && getIntent().hasExtra("color") && getIntent().hasExtra("description") && getIntent().hasExtra("imageUrl")) {
            String brand = getIntent().getStringExtra("brand");
            String category = getIntent().getStringExtra("category");
            String price = getIntent().getStringExtra("price");
            String name = getIntent().getStringExtra("name");
            String Url = getIntent().getStringExtra("Url");
            String rating = getIntent().getStringExtra("rating");
            // String color = getIntent().getStringExtra("color");
            String description = getIntent().getStringExtra("description");
            // Log.d(TAG, "getImage: " + bottomPrice);
            String imageUrl = getIntent().getStringExtra("imageUrl");

            setImage(brand, category, price, name, Url, rating,description,imageUrl);
        }
    }
    private void setImage(String brand, String category,String price, String name, String Url, String rating, String description, String imageUrl) {
        ImageView image = findViewById(R.id.product_pic);
        Picasso.get().load(imageUrl).into(image);
        TextView brand_name = findViewById(R.id.brand_name);
        brand_name.setText("Brand: " + brand);
        TextView category1 = findViewById(R.id.category);
        category1.setText("Category: " + category);
        TextView product_name = findViewById(R.id.product_name);
        product_name.setText(name);
        TextView product_price = findViewById(R.id.product_price);
        product_price.setText("$ " + price);

        Button product_link2 = findViewById(R.id.product_link2);
        product_link2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });
        Log.d(TAG, "setImage: " + Url);
        TextView product_rate = findViewById(R.id.product_rate);
        product_rate.setText("Rating: " + rating);
        TextView description1 = findViewById(R.id.description);
        description1.setText(description);
        //TextView bottomU = findViewById(R.id.item_bottom_url);


    }
}