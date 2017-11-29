package com.bocquet.yoann.tp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Info", "Menu activity onCreate.");
        setContentView(R.layout.activity_menu);
    }

    public void openGame(View v)
    {
        Intent i = new Intent(this, Jeu.class);
        this.startActivity(i);
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
