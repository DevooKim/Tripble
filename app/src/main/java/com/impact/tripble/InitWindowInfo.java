package com.impact.tripble;
//지도에서 대표마커 정보창 클릭 시 나오는 동적뷰 액티비티
//호스트들이 대표적으로 보여진다.
//todo
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class InitWindowInfo extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_list_new);
        //container = (LinearLayout) findViewById(R.id.mission_list);
        LatLng latLng1 = new LatLng(36.353852, 127.423138);
        LatLng latLng2 = new LatLng(36.356325, 127.419504);
        //Mission mission1 = new Mission( "제목1", latLng1,"위치1", "내용1", "보상1", "사진1", "qr", 1);
        //Mission mission2 = new Mission("제목2", latLng2,"위치2", "내용2", "보상2", "사진2", "nfc", 1);

        //textView(mission1);
        //textView(mission2);
    }

    public void textView(Mission mission){

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);
        container.addView(linearLayout);

        TextView view1 = new TextView(this);
        TextView view2 = new TextView(this);
        view1.setText(mission.title);
        view1.setTextSize(20);
        view2.setText(mission.position);
        view2.setTextSize(15);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        view1.setLayoutParams(lp);
        view2.setLayoutParams(lp);

        linearLayout.addView(view1);
        linearLayout.addView(view2);

    }

    public void setMission(){
    //public Mission InitWindowInfo(){
        Mission mission;
        LatLng latLng = new LatLng(36.356325, 127.419504);
        //mission = new Mission( "제목",latLng, "장소", "내용", "보상", "사진", "qr", 1);

        //return mission;
    }
}
