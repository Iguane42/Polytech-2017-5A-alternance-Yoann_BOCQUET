package com.bocquet.yoann.tp1.UI;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bocquet.yoann.tp1.R;


public class FragmentJeu extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_jeu, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent();
        i.setAction("com.bocquet.yoann.tp1.notification");
        i.putExtra("data",getResources().getString(R.string.game_launched));
        this.getActivity().sendBroadcast(i);
    }
}
