package com.laioffer.beautips.Fragments.StylistPage.StylistList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.beautips.Fragments.StylistPage.StylistPost.StylistPostViewModel;
import com.laioffer.beautips.Models.Post;
import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.R;
import com.laioffer.beautips.Repository.BeautipsViewModelFactory;
import com.laioffer.beautips.Repository.StylistPostRepository;
import com.laioffer.beautips.databinding.FragmentStylistListBinding;
import com.laioffer.beautips.databinding.SingleStylistListBinding;

import java.util.ArrayList;
import java.util.List;

public class StylistListFragment extends Fragment {
    Context context;
    private FragmentStylistListBinding binding;


    RecyclerView recyclerView;

    ArrayList<Stylist> stylistList = new ArrayList<>();

    StylistPostViewModel stylistViewModel;


    // construct a view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // setup parameter
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Find your Stylist");

        this.context = getContext();

        Log.d("a",binding.stylistListRecyclerView.toString());
        Stylist abby = new Stylist("Abby");
        abby.setNumOfCustomers(123);
        abby.setNumOfReviews(12);
        abby.setTitle("Specialist");
        abby.setProfileImageUrl("https://firebasestorage.googleapis.com/v0/b/beautips-54eb3.appspot.com/o/Abby%2FMask%20Group-6.png?alt=media&token=a9372e0f-6edb-4c77-bf39-4a0baf1ce979");


        Stylist emily = new Stylist("Emily");
        emily.setNumOfCustomers(312);
        emily.setNumOfReviews(123);
        emily.setTitle("Nordstrom Style Advisor");
        emily.setProfileImageUrl("https://firebasestorage.googleapis.com/v0/b/beautips-54eb3.appspot.com/o/Emily%2FRectangle%20135.png?alt=media&token=0406c870-fe0d-4264-bdf0-66b912274284");


        Stylist joe = new Stylist("Joe");
        joe.setNumOfCustomers(6543);
        joe.setNumOfReviews(876);
        joe.setTitle("Freelancer");
        joe.setProfileImageUrl("https://firebasestorage.googleapis.com/v0/b/beautips-54eb3.appspot.com/o/Joe%2FRectangle%20137.png?alt=media&token=2ace6a46-0b01-4111-a366-a6e714a89a5f");

        stylistList.add(abby);
        stylistList.add(emily);
        stylistList.add(joe);


        StylistListAdapter stylistListAdapter = new StylistListAdapter(context, stylistList);
        binding.stylistListRecyclerView.setAdapter(stylistListAdapter);
        binding.stylistListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

}
