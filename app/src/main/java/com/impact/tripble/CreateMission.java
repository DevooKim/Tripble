package com.impact.tripble;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class CreateMission extends AppCompatActivity {

    String host;
    String position;
    int sort;
    Context mContext = CreateMission.this;

    private Button button;
    Intent receiveHost_intent, send_intent;

    //String spName;
    ArrayList<Mission> missionList = new ArrayList<Mission>();
    Mission mMission;
    Gson gson = new GsonBuilder().create();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mission);
        receiveHost_intent = getIntent();
        host = receiveHost_intent.getExtras().getString("name");
        sort= receiveHost_intent.getExtras().getInt("sort");    //todo 다른 액티비티 방문 후 지워지므로 shared로 호스트 저장해서 사용.

        clickExtraButton();

        Button save_button = (Button)findViewById(R.id.save);
        Button load_button = (Button)findViewById(R.id.load);
        Button del_button = (Button)findViewById(R.id.del);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveData();
            }
        });
        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchData();
            }
        });
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelData();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1000:
                    mMission = (Mission) data.getSerializableExtra("mission");
                    missionList.add(mMission);

                    //spName = mMission.tag;
                    break;
            }
        }
    }

    public void clickExtraButton(){
        button = (Button)findViewById(R.id.extraMission);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send_intent = new Intent(mContext, ExtraMission.class);
                send_intent.putExtra("sort",sort);
                send_intent.putExtra("host", host);

                startActivityForResult(send_intent,1000);
            }
        });
    }

   public void onSaveData(){
       Toast.makeText(mContext, "save", Toast.LENGTH_SHORT).show(); //todo이름은 spName (mMission.tag);

        Type listType = new TypeToken<ArrayList<Mission>>(){}.getType();
        String json = gson.toJson(missionList,listType);

        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mission",json);
        editor.commit();
    }

    public void onSearchData(){
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        String strMission = sp.getString("mission", "");

        Type listType = new TypeToken<ArrayList<Mission>>(){}.getType();
        ArrayList<Mission> dataList = gson.fromJson(strMission,listType);   //todo 데이터 없을때 조건 추가

        for(Mission data : dataList){
            Log.d("PRINT", data.position);
            Toast.makeText(mContext, "load: "+data.position, Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelData(){
        Toast.makeText(mContext, "Del", Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        //todo 리스트도 비워야한다.
        //todo 저장 후 하나의 미션만 지우려면 전체를 불러와서 리스트에서 미션제거 후 다시 저장.
    }

    public void onDelMission(){
        //todo 미션 추가하고 삭제 (리스트에서 제거)
    }



}
