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

            }
        });


    }


}
