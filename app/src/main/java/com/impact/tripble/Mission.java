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

    // TODO: 2019-08-16-016 LatLng 변환함수 추가

    /*
    <그룹>
    tag: hnu
          <호스트>
          sort: 랜덤
              <미션>
    */

    public Mission(String title, LatLng latLng, String position, String contents, Bitmap image, String complete) {
        this.title = title;
        this.latLng = latLng;
        this.position = position;
        this.contents = contents;
        //this.image = image;
        this.complete = complete;
    }
}


//public class Mission extends Theme implements Serializable {
//
////    String title;          //제목
////    LatLng latLng;  //좌표
////    String position;   //장소명
////    String contents;    //내용(미션)
////    //Bitmap image;       //사진
////    String complete;        //수행방법(qr, passward, gps, nfc ...)
////
////    // TODO: 2019-08-16-016 LatLng 변환함수 추가
//
//    String title;
//    String latitude;
//    String longitude;
//    String context;
//    String image;
//    String playing;
//
//    public String getTitle() {
//        return this.title;
//    }
//    public String getLatitude() {
//        return this.latitude;
//    }
//    public String getLongitude() {
//        return this.longitude;
//    }
//    public String getContext() { return context; }
//    public String getImage() { return image; }
//    public String getPlaying() { return playing; }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//    public void setContext(String context) { this.context = context; }
//    public void setImage(String image) { this.image = image; }
//    public void setPlaying(String playing) { this.playing = playing; }
//
//    public LatLng getLatLng(){
//        double lat = Double.parseDouble(this.latitude);
//        double lon = Double.parseDouble(this.longitude);
//        return new LatLng(lat,lon);
//    }
//
//}
