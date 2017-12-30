package com.bocquet.yoann.tp1.UI;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bocquet.yoann.tp1.Models.Beer;
import com.bocquet.yoann.tp1.Models.BeerAPI;
import com.bocquet.yoann.tp1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMenu extends Fragment {

    private BeerAPI beerAPI;
    private BeerListAdapter beerAdapter;
    private int page = 1;
    private int perPage = 20;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        //if (beerAPI == null) {
            beerAPI = new BeerAPI();
        //}

        activity = this.getActivity();
        beerAPI.getBeerList(page,perPage).enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    displayBeerList(response.body());
                    loadNext();
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
        return inflater.inflate(R.layout.fragment_fragment_menu, container, false);
    }


    private void displayBeerList(final List<Beer> beers)
    {

        activity.runOnUiThread(new Runnable() {
            public void run() {

                beerAdapter = new BeerListAdapter(activity, R.layout.list_element, beers);

                ((ListView)activity.findViewById(R.id.beer_list)).setAdapter(beerAdapter);

            }
        });
    }


    private void loadNext()
    {
        ++page;
        beerAPI.getBeerList(page,perPage).enqueue(new Callback<List<Beer>>() {

            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (!response.body().isEmpty()) {
                    beerAdapter.addAll(response.body());
                    loadNext();
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
    }


}
