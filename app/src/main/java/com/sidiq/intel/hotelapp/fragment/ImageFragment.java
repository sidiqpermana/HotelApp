package com.sidiq.intel.hotelapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sidiq.intel.hotelapp.R;
import com.sidiq.intel.hotelapp.model.GalleryItem;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    private PhotoView imgDetil;
    private GalleryItem galleryItem;

    public GalleryItem getGalleryItem() {
        return galleryItem;
    }

    public void setGalleryItem(GalleryItem galleryItem) {
        this.galleryItem = galleryItem;
    }

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(GalleryItem item){
        ImageFragment fragment = new ImageFragment();
        fragment.setGalleryItem(item);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image, container, false);
        imgDetil = (PhotoView) view.findViewById(R.id.img_detil);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PhotoViewAttacher photoView = new PhotoViewAttacher(imgDetil);
        Glide.with(getActivity())
                .load(getGalleryItem().getImagePath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(android.R.color.black)
                .error(R.color.grey_200)
                .into(imgDetil);
    }
}
