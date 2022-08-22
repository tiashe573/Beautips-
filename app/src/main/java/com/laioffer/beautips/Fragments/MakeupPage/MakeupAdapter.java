package com.laioffer.beautips.Fragments.MakeupPage;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.laioffer.beautips.Fragments.HomePage.HomeImageAdapter;
import com.laioffer.beautips.MainActivity;
import com.laioffer.beautips.MainActivity2;
import com.laioffer.beautips.MainActivity3;
import com.laioffer.beautips.Models.Closet;
import com.laioffer.beautips.Models.Makeup;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Utils.GlideApp;
import com.laioffer.beautips.databinding.SearchImageItemBinding;
import com.laioffer.beautips.Models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MakeupAdapter extends RecyclerView.Adapter<com.laioffer.beautips.Fragments.MakeupPage.MakeupAdapter.MakeupImageViewHolder> {
    private static final String TAG = "MakeupAdapter";
    private Context context;
    private List<Makeup> MakeupList;
    public MakeupAdapter(Context context, ArrayList<Makeup> MakeupList) {

        this.context = context;
        this.MakeupList = MakeupList;
    }

    @NonNull
    @Override
    public com.laioffer.beautips.Fragments.MakeupPage.MakeupAdapter.MakeupImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_image_item, parent, false);
        return new MakeupImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull com.laioffer.beautips.Fragments.MakeupPage.MakeupAdapter.MakeupImageViewHolder holder, int position) {
        Makeup MakeupImage = MakeupList.get(position);
        //Load Post Image
        GlideApp.with(holder.itemView)
                .load(getImage(MakeupImage.getName()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .fitCenter()
                .into(holder.post);
        // modify all the text view

        holder.likeCount.setText(String.valueOf(MakeupImage.getId()));
        holder.stylistName.setText(MakeupImage.getProduct_type());
        int price = (int) MakeupImage.getPrice();
        String priceTotal = "$" + price;
        holder.likeCount.setText(priceTotal);
        //holder.bottomName.setText(ClosetImage.getBottomName());
        //holder.post.setImageURI(Uri.parse(ClosetImage.getImageUrl()));
        //holder.stylistPic.setImageURI(Uri.parse(ClosetImage.getImageUrl()));
        /*Picasso.get().load(ClosetImage.getImageUrl()).into(holder.stylistPic);
        Picasso.get().load(ClosetImage.getImageUrl()).into(holder.post);*/

        Log.d(TAG, "onBindViewHolder: ");
        /*Picasso.get()
                .load(Uri.parse("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/76/760261cecb162a3d81dc8b767078bc53342c7e4e_full.jpg"))
                .into(holder.post);
        Picasso.get()
                .load(ClosetImage.getImageUrl())
                .into(holder.stylistPic);*/
        GlideApp.with(holder.itemView)
                .load(Uri.parse(MakeupImage.getImage_link()))
                .into(holder.stylistPic);
        GlideApp.with(holder.itemView)
                .load(Uri.parse(MakeupImage.getImage_link()))
                .into(holder.post);
        holder.itemView.setOnClickListener((view) ->{
            Intent intent = new Intent(context, MainActivity3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("brand", MakeupImage.getBrand());
            intent.putExtra("category", MakeupImage.getCategory());
            intent.putExtra("price", ""+MakeupImage.getPrice());
            intent.putExtra("name", MakeupImage.getName());
            intent.putExtra("Url", MakeupImage.getProduct_link().toString());
            intent.putExtra("rating", MakeupImage.getRating());
            intent.putExtra("color", ""+MakeupImage.getProduct_colors().toString());
            // Log.d(TAG, "onBindViewHolder: " + ClosetImage.getBottomPrice());
            intent.putExtra("description", MakeupImage.getDescription());
            intent.putExtra("imageUrl", MakeupImage.getImage_link());
            context.startActivities(new Intent[]{intent});
        });
    }

    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
    @Override
    public int getItemCount() {
        return MakeupList.size();
    }

    public static class MakeupImageViewHolder extends RecyclerView.ViewHolder {

        ImageView post;
        TextView stylistName;
        TextView likeCount;
        ImageView thumbsUp;
        ImageView stylistPic;

        public MakeupImageViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchImageItemBinding binding = SearchImageItemBinding.bind(itemView);
            post = binding.postPic;
            stylistName = binding.stylistNamePost;
            likeCount = binding.numThums;
            thumbsUp = binding.thumb;
            stylistPic = binding.stylistProfileImage;
        }

    }
}
