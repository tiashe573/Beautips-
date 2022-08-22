package com.laioffer.beautips.Fragments.ClosetPage;

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
import com.laioffer.beautips.Models.Closet;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Utils.GlideApp;
import com.laioffer.beautips.databinding.SearchImageItemBinding;
import com.laioffer.beautips.Models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ClosetImageAdapter extends RecyclerView.Adapter<ClosetImageAdapter.ClosetImageViewHolder> {
    private static final String TAG = "ClosetAdapter";
    private Context context;
    private List<Closet> ClosetList;
    public ClosetImageAdapter(Context context, ArrayList<Closet> ClosetList) {

        this.context = context;
        this.ClosetList = ClosetList;
    }

    @NonNull
    @Override
    public ClosetImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.closet_display, parent, false);
        return new ClosetImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClosetImageViewHolder holder, int position) {
        Closet ClosetImage = ClosetList.get(position);
        //Load Post Image
        GlideApp.with(holder.itemView)
                .load(getImage(ClosetImage.getImageName()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .fitCenter()
                .into(holder.post);
        // modify all the text view

        holder.likeCount.setText(String.valueOf(ClosetImage.getScore()));
        holder.stylistName.setText(ClosetImage.getBodyShape());
        int price = ClosetImage.getBottomPrice() + ClosetImage.getTopPrice();
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
                .load(Uri.parse(ClosetImage.getImageUrl()))
                .into(holder.stylistPic);
        GlideApp.with(holder.itemView)
                .load(Uri.parse(ClosetImage.getImageUrl()))
                .into(holder.post);
        holder.itemView.setOnClickListener((view) ->{
            Intent intent = new Intent(context, MainActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("imageUrl", ClosetImage.getImageUrl());
            intent.putExtra("topName", ClosetImage.getTopName());
            intent.putExtra("topPrice", ""+ClosetImage.getTopPrice());
            intent.putExtra("topSize", ClosetImage.getTopSize());
            intent.putExtra("topUrl", ClosetImage.getTopUrl());
            intent.putExtra("bottomName", ClosetImage.getBottomName());
            intent.putExtra("bottomPrice", ""+ClosetImage.getBottomPrice());
            Log.d(TAG, "onBindViewHolder: " + ClosetImage.getBottomPrice());
            intent.putExtra("bottomSize", ClosetImage.getBottomSize());
            intent.putExtra("bottomUrl", ClosetImage.getBottomUrl());
            context.startActivities(new Intent[]{intent});
        });
    }

    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
    @Override
    public int getItemCount() {
        return ClosetList.size();
    }

    public static class ClosetImageViewHolder extends RecyclerView.ViewHolder {

        ImageView post;
        TextView stylistName;
        TextView bottomName;
        TextView likeCount;
        ImageView thumbsUp;
        ImageView stylistPic;


        public ClosetImageViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchImageItemBinding binding = SearchImageItemBinding.bind(itemView);
            post = binding.postPic;
            stylistName = binding.stylistNamePost;
            likeCount = binding.numThums;
            thumbsUp = binding.thumb;
            // bottomName = binding.bottomName;
            stylistPic = binding.stylistProfileImage;
        }

    }
}
