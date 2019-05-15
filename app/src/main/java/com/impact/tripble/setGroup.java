package com.impact.tripble;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class setGroup extends AppCompatActivity {

    EditText et_title, et_reward;
    Button bt_extraMission, bt_next;
    //LinearLayout layout_mission;

    Intent receiveIntent;
    Intent sendToMission_intent, sendToFinal_intent;
    String host;
    String title, reward, position;

    Host mHost;
    Mission mMission;
    Context mContext = setGroup.this;

    ArrayList<String> positionList = new ArrayList<>();
    ArrayList<Mission> missionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_group);

        et_title = (EditText)findViewById(R.id.title);
        et_reward = (EditText)findViewById(R.id.reward);
        bt_extraMission = (Button)findViewById(R.id.extraMission);
        bt_next = (Button)findViewById(R.id.next);
        //layout_mission = (LinearLayout)findViewById(R.id.mission_layout);

        receiveIntent = getIntent();
        host =  receiveIntent.getStringExtra("host");

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 100:
                    mMission = (Mission) intent.getSerializableExtra("mission");
                    missionList.add(mMission);
                    positionList.add(mMission.position);
                    missionView(mMission);
                    break;
            }
        }
    }

    public void nextButton(){

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendToFinal_intent = new Intent(mContext, select_HostUser.class); //호스트 / 사용자 선택 액티비티로

                setHost();
                //todo 파일 저장

                startActivity(sendToFinal_intent);
                finish();
            }
        });
    }

    public Host setHost(){
        title = et_title.getText().toString();
        reward = et_reward.getText().toString();

        for(String pos : positionList){
            position += (pos+", ");
        }

        Host sHost = new Host(title, reward, position);

        return sHost;
    }

    //todo saveHost, saveMission 2개 수행.

    //미션 뷰 동적 생성//
    //onActivityResult에서 호출//
    //todo 2xN 그리드로 변경 고려
    //todo 레이아웃 박스디자인 + 상하 패딩 추가
    public void missionView(Mission mission){
        LinearLayout container = (LinearLayout)findViewById(R.id.mission_layout);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);
        container.addView(linearLayout);

        TextView title = new TextView(this);
        TextView position = new TextView(this);
        TextView contents = new TextView(this);
        TextView image = new TextView(this);
        TextView complete = new TextView(this);

        title.setText("미션명: "+ mission.title);
        position.setText("장소: " + mission.position);
        contents.setText("미션: " + mission.contents);
        image.setText("사진" + mission.image);
        complete.setText("구분(?): " + mission.complete);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        title.setLayoutParams(lp);
        position.setLayoutParams(lp);
        contents.setLayoutParams(lp);
        image.setLayoutParams(lp);
        complete.setLayoutParams(lp);

        linearLayout.addView(title);
        linearLayout.addView(position);
        linearLayout.addView(contents);
        linearLayout.addView(image);
        linearLayout.addView(complete);
    }

}
