package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

public class Host {
    String host;
    String title;
    LatLng latLng;
    String position;
    String call;
    String tag;

    public Host(String host, String title, LatLng latLng, String position, String call, String tag){
        this.host = host;
        this.title = title;
        this.latLng = latLng;
        this.position =position;
        this.call = call;
        this.tag = tag;
    }

}
