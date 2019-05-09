package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateMission extends AppCompatActivity {

    private String name;
    private String call;
    private int tag;

    private Button button;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mission);

        Intent intent = getIntent();

        name = intent.getExtras().getString("name");
        call = intent.getExtras().getString("call");
        tag= intent.getExtras().getInt("tag");

        clickExtraButton();

    }

    public void clickExtraButton(){
        button = (Button)findViewById(R.id.extraMission);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateMission.this, ExtraMission.class);
                intent.putExtra("tag",tag);

                //todo tag값 넘길 필요 없음.
                //todo Json (Host, mission) 구현.
                //todo json이 담긴 배열?
                //todo 완료버튼 누를 시 mission데이터 저장. + host데이터도 같이 저장

            }
        });


    }


}
