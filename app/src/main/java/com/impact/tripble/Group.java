package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

//예시: 마커좌표, 한남대, hnu
public class Group {
    LatLng latLng;
    String name;
    String tag;

    public Group(LatLng latLng, String name, String tag){
        this.latLng = latLng;
        this.name = name;
        this.tag = tag;
    }

}
