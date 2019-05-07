package com.impact.tripble;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = (Button)findViewById(R.id.bt1);

        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Make_id.class);
                startActivity(intent);
            }
        });


        //bottomNavigator();
    }

    protected void bottomNavigator(){

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigationBar);

        FixBottomIcon.disableShiftMode(bottomNavigationView);
    }
}