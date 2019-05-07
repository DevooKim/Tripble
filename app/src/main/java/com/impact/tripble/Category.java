package com.impact.tripble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridLayout;

public class Category extends AppCompatActivity {
    GridLayout gl;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
    }

    private void setButton(){
        b1=(Button)findViewById(R.id.oneX1);
        b2=(Button)findViewById(R.id.oneX2);
        b3=(Button)findViewById(R.id.oneX3);
        b4=(Button)findViewById(R.id.oneX4);
        b5=(Button)findViewById(R.id.oneX5);
        b6=(Button)findViewById(R.id.twoX1);
        b7=(Button)findViewById(R.id.twoX2);
        b8=(Button)findViewById(R.id.twoX3);
        b9=(Button)findViewById(R.id.twoX4);
        b10=(Button)findViewById(R.id.twoX5);
        b11=(Button)findViewById(R.id.threeX1);
        b12=(Button)findViewById(R.id.threeX2);
        b13=(Button)findViewById(R.id.threeX3);
        b14=(Button)findViewById(R.id.threeX4);
        b15=(Button)findViewById(R.id.threeX5);
        b16=(Button)findViewById(R.id.fourX1);
        b17=(Button)findViewById(R.id.fourX2);
        b18=(Button)findViewById(R.id.fourX3);
        b19=(Button)findViewById(R.id.fourX4);
        b20=(Button)findViewById(R.id.fourX5);
        b21=(Button)findViewById(R.id.fiveX1);
        b22=(Button)findViewById(R.id.fiveX2);
        b23=(Button)findViewById(R.id.fiveX3);
        b24=(Button)findViewById(R.id.fiveX4);
        b25=(Button)findViewById(R.id.fiveX5);

        for(int i=1; i<26; i++){
            String str = "b" + i;

        }

    }

}


