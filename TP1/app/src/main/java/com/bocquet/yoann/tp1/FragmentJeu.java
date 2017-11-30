package com.bocquet.yoann.tp1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentJeu extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = new Intent();
        i.setAction("com.bocquet.yoann.tp1.notification");
        i.putExtra("data",getResources().getString(R.string.game_launched));
        this.getActivity().sendBroadcast(i);
        return inflater.inflate(R.layout.fragment_fragment_jeu, container, false);
    }


}
