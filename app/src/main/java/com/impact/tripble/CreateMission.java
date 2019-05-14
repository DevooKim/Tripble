package com.impact.tripble;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateMission extends AppCompatActivity {

    String host;
    String tag = "hnu";
    String position;
    int sort;

    private Button button;
    Intent receiveHost_intent, receiveMission_intent,send_intent;
    ArrayList<Mission> missionList;
    Gson gson;
    Mission mMission;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mission);

        clickExtraButton();
        mMission = receiveClass();

        host = receiveHost_intent.getExtras().getString("name");
        sort= receiveHost_intent.getExtras().getInt("sort");

        //missionList.add(turnToJson(mMission));    //mission객체를 json으로 변환 후 리스트에 삽입.
        addList(mMission);
        saveData(missionList);

    }

    public void clickExtraButton(){
        button = (Button)findViewById(R.id.extraMission);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_intent = new Intent(CreateMission.this, ExtraMission.class);
                send_intent.putExtra("sort",sort);
                send_intent.putExtra("host", host);

                startActivity(send_intent);

                //todo Json (Host, mission) 구현.
                //todo 완료버튼 누를 시. + host데이터도 같이 저장
            }
        });
    }

    public Mission receiveClass(){
        receiveMission_intent = getIntent();

        Mission object = (Mission) receiveMission_intent.getSerializableExtra("Mission");

        return object;
    }

    //json이름은 tag_host_sort_mission장소
    public ArrayList<Mission> addList(Mission mission){
        mission = mMission;
        missionList.add(mission);
        return missionList;
    }

    public void saveData(ArrayList missionList){
        gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Mission>>() {}.getType();
        String json = gson.toJson(missionList, listType);

        SharedPreferences sp = getSharedPreferences("mission", MODE_PRIVATE);   //sp 파일이름
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mission", json);  //id , key
        editor.commit();
    }


}
