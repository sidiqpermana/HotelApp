package com.sidiq.intel.hotelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sidiq.intel.hotelapp.adapter.RoomAdapter;
import com.sidiq.intel.hotelapp.api.request.GetRoomRequest;
import com.sidiq.intel.hotelapp.api.response.GetRoomResponse;
import com.sidiq.intel.hotelapp.model.Room;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GetRoomRequest.OnGetRoomRequestListener{
    private String[][] data = new String[][]{
            {"1","Super Deluxe Room Only", "Tidak termasuk sarapan, Internet - Wifi Gratis. " +
                    "Stay 3 Nights get free 1x breakfast for 2 pax", "IDR 341.250",
                    "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--5181.picture525x375.jpg"
                },
            {"2", "Super Deluxe with Breakfast", "Sarapan Pagi Termasuk. Internet Gratis",
                "IDR 403.750", "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--7800.picture525x375.jpg"},
            {"3", "Super Deluxe Pool Access", "Tidak termasuk sarapan pagi. Internet - wifi gratis",
                "IDR 617.500", "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--6675.picture525x375.jpg"}
    };

    private ListView lvRoom;
    private ProgressBar progressBar;
    private GetRoomRequest getRoomRequest;
    private ArrayList<Room> listRoom;
    private RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        lvRoom = (ListView)findViewById(R.id.lv_room);

        getRoomRequest = new GetRoomRequest();
        getRoomRequest.setListener(this);

        progressBar.setVisibility(View.VISIBLE);
        lvRoom.setVisibility(View.INVISIBLE);

        listRoom = new ArrayList<>();

        getRoomRequest.callApi();
        /**
        Room mRoom = null;
        for (int i = 0; i < data.length; i++){
            mRoom = new Room();
            mRoom.setRoomId(Integer.parseInt(data[i][0]));
            mRoom.setName(data[i][1]);
            mRoom.setRemarks(data[i][2]);
            mRoom.setRate(data[i][3]);
            mRoom.setPhoto(data[i][4]);

            listRoom.add(mRoom);
        }

        adapter = new RoomAdapter(HomeActivity.this, listRoom);
        lvRoom.setAdapter(adapter);
        **/

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, RoomDetailActivity.class);
                intent.putExtra(RoomDetailActivity.KEY_ROOM, listRoom.get(position));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(HomeActivity.this, BookmarkActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(HomeActivity.this, GalleryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onGetRoomRequestSuccess(GetRoomResponse getRoomResponse) {
        progressBar.setVisibility(View.GONE);
        lvRoom.setVisibility(View.VISIBLE);

        listRoom.addAll(getRoomResponse.getListRoom());

        adapter = new RoomAdapter(HomeActivity.this, listRoom);
        lvRoom.setAdapter(adapter);
    }

    @Override
    public void onGetRoomRequestFailed(String errorMessage) {
        Snackbar.make(lvRoom, errorMessage, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        lvRoom.setVisibility(View.INVISIBLE);

                        getRoomRequest.callApi();
                    }
                });
    }
}
