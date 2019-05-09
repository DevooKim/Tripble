package com.impact.tripble;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindow_host implements GoogleMap.InfoWindowAdapter{

    private Context context;

    public CustomInfoWindow_host(Context context){ this.context = context;}

    @Override
    public View getInfoWindow(Marker marker){ return null;}

    @Override
    public View getInfoContents(Marker marker){
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.info_window,null);

        return view;
    }
}
