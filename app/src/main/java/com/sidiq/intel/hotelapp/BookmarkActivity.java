package com.sidiq.intel.hotelapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sidiq.intel.hotelapp.adapter.RoomTableAdapter;
import com.sidiq.intel.hotelapp.helper.DbHelper;
import com.sidiq.intel.hotelapp.model.Room;
import com.sidiq.intel.hotelapp.model.RoomTable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkActivity extends AppCompatActivity {
    @BindView(R.id.lv_bookmark)
    ListView lvBookmark;

    private ArrayList<RoomTable> list;
    private RoomTableAdapter adapter;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Bookmark");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DbHelper(BookmarkActivity.this);

        list = dbHelper.getAllItems();

        if (list != null && !list.isEmpty()){
            adapter = new RoomTableAdapter(BookmarkActivity.this, list);
            lvBookmark.setAdapter(adapter);
        }else{
            Snackbar.make(lvBookmark, "No items found", Snackbar.LENGTH_LONG).show();
        }

        lvBookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RoomTable roomTable = list.get(position);

                Room room = new Room();
                room.setRoomId(roomTable.getRoomId());
                room.setRemarks(roomTable.getRemarks());
                room.setRate(roomTable.getRate());
                room.setName(roomTable.getName());
                room.setPhoto(roomTable.getPhoto());

                Intent intent = new Intent(BookmarkActivity.this, RoomDetailActivity.class);
                intent.putExtra(RoomDetailActivity.KEY_ROOM, room);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
