package com.impact.tripble;

import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Mission implements Serializable {

    String title;          //제목
    LatLng latLng;  //좌표
    String position = null;    //장소명
    String contents;    //내용
    String reward = null;      //보상
    String image;       //사진
    String complete;        //수행방법(qr, passward, gps, nfc ...)
    String tag;

    /*
    <그룹>
    tag: hnu
          <호스트>
          sort: 랜덤
              <미션>
    */

    public Mission(String title, LatLng latLng, String position,  String contents, String reward, String image, String complete, String tag, String host, String sort){
        this.title = title;
        this.latLng = latLng;
        this.position = position;
        this.contents = contents;
        this.reward = reward;
        this.image = image;
        this.complete = complete;
        this.tag = tag+"_"+host +"_"+sort+"_"+position;
    }


}
