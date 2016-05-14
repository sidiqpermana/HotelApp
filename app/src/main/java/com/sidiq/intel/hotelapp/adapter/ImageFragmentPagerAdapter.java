package com.sidiq.intel.hotelapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sidiq.intel.hotelapp.fragment.ImageFragment;
import com.sidiq.intel.hotelapp.model.GalleryItem;

import java.util.ArrayList;

/**
 * Created by inte on 5/12/2016.
 */
public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<GalleryItem> listGallery;

    public ArrayList<GalleryItem> getListGallery() {
        return listGallery;
    }

    public void setListGallery(ArrayList<GalleryItem> listGallery) {
        this.listGallery = listGallery;
    }

    public ImageFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(listGallery.get(position));
    }

    @Override
    public int getCount() {
        return listGallery.size();
    }
}
