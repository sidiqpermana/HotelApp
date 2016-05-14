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
import com.sidiq.intel.hotelapp.model.Room;

import java.util.ArrayList;

/**
 * Created by inte on 5/11/2016.
 */
public class RoomAdapter extends BaseAdapter {
    private ArrayList<Room> listRoom;
    private Activity activity;

    public RoomAdapter(Activity activity, ArrayList<Room> listRoom){
        this.activity = activity;
        this.listRoom = listRoom;
    }

    @Override
    public int getCount() {
        return listRoom.size();
    }

    @Override
    public Object getItem(int position) {
        return listRoom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_room_type, null);
            holder = new ViewHolder();
            holder.imgPhoto = (ImageView)convertView
                    .findViewById(R.id.img_item_list_room_type);
            holder.txtType = (TextView)convertView
                    .findViewById(R.id.txt_item_list_room_type);
            holder.txtRate = (TextView)convertView
                    .findViewById(R.id.txt_item_list_room_rate);
            holder.txtRemarks = (TextView)convertView
                    .findViewById(R.id.txt_item_list_room_remarks);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        Room itemRoom = (Room)getItem(position);

        Glide.with(activity)
                .load(itemRoom.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(R.color.grey_200)
                .error(R.color.grey_200)
                .into(holder.imgPhoto);

        holder.txtRate.setText(itemRoom.getRate());
        holder.txtRemarks.setText(itemRoom.getRemarks());
        holder.txtType.setText(itemRoom.getName());

        return convertView;
    }

    static class ViewHolder{
        ImageView imgPhoto;
        TextView txtType, txtRate, txtRemarks;
    }
}
