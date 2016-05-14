package com.sidiq.intel.hotelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sidiq.intel.hotelapp.R;
import com.sidiq.intel.hotelapp.model.GalleryItem;

import java.util.ArrayList;

/**
 * Created by inte on 5/12/2016.
 */
public class GalleryAdapter extends BaseAdapter {
    private ArrayList<GalleryItem> listGallery;
    private Activity activity;

    public GalleryAdapter(Activity activity, ArrayList<GalleryItem> listGallery){
        this.listGallery = listGallery;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listGallery.size();
    }

    @Override
    public Object getItem(int position) {
        return listGallery.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gallery, null);
            holder.imgItem = (ImageView)convertView.findViewById(R.id.img_item_gallery);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        GalleryItem item = (GalleryItem)getItem(position);
        Glide.with(activity)
                .load(item.getImagePath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(R.color.grey_200)
                .error(R.color.grey_200)
                .into(holder.imgItem);
        return convertView;
    }

    static class ViewHolder{
        ImageView imgItem;
    }
}
