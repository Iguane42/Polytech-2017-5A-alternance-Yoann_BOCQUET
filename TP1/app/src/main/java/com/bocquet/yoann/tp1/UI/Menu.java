package com.bocquet.yoann.tp1.UI;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bocquet.yoann.tp1.Models.Beer;
import com.bocquet.yoann.tp1.Models.BeerAPI;
import com.bocquet.yoann.tp1.R;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {
    private BroadcastReceiver NotificationReceiver;
    private IntentFilter intentfilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("Info", "Menu activity onCreate.");
        setContentView(R.layout.activity_menu);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer, new FragmentMenu());
        fragmentTransaction.commit();
//        intentfilter = new IntentFilter();
//        intentfilter.addAction("com.bocquet.yoann.tp1.notification");
//        NotificationReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.mipmap.ic_launcher_round)
//                        .setContentTitle("ZeQuizz")
//                        .setContentText(intent.getStringExtra("data"));
//                NotificationManager mNotifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                mNotifManager.notify(1,mBuilder.build());
//            }
//        };

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
        //Log.d("Info", "Menu activity onPause.");
        //unregisterReceiver(NotificationReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("Info", "Menu activity onResume.");
        //registerReceiver(NotificationReceiver, intentfilter);
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
