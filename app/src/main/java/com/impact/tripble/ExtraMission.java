package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ExtraMission extends AppCompatActivity {

    Button bt_latLng, bt_openMap, bt_next;
    EditText et_searchPos, et_title, et_position, et_mission, et_reward, et_image;
    Spinner spinner;

    String way;
    String title;
    String position;
    String mission;
    String reward;
    private int tag;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_mission);

        setMission();
    }

    public void setMission(){
        Intent intent = getIntent();
        bt_latLng = (Button)findViewById(R.id.bt_searchLatLng);
        bt_openMap = (Button)findViewById(R.id.bt_openMap);
        bt_next = (Button)findViewById(R.id.bt_next);
        et_searchPos = (EditText) findViewById(R.id.et_searchPos);
        et_title = (EditText) findViewById(R.id.et_title);
        et_position = (EditText) findViewById(R.id.et_position);
        et_mission = (EditText) findViewById(R.id.et_mission);
        et_reward = (EditText) findViewById(R.id.et_reward);
        et_image = (EditText) findViewById(R.id.image);
        spinner = (Spinner) findViewById(R.id.spinner);




        int tag = intent.getExtras().getInt("tag");
        title = et_title.getText().toString();
        position = et_position.getText().toString();
        mission = et_mission.getText().toString();
        reward = et_reward.getText().toString();
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                way = spinner.getSelectedItem().toString();
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(ExtraMission.this, CreateMission.class);
                intent1.putExtra("title",title);
                intent1.putExtra("position",position);
                intent1.putExtra("mission",mission);
                intent1.putExtra("reward",reward);
                intent1.putExtra("way",way);

                startActivity(intent1);

                //todo mission객체로 만들어 CreateMission에 넘긴다.
            }
        });

    }
}
