package com.laioffer.beautips;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laioffer.beautips.Fragments.ClosetPage.ClosetFragment;
import com.laioffer.beautips.Fragments.ClosetPage.ClosetImageAdapter;
import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "new pop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        getImage();
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar2);
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
        if (getIntent().hasExtra("imageUrl") && getIntent().hasExtra("topName") && getIntent().hasExtra("topPrice") && getIntent().hasExtra("topSize") && getIntent().hasExtra("topUrl")
                && getIntent().hasExtra("bottomName") && getIntent().hasExtra("bottomPrice") && getIntent().hasExtra("bottomSize") && getIntent().hasExtra("bottomUrl")) {
            String imageUrl = getIntent().getStringExtra("imageUrl");
            String topName = getIntent().getStringExtra("topName");
            String topPrice = getIntent().getStringExtra("topPrice");
            String topSize = getIntent().getStringExtra("topSize");
            String topUrl = getIntent().getStringExtra("topUrl");
            String bottomName = getIntent().getStringExtra("bottomName");
            String bottomPrice = getIntent().getStringExtra("bottomPrice");
            String bottomSize = getIntent().getStringExtra("bottomSize");
            Log.d(TAG, "getImage: " + bottomPrice);
            String bottomUrl = getIntent().getStringExtra("bottomUrl");

            setImage(imageUrl, topName, topPrice, topSize, topUrl, bottomName, bottomPrice,bottomSize,bottomUrl);
        }
    }
    private void setImage(String imageUrl, String topName,String topPrice, String topSize, String topUrl, String bottomName, String bottomPrice, String bottomSize,String bottomUrl) {
        ImageView image = findViewById(R.id.item_pic);
        Picasso.get().load(imageUrl).into(image);
        TextView topN = findViewById(R.id.item_top_name);
        topN.setText(topName);
        TextView topS = findViewById(R.id.item_top_size);
        topS.setText(topSize);
        TextView topU = findViewById(R.id.item_top_url);
        topU.setText(topUrl);
        TextView topP = findViewById(R.id.item_top_price);
        topP.setText("$ " + topPrice);
        TextView bottomN = findViewById(R.id.item_bottom_name);
        bottomN.setText(bottomName);
        TextView bottomP = findViewById(R.id.item_bottom_price);
        bottomP.setText("$ "+bottomPrice);
        TextView bottomS = findViewById(R.id.item_bottom_size);
        bottomS.setText(bottomSize);
        TextView bottomU = findViewById(R.id.item_bottom_url);
        bottomU.setText(bottomUrl);

    }
}