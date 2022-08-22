package com.laioffer.beautips.Fragments.StylistPage.StylistPost;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.laioffer.beautips.Models.Post;

import java.util.ArrayList;
import java.util.List;

import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Utils.GlideApp;
import com.laioffer.beautips.databinding.StylistInfoRecyclerComponentBinding;
import com.laioffer.beautips.databinding.StylistPostBinding;

/*

 */
public class StylistPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Object> items;

    private static final int BLOCK = 0, IMAGE = 1;
    private boolean  load = false;



    public StylistPostAdapter(Context context, ArrayList<Object> items) {
        this.context = context;
        this.items = items;
    }

    
    public void setPosts(List<Post> newsList ) {
        Stylist obj = (Stylist) items.get(0);
        items.clear();
        items.add(obj);
        items.addAll(newsList);
        notifyDataSetChanged();
    }

    @SuppressLint("LongLogTag")
    public void setFirst(Stylist stylist) {
        items.add(0,stylist);
        Log.d("test here for setting first index","I am here");

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_post, parent, false);
//        return new StylistPostViewHolder(view);

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case BLOCK:
                View v1 = inflater.inflate(R.layout.stylist_info_recycler_component, parent, false);
                viewHolder = new StylistInfos(v1);
                break;
            default:
                View v2 = inflater.inflate(R.layout.stylist_post, parent, false);
                viewHolder = new StylistPostViewHolder(v2);
                break;

        }
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case BLOCK:
                StylistInfos vh1 = (StylistInfos) holder;
                Object ITEM_INFO = items.get(position);
                Stylist infos = (Stylist) ITEM_INFO;
                vh1.age.setText("#" + String.valueOf(infos.getAge()) + " years");
                vh1.numCustomers.setText(String.valueOf(infos.getNumOfCustomers()));
                vh1.bodyShape.setText("#" +infos.getBodyShape() + " Shape");
                vh1.size.setText("#" +infos.getSize());
                vh1.numLikes.setText(String.valueOf(infos.getNumOfLikes()));
                vh1.stylistTitle.setText(infos.getTitle());
                vh1.stylistName.setText(infos.getName());
                vh1.numFollows.setText(String.valueOf(infos.getNumOfCustomers()));
                vh1.numReviews.setText(infos.getNumOfReviews() + " reviews");
                GlideApp.with(vh1.itemView)
                        .load(infos.getProfileImageUrl())
                        .fitCenter()
                        .into(vh1.profilepic);

                break;
            //Load Post Image
            // modify all the text view
            default:
                StylistPostViewHolder vh3 = (StylistPostViewHolder) holder;
                Object ITEM_POST_DEFAULT = items.get(position);
                Post postImage_DEFAULT = (Post) ITEM_POST_DEFAULT;
                //Load Post Image
                GlideApp.with(vh3.itemView)
                        .load(postImage_DEFAULT.getImageUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                        .fitCenter()
                        .into(vh3.post);
                // modify all the text view
                vh3.likeCount.setText(String.valueOf(postImage_DEFAULT.getNumOfLikes()));
                vh3.stylistName.setText(postImage_DEFAULT.getOwnerId());
                GlideApp.with(vh3.itemView)
                        .load(postImage_DEFAULT.getProfileImageUrl())
                        .fitCenter()
                        .into(vh3.stylistPic);
                break;
        }

    }



    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Post) {
            return IMAGE;
        } else if (items.get(position) instanceof Stylist) {
            return BLOCK;
        }
        return -1;
    }




    @Override
    public int getItemCount() {
        return items.size();
    }


    /*
    This is the holder class for an individual post item;
     */
    public static class StylistPostViewHolder extends RecyclerView.ViewHolder {

        ImageView post;
        TextView stylistName;
        TextView likeCount;
        ImageView thumbsUp;
        ImageView stylistPic;

        public StylistPostViewHolder(@NonNull View itemView) {
            super(itemView);
            StylistPostBinding binding = StylistPostBinding.bind(itemView);
            post = binding.postPic;
            stylistName = binding.stylistNamePost;
            likeCount = binding.numThums;
            thumbsUp = binding.thumb;
            stylistPic = binding.stylistProfileImage;
        }
    }

    public static class StylistInfos extends RecyclerView.ViewHolder {
        TextView numReviews;
        TextView numFollows;
        TextView stylistTitle;
        TextView stylistName;
        TextView numLikes;
        TextView size;
        TextView bodyShape;
        TextView numCustomers;
        TextView age;
        ImageView profilepic;


        public StylistInfos(@NonNull View itemView) {
            super(itemView);
            StylistInfoRecyclerComponentBinding binding = StylistInfoRecyclerComponentBinding.bind(itemView);
            profilepic =  binding.stylistImage;
            age = binding.age;
            numCustomers = binding.numCustomers;
            bodyShape = binding.bodyShape;
            size = binding.size;
            numLikes = binding.numLikes;
            stylistName = binding.stylistName;
            stylistTitle = binding.stylistTitle;
            numFollows = binding.numsFollows;
            numReviews = binding.reviewsNum;

        }
    }


}
