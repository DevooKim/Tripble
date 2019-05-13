package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;

public class Mission{

    String title;          //제목
    LatLng latLng;  //좌표
    String position;    //장소명
    String contents;    //내용
    String reward;      //보상
    String image;       //사진
    String means;        //수행방법(qr, passward, gps, nfc ...)
    int sort;

    public Mission(String title, LatLng latLng, String position,  String contents, String reward, String image, String means, int sort){
        this.title = title;
        this.latLng = latLng;
        this.position = position;
        this. contents = contents;
        this. reward = reward;
        this.image = image;
        this.means = means;
        this.sort = sort;
    }

}
