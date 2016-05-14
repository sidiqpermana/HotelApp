package com.sidiq.intel.hotelapp.helper;

import android.app.Activity;
import android.content.Context;

import com.sidiq.intel.hotelapp.model.Room;
import com.sidiq.intel.hotelapp.model.RoomTable;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by inte on 5/12/2016.
 */
public class DbHelper {
    Context mContext;
    public Realm mRealm;
    public DbHelper(Context mContext){
        this.mContext = mContext;
        mRealm = Realm.getInstance(
                new RealmConfiguration.Builder(mContext)
                        .name("myOtherRealm.realm")
                        .build());
    }

    public void create(Room room){
        if (!isItemAlreadyIn(room.getRoomId())){
            mRealm.beginTransaction();
            RoomTable item = mRealm.createObject(RoomTable.class);
            item.setId(System.currentTimeMillis());
            item.setName(room.getName());
            item.setPhoto(room.getPhoto());
            item.setRate(room.getRate());
            item.setRemarks(room.getRemarks());
            item.setRoomId(room.getRoomId());
            mRealm.commitTransaction();
        }
    }

    public ArrayList<RoomTable> getAllItems(){
        ArrayList<RoomTable> list = null;
        RealmResults<RoomTable> results = mRealm.where(RoomTable.class).findAll();
        if (results.size() > 0){
            list = new ArrayList<>();
            for (RoomTable item : results){
                list.add(item);
            }
        }

        return list;
    }

    public boolean isItemAlreadyIn(int roomId){
        RoomTable mRoomTable = mRealm.where(RoomTable.class)
                .equalTo("roomId", roomId).findFirst();
        if (mRoomTable != null){return true;}else{return false;}
    }

    public void deleteItem(int roomId){
        mRealm.beginTransaction();
        RealmResults<RoomTable> items = mRealm.where(RoomTable.class).equalTo("roomId", roomId).findAll();
        RoomTable item = items.get(0);
        item.deleteFromRealm();
        mRealm.commitTransaction();
    }
}
