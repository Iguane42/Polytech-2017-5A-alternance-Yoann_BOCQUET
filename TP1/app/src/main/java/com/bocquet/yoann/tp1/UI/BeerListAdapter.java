package com.bocquet.yoann.tp1.UI;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocquet.yoann.tp1.Models.Beer;
import com.bocquet.yoann.tp1.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Epulapp on 30/11/2017.
 */

public class BeerListAdapter extends ArrayAdapter<Beer> {

    private final List<Beer> mItems;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public BeerListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Beer> objects) {
        super(context, resource, objects);

        mItems = objects;
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.list_element, null);
            holder = new ViewHolder();
            holder.beerName = (TextView)convertView.findViewById(R.id.beer_name);
            holder.alcVol = (TextView)convertView.findViewById(R.id.alc_vol);
            holder.thumbnail = (ImageView)convertView.findViewById(R.id.beer_pic);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Beer item = getItem(position);
        if (item != null)
        {
            // This is where you set up the views.
            // This is just an example of what you could do.
            holder.beerName.setText(item.getName());
            holder.alcVol.setText(mContext.getString(R.string.alcohol_short)+" "+String.valueOf(item.getAbv())+"%");
            new DownloadImageTask(holder.thumbnail).execute(item.getImageUrl());

            /*holder.button.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            holder.video.setVideoURI(item.getURI());
                            holder.video.start();
                        }
                    }
            );*/
        }

        return convertView;
    }

    @Override
    public int getCount()
    {
        return mItems.size();
    }




    @Override
    public Beer getItem(int position)
    {
        return mItems.get(position);
    }

    public class ViewHolder
    {
        TextView beerName;
        TextView alcVol;
        ImageView thumbnail;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            Log.d("Info", "Loading image..."+urls[0]);
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
