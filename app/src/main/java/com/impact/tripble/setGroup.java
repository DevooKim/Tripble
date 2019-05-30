package com.impact.tripble;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class setGroup extends AppCompatActivity {

    //EditText et_title, et_reward;
    Button bt_extraMission, bt_next;
    //LinearLayout layout_mission;

    Intent sendToMission_intent, sendToFinal_intent;
    String host;
    String title, /*reward,*/ position;

    Host mHost;
    Mission mMission;
    Context mContext = setGroup.this;

    //ArrayList<String> positionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_add);

        //et_title = (EditText)findViewById(R.id.title);
        //et_reward = (EditText)findViewById(R.id.reward);
        bt_extraMission = (Button)findViewById(R.id.extraMission);
        bt_next = (Button)findViewById(R.id.next);
        //layout_mission = (LinearLayout)findViewById(R.id.mission_layout);

        extraMission();
        nextButton();
    }

    public void extraMission(){

        bt_extraMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMission_intent = new Intent(mContext, setMission.class);

                startActivityForResult(sendToMission_intent,100);
            }
        });

        //missionView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 100:
                    //mMission = (Mission) intent.getSerializableExtra("mission");
                    //positionList.add(mMission.position);
                    //missionView(mMission);

                    byte[] arr = intent.getByteArrayExtra("image");
                    Toast.makeText(this,Integer.toString(arr.length),Toast.LENGTH_LONG).show();
                    Bitmap image = BitmapFactory.decodeByteArray(arr,0,arr.length);

                    String title=intent.getStringExtra("title");
                    String position=intent.getStringExtra("position");
                    String contents=intent.getStringExtra("contents");
                    String complete=intent.getStringExtra("complete");

                    missionView(title, position, contents, image, complete);
                    break;
            }
        }
    }

    public void nextButton(){

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendToFinal_intent = new Intent(mContext, select_HostUser.class); //호스트 / 사용자 선택 액티비티로

                //setHost();
                //todo 파일 저장

                startActivity(sendToFinal_intent);
                finish();
            }
        });
    }

    /*public Host setHost(){
        title = et_title.getText().toString();
        reward = et_reward.getText().toString();

        for(String pos : positionList){
            position += (pos+", ");
        }

        Host sHost = new Host(title, reward, position);

        return sHost;
    }*/

    //todo saveHost, saveMission 2개 수행.

    //미션 뷰 동적 생성//
    //onActivityResult에서 호출//
    //todo 2xN 그리드로 변경 고려
    //todo 레이아웃 박스디자인 + 상하 패딩 추가
    public void missionView(String title, String position, String contents, Bitmap image, String complete){
        LinearLayout container = (LinearLayout)findViewById(R.id.mission_layout);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);
        container.addView(linearLayout);

        TextView titleView = new TextView(this);
        TextView positionView = new TextView(this);
        TextView contentsView = new TextView(this);
        ImageView imageView = new ImageView(this);
        TextView completeView = new TextView(this);

//        title.setText("미션명: "+ mission.title);
//        position.setText("장소: " + mission.position);
//        contents.setText("미션: " + mission.contents);
//        //image.setImageBitmap(mission.image);
//        complete.setText("수행방법: " + mission.complete);


        titleView.setText("미션명: "+ title);
        positionView.setText("장소: " + position);
        contentsView.setText("미션: " + contents);
        imageView.setImageBitmap(image);
        completeView.setText("수행방법: " +complete);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        titleView.setLayoutParams(lp);
        positionView.setLayoutParams(lp);
        contentsView.setLayoutParams(lp);
        imageView.setLayoutParams(lp);
        completeView.setLayoutParams(lp);

        linearLayout.addView(titleView);
        linearLayout.addView(positionView);
        linearLayout.addView(contentsView);
        linearLayout.addView(imageView);
        linearLayout.addView(completeView);
    }

}
