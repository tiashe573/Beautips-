package com.laioffer.beautips.Fragments.HomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.R;
import com.laioffer.beautips.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "homeFragment";
    private SharedPreferences preferences;
    private SharedPreferences.Editor myEdit;
    private DatabaseReference myRef;
    private FragmentHomeBinding binding;
    String shape;
    String size;
    String age;
    Context context;
    RecyclerView recyclerView;
    com.laioffer.beautips.Fragments.HomePage.HomeImageAdapter HomeImageAdapter;
    private ArrayList<Post> postList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }



    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding = binding.inflate(inflater, container, false);
        preferences = getActivity().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
        shape = preferences.getString("shape","shape not found");
        size = preferences.getString("size","size not found");
        age = preferences.getString("age","age not found");
        Log.d("shape testing for home frag", shape);
        Log.d("shape testing for size",size);
        Log.d("shape testing for age",age);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i("size of list",String.valueOf(postList.size()));
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.news_results_recycler_view);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        HomeImageAdapter = new HomeImageAdapter(context, postList);
        recyclerView.setAdapter(HomeImageAdapter);
        recyclerView.setHasFixedSize(true);
        TextView firstTag = view.findViewById(R.id.firstTag);
        firstTag.setText("#"+shape);
        TextView secondTag = view.findViewById(R.id.secondTag);
        secondTag.setText("#"+age);
        TextView thirdTag = view.findViewById(R.id.thirdTag);
        thirdTag.setText("#"+size);
        myRef = FirebaseDatabase.getInstance().getReference();
        postList = new ArrayList<>();
        ClearAll();
        GetDataBaseFromFireBase();
    }

    private void GetDataBaseFromFireBase() {
        Query query = myRef.child("Images");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Post post = new Post();

                    post.setImageName(snapshot.child("imageName").getValue().toString());
                    post.setImageUrl(snapshot.child("imageUrl").getValue().toString());
                    post.setNumOfLikes(Integer.parseInt(snapshot.child("numOfLikes").getValue().toString()));
                    post.setOwnerId(snapshot.child("ownerId").getValue().toString());
                    post.setProfileImageUrl(snapshot.child("profileImageUrl").getValue().toString());
                    post.setPostId(snapshot.child("postId").getValue().toString());
                    post.setTimeStamp(snapshot.child("timeStamp").getValue().toString());

                    postList.add(post);
                }

                HomeImageAdapter = new HomeImageAdapter(context, postList);
                recyclerView.setAdapter(HomeImageAdapter);
                HomeImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll () {
        if (postList!= null) {
            postList.clear();

            if(HomeImageAdapter != null) {
                HomeImageAdapter.notifyDataSetChanged();
            }
        }
        postList = new ArrayList<>();
    }

}
