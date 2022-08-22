package com.laioffer.beautips.Fragments.StylistPage.StylistPost;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.R;

import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.StylistPostRepository;

import com.laioffer.beautips.Repository.UserRepository;
import com.laioffer.beautips.databinding.ScrollStylistPostsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class StylistPostFragment extends Fragment {


    Context context;
    RecyclerView recyclerView;
    StylistPostAdapter StylistPostAdapter;
    StylistPostViewModel stylistViewModel;
    ScrollStylistPostsBinding binding;
    private List<Post> Posts;
    String  stylistName;
    Stylist stylistInfo;
    int test = 0;



    private ArrayList<Object> postList = new ArrayList<>();


    public StylistPostFragment() {
        // Required empty public constructor
//        this.stylistName = Name;
    }

    public StylistPostFragment(String name) {
        // Required empty public constructor
        this.stylistName = name;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          //return inflater.inflate(R.layout.scroll_stylist_posts, container, false);
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @SuppressLint("LongLogTag")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();

        //recycler view image show
        recyclerView = view.findViewById(R.id.swipe_post_recycler_view);

        //use grid layout
        int numberOfColumns = 2;
        StylistPostAdapter = new StylistPostAdapter(context, postList);
        recyclerView.setAdapter(StylistPostAdapter);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.d("test position:", String.valueOf(StylistPostAdapter.getItemViewType(position)));
                switch (StylistPostAdapter.getItemViewType(position)) {
                    case 0:
                        return 2;
                    default:
                        return 1;
                }
            }
        });

        recyclerView.setLayoutManager(mLayoutManager);

//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));



        StylistPostRepository repository = new StylistPostRepository(getContext());

        stylistViewModel = new ViewModelProvider(this, new BeautipsViewModelFactory(repository))
                .get(StylistPostViewModel.class);





        stylistViewModel
                .getStylistInfo(stylistName)
                .observe(
                        getViewLifecycleOwner(),
                        response -> {
                            if (response != null) {
                                //Binding set text
                                stylistInfo = response;
                                StylistPostAdapter.setFirst(stylistInfo);
                                stylistViewModel
                                        .getStylistPosts(stylistName)
                                        .observe(
                                                getViewLifecycleOwner(),
                                                response2 -> {
                                                    if (response2 != null) {
                                                        //Binding set text
                                                        Posts = response2;
                                                        StylistPostAdapter.setPosts(Posts);
                                                    }
                                                });

                            }
                        });


    }


}