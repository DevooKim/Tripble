package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

public class Mission{

    LatLng latLng;
    String position;    //장소명
    String title;          //제목
    String contents;    //내용
    String reward;      //보상
    String image;       //사진
    String means;        //수행방법(qr, passward, gps, nfc ...)
    String tag;

    public Mission(double v1, double v2, String position, String title, String contents, String reward, String image, String means, String tag){
        this.latLng = new LatLng(v1, v2);
        this.position = position;
        this.title = title;
        this. contents = contents;
        this. reward = reward;
        this.image = image;
        this.means = means;
        this.tag = tag;
    }

}
