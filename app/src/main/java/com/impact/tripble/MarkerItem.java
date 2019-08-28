package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerItem implements ClusterItem {


    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    public MarkerItem(double lat, double lng, String title, String snippet){
        this.mPosition = new LatLng(lat, lng);
        this.mTitle = title;
        this. mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {

        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
