package com.laioffer.beautips.Fragments.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Utils.GlideApp;
import com.laioffer.beautips.databinding.SearchImageItemBinding;
import com.laioffer.beautips.Models.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeImageAdapter extends RecyclerView.Adapter<HomeImageAdapter.HomeImageViewHolder>{


    private Context context;
    private List<Post> posts;
    public HomeImageAdapter(Context context, ArrayList<Post> postList) {

        this.context = context;
        this.posts = postList;
    }

    @NonNull
    @Override
    public HomeImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_image_item, parent, false);
        return new HomeImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeImageViewHolder holder, int position) {
        Post postImage = posts.get(position);
        //Load Post Image
        GlideApp.with(holder.itemView)
                .load(getImage(postImage.getImageName()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .fitCenter()
                .into(holder.post);
        // modify all the text view
        holder.likeCount.setText(String.valueOf(postImage.getNumOfLikes()));
        holder.stylistName.setText(postImage.getOwnerId());

        Picasso.get().load(postImage.getProfileImageUrl()).into(holder.stylistPic);
        Picasso.get().load(postImage.getImageUrl()).into(holder.post);
        /*GlideApp.with(holder.itemView)
                .load(getImage(postImage.getProfileImageUrl()))
                .fitCenter()
                .into(holder.stylistPic);*/
    }

    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class HomeImageViewHolder extends RecyclerView.ViewHolder {

        ImageView post;
        TextView stylistName;
        TextView likeCount;
        ImageView thumbsUp;
        ImageView stylistPic;

        public HomeImageViewHolder(@NonNull View itemView) {
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
