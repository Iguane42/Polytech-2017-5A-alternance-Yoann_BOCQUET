package com.bocquet.yoann.tp1.UI;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bocquet.yoann.tp1.R;

public class Menu extends AppCompatActivity {
    private BroadcastReceiver NotificationReceiver;
    private IntentFilter intentfilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment menu = new FragmentMenu();
        fragmentTransaction.replace(R.id.FragmentContainer, menu);
        fragmentTransaction.commit();

    }



    public void openGame(View v)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_left_in, R.animator.slide_left_out);
        fragmentTransaction.replace(R.id.FragmentContainer, new FragmentJeu());
        fragmentTransaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Info", "Menu activity onRestart.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Info", "Menu activity onStart.");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Info", "Menu activity onDestroy.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Info", "Menu activity onStop.");
    }
}
