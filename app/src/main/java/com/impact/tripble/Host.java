package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

//예시: 창업지원단, 대덕구 ~, 창업의 이해, 042~, tag
public class Host {
    String host;
    String address;
    String title;
    String call;
    String tag;

    public Host(String host, String address, String title , String call, String tag){
        this.host = host;
        this.address =address;
        this.title = title;
        this.call = call;
        this.tag = tag;
    }

}
