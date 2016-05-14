package com.sidiq.intel.hotelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sidiq.intel.hotelapp.adapter.GalleryAdapter;
import com.sidiq.intel.hotelapp.model.GalleryItem;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    private String[] imageGallery = new String[]{
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-126.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-512.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-1767.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-2270.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-3327.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-4541.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-3313.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-4198.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-5790.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-5056.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-4775.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-4775.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-5268.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-7681.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-9932.picture525x375.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-theonelegianformerlythe1o1balilegian-hotel-8839.picture525x375.jpg"
    };
    private GridView gridView;
    private ArrayList<GalleryItem> listGallery;
    private GalleryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setTitle("Gallery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridView = (GridView)findViewById(R.id.gv_gallery);

        listGallery = new ArrayList<>();
        GalleryItem item = null;
        for (int i = 0; i < imageGallery.length; i++){
            item = new GalleryItem();
            item.setImagePath(imageGallery[i]);

            listGallery.add(item);
        }

        adapter = new GalleryAdapter(GalleryActivity.this, listGallery);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GalleryActivity.this, DetilImageActivity.class);
                intent.putExtra(DetilImageActivity.KEY_GALLERY_ITEM, listGallery);
                intent.putExtra(DetilImageActivity.KEY_POSITION, position);
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
