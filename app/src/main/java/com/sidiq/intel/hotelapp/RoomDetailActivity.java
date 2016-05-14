package com.sidiq.intel.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.sidiq.intel.hotelapp.helper.DbHelper;
import com.sidiq.intel.hotelapp.model.Room;

import de.hdodenhof.circleimageview.CircleImageView;

public class RoomDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgRoom1,
            imgRoom2, imgRoom3, imgRoom4;
    private TextView txtDeskripsi, txtFasilitas, txtRoomType,
        txtRate, txtMoreImages;
    private CircleImageView imgUser;
    private KenBurnsView imgMainPhoto;
    private String imgMain = "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-8130.picture525x375.jpg";
    private String imgPhoto1 = "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-5268.picture525x375.jpg";
    private String imgPhoto2 = "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-5371.picture415x295.jpg";
    private String imgPhoto3 = "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-3992.picture415x295.jpg";
    private String imgPhoto4 = "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-8130.picture525x375.jpg";
    private String userAvatar = "https://developers.google.com/experts/img/user/113340218414263722427.png";

    public static String KEY_ROOM = "key_room";
    private Room mRoom;
    private DbHelper dbHelper;
    private MenuItem itemSave, itemDelete;
    private boolean isFromDb = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        imgMainPhoto = (KenBurnsView) findViewById(R.id.img_main_photo);
        imgUser = (CircleImageView) findViewById(R.id.img_user);
        imgRoom1 = (ImageView)findViewById(R.id.img_room_1);
        imgRoom2 = (ImageView)findViewById(R.id.img_room_2);
        imgRoom3 = (ImageView)findViewById(R.id.img_room_3);
        imgRoom4 = (ImageView)findViewById(R.id.img_room_4);
        txtDeskripsi = (TextView)findViewById(R.id.txt_deskripsi);
        txtFasilitas = (TextView)findViewById(R.id.txt_fasilitas);
        txtRoomType = (TextView)findViewById(R.id.txt_room_type);
        txtRate = (TextView)findViewById(R.id.txt_rate);
        txtMoreImages = (TextView)findViewById(R.id.txt_more_images);
        txtMoreImages.setOnClickListener(this);

        dbHelper = new DbHelper(RoomDetailActivity.this);

        mRoom = getIntent().getParcelableExtra(KEY_ROOM);
        if (dbHelper.isItemAlreadyIn(mRoom.getRoomId())){
            isFromDb = true;
        }


        getSupportActionBar().setTitle("Detail Room");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String deskripsi = "Design modern minimalis design dengan " +
                "interior nyaman, berada di lokasi Legian yang strategis " +
                "untuk anda menginap"+"<br><br>"+mRoom.getRemarks();
        String fasilitas = "AC, Baju, Handuk, Mesin pembuat kopi/teh, " +
                "Air mineral botol gratis, Meja Pengering rambut dengan permintaan, " +
                "Pengering Rambut, Brankas, Internet - Wifi (gratis), " +
                "Papan setrika dengan permintaan";

        txtFasilitas.setText(fasilitas);
        txtDeskripsi.setText(Html.fromHtml(deskripsi));
        txtRate.setText(mRoom.getRate());
        txtRoomType.setText(mRoom.getName());

        //setUpPhoto(imgMain, imgMainPhoto);
        setUpPhoto(mRoom.getPhoto(), imgMainPhoto);
        setUpPhoto(userAvatar, imgUser);
        setUpPhoto(imgPhoto1, imgRoom1);
        setUpPhoto(imgPhoto2, imgRoom2);
        setUpPhoto(imgPhoto3, imgRoom3);
        setUpPhoto(imgPhoto4, imgRoom4);

    }
    private void setUpPhoto(String imageUrl, ImageView imgView){
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(R.color.grey_200)
                .error(R.color.grey_200)
                .into(imgView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        itemSave = menu.findItem(R.id.action_bookmark);
        itemDelete = menu.findItem(R.id.action_delete);

        if (isFromDb){
            itemSave.setVisible(false);
            itemDelete.setVisible(true);
        }else{
            itemSave.setVisible(true);
            itemDelete.setVisible(false);
        }

        return true;
    }

    //method untuk kontrol menu item di actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        if (item.getItemId() == R.id.action_bookmark){
            dbHelper.create(mRoom);

            itemSave.setVisible(false);
            itemDelete.setVisible(true);

            isFromDb = true;

            Toast.makeText(RoomDetailActivity.this,
                    "Item saved", Toast.LENGTH_LONG).show();
        }

        if (item.getItemId() == R.id.action_delete){

            dbHelper.deleteItem(mRoom.getRoomId());

            isFromDb = false;

            itemSave.setVisible(true);
            itemDelete.setVisible(false);

            Toast.makeText(RoomDetailActivity.this,
                    "Item deleted from bookmark", Toast.LENGTH_LONG).show();
        }

        if (item.getItemId() == R.id.action_share){
            String title = mRoom.getName();
            String message = mRoom.getRate()+" "+mRoom.getRemarks();

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                    message);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txt_more_images){
            Intent intent = new Intent(RoomDetailActivity.this, GalleryActivity.class);
            startActivity(intent);
        }
    }
}
