package com.bocquet.yoann.tp1.UI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    //private Activity activity;
    private boolean stopped;
    private View view;
    private int page;
    private int perPage = 20;

    @Override
    public void onAttach(Context context)
    {
        Log.d("Info", "Called onResume on FragmentMenu");
        super.onAttach(context);
    }

    @Override
    public void onResume()
    {
        page = 1;
        Log.d("Info", "Called onResume on FragmentMenu");
        super.onResume();
        stopped = false;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        Log.d("Info", "Called onCreateView on FragmentMenu");

        super.onCreateView(inflater, container, savedInstanceState);

        beerAPI = new BeerAPI();

        //activity = this.getActivity();

        view = inflater.inflate(R.layout.fragment_fragment_menu, container, false);

        return view;
    }


    private void displayBeerList(List<Beer> beers)
    {
        final Activity activity = getActivity();
        //activity.runOnUiThread(new Runnable() {
            //public void run() {

                beerAdapter = new BeerListAdapter(activity, R.layout.list_element, beers);
                ListView list = ((ListView)activity.findViewById(R.id.beer_list));
                list.setAdapter(beerAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        beerAdapter.stop();
                        stopped = true;
                        Log.d("Info", "Item "+i+" clicked.");
                        Bundle data = new Bundle();
                        Beer beer = beerAdapter.getItem(i);
                        data.putFloat("abv", beer.getAbv());
                        data.putString("description", beer.getDescription());
                        data.putString("firstBrewed", beer.getFirstBrewed());
                        data.putString("imageUrl", beer.getImageUrl());
                        data.putString("name", beer.getName());
                        data.putString("tagline", beer.getTagline());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        BeerDetails details = new BeerDetails();
                        details.setArguments(data);
                        ft.replace(R.id.FragmentContainer, details).addToBackStack(null);
                        ft.commit();
                    }
                });

            //}
        //});
    }


    private void loadNext()
    {
        ++page;
        beerAPI.getBeerList(page,perPage).enqueue(new Callback<List<Beer>>() {

            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {

                if (!response.body().isEmpty()) {
                    Log.d("Info", "Response from API : "+response.body().get(0).getName());
                    beerAdapter.addAll(response.body());
                    if (!stopped) {
                        loadNext();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
    }




}
