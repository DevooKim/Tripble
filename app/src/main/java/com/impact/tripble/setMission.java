package com.impact.tripble;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class setMission extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_list);
        container = (LinearLayout) findViewById(R.id.mission_list);
        Mission mission1 = new Mission(36.353852, 127.423138, "DFGN", "제목2", "내용2", "보상2", "사진2", "qr", "1");
        Mission mission2 = new Mission(36.356325, 127.419504, "공과대", "제목", "내용", "보상", "사진", "qr", "1");

        textView(mission1);
        textView(mission2);
    }

    public void textView(Mission mission){

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);
        container.addView(linearLayout);

        TextView view1 = new TextView(this);
        TextView view2 = new TextView(this);
        TextView view3 = new TextView(this);
        view1.setText(mission.title);
        view1.setTextSize(20);
        view2.setText(mission.position);
        view2.setTextSize(15);
        view3.setText(mission.reward);
        view3.setTextSize(15);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        view1.setLayoutParams(lp);
        view2.setLayoutParams(lp);
        view3.setLayoutParams(lp);

        linearLayout.addView(view1);
        linearLayout.addView(view2);
        linearLayout.addView(view3);

    }

    public Mission setMission(){
        Mission mission;
        mission = new Mission(36.356325, 127.419504, "공과대", "제목", "내용", "보상", "사진", "qr", "1");

        return mission;
    }
}
