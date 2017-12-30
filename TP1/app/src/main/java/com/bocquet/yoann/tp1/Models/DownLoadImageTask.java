package com.bocquet.yoann.tp1.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Epulapp on 30/12/2017.
 */

public class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownLoadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        Log.d("Info", "Loading image..."+urls[0]);
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            URL url = new java.net.URL(urldisplay);
            InputStream in = url.openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            this.cancel(true);
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
