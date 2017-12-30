package com.bocquet.yoann.tp1.UI;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocquet.yoann.tp1.Models.DownLoadImageTask;
import com.bocquet.yoann.tp1.R;



public class BeerDetails extends Fragment {

    private static final String ARG_ABV = "abv";
    private static final String ARG_DESC = "description";
    private static final String ARG_FIRST_BREW = "firstBrewed";
    private static final String ARG_IMAGE_URL = "imageUrl";
    private static final String ARG_NAME = "name";
    private static final String ARG_TAGLINE = "tagline";

    private Float abv;
    private String description;
    private String firstBrewed;
    private String imageUrl;
    private String name;
    private String tagline;

    public BeerDetails() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            abv = getArguments().getFloat(ARG_ABV);
            description = getArguments().getString(ARG_DESC);
            firstBrewed = getArguments().getString(ARG_FIRST_BREW);
            imageUrl = getArguments().getString(ARG_IMAGE_URL);
            name = getArguments().getString(ARG_NAME);
            tagline = getArguments().getString(ARG_TAGLINE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_beer_details, container, false);
        TextView nameView = (TextView)(view.findViewById(R.id.beerName_details));
        nameView.setText(name);
        TextView taglineView = (TextView)(view.findViewById(R.id.beerTagline_details));
        taglineView.setText(tagline);
        TextView descView = (TextView)(view.findViewById(R.id.beerDesc_details));
        descView.setText(description);
        TextView abvView = (TextView)(view.findViewById(R.id.beerAlc_details));
        abvView.setText("Alc. : "+abv+"%");
        TextView firstBrewView = (TextView)(view.findViewById(R.id.beerFirstBrew_details));
        firstBrewView.setText(getResources().getString(R.string.first_brewed)+" : "+firstBrewed);
        new DownLoadImageTask((ImageView)view.findViewById(R.id.beerImage_details)).execute(imageUrl);

        return view;
    }

}
