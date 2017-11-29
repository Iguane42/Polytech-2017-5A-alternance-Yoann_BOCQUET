package com.bocquet.yoann.tp1;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Info", "Menu activity onCreate.");
        setContentView(R.layout.activity_menu);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer, new FragmentMenu());
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
        Log.d("Info", "Menu activity onPause.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Info", "Menu activity onResume.");
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
