package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class setHost extends AppCompatActivity {

    Button button;
    EditText et_name;
    EditText et_call;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_host);

        setHost();

    }

    public void setHost(){
        button= (Button) findViewById(R.id.host_next);
        et_name= (EditText)findViewById(R.id.host_name);
        et_call= (EditText)findViewById(R.id.host_call);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String call = et_call.getText().toString();

                Random random = new Random();
                int tag = random.nextInt(100);

                Intent intent = new Intent(setHost.this, CreateMission.class);

                intent.putExtra("name", name);
                intent.putExtra("call", call);
                intent.putExtra("tag",tag);

                startActivity(intent);


            }
        });


    }









}
