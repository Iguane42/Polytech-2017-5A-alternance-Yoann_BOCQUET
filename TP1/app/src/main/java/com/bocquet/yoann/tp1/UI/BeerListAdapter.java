package com.bocquet.yoann.tp1.UI;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocquet.yoann.tp1.Models.Beer;
import com.bocquet.yoann.tp1.Models.DownLoadImageTask;
import com.bocquet.yoann.tp1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Epulapp on 30/11/2017.
 */

public class BeerListAdapter extends ArrayAdapter<Beer> {

    private final List<Beer> mItems;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<DownLoadImageTask> dlList;
    private boolean stopped;

    public BeerListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Beer> objects) {
        super(context, resource, objects);
        dlList = new ArrayList<DownLoadImageTask>();
        mItems = objects;
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        stopped = false;
    }

    public void stop()
    {
        for(DownLoadImageTask dl :dlList) {
            if (!dl.isCancelled()) {
                dl.cancel(true);
            }
        }
        stopped = true;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
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

        final Beer item = getItem(position);
        if (item != null)
        {

            holder.beerName.setText(item.getName());
            holder.alcVol.setText(mContext.getString(R.string.alcohol_short)+" "+String.valueOf(item.getAbv())+"%");
            DownLoadImageTask dl = new DownLoadImageTask(holder.thumbnail);
            dl.execute(item.getImageUrl());
            dlList.add(dl);
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


}
