package com.impact.tripble;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Mission implements Serializable {

    String title;          //제목
    LatLng latLng;  //좌표
    String position;   //장소명
    String contents;    //내용(미션)
    //Bitmap image;       //사진
    String complete;        //수행방법(qr, passward, gps, nfc ...)

    /*
    <그룹>
    tag: hnu
          <호스트>
          sort: 랜덤
              <미션>
    */

    public Mission(String title, LatLng latLng, String position, String contents, Bitmap image, String complete){
        this.title = title;
        this.latLng = latLng;
        this.position = position;
        this.contents = contents;
        //this.image = image;
        this.complete = complete;
    }


}
