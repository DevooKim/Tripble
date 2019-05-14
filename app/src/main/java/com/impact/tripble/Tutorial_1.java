package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Tutorial_1 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_1);

        Button bt = (Button)findViewById(R.id.bt1);

        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Tutorial_1.this, Tutorial_2.class);
                startActivity(intent);
            }
        });
    }

}