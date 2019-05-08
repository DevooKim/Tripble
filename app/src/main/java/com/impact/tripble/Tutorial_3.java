package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Tutorial_3 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_1);
    }

    public void Start(View view){
        Intent intent = new Intent(Tutorial_3.this, MainActivity.class);
        startActivity(intent);
    }
}
