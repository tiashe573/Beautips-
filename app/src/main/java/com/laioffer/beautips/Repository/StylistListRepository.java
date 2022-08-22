package com.laioffer.beautips.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.beautips.Models.Stylist;
import com.laioffer.beautips.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StylistListRepository {
    private final com.laioffer.beautips.Network.StylistProfileApi StylistProfileApi;
    Stylist stylistInfo = null;

    public StylistListRepository(Context context) {
        StylistProfileApi = RetrofitClient.newInstance(context).create(com.laioffer.beautips.Network.StylistProfileApi.class);

    }

    public StylistListRepository(com.laioffer.beautips.Network.StylistProfileApi stylistProfileApi) {
        StylistProfileApi = stylistProfileApi;
    }

    public MutableLiveData<Stylist> getStylistProfile(String name) {
        final MutableLiveData<Stylist> result = new MutableLiveData<>();
        Stylist stylistName = new Stylist(name);
        Log.i("test", stylistName.toString());
        Call<Stylist> callResult = StylistProfileApi.getStylistInfo(stylistName);

        callResult.enqueue(new Callback<Stylist>() {
            @Override
            public void onResponse(Call<Stylist> call, Response<Stylist> response) {
                if (response.isSuccessful()) {
                    result.setValue(response.body());
                    Log.i("Successful", response.body().toString());
                } else {
                    Log.i("Failure status code:", String.valueOf(response.code()));
                    result.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Stylist> call, Throwable t) {
                Log.i("Sth is wrong", t.toString());
            }
        });
        return result;
    }
}
