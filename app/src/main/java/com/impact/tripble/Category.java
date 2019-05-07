package com.impact.tripble;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

public class Category extends AppCompatActivity {
    GridLayout gl;
    Button btn, bt_tmp, bt_check;
    ArrayList list1, list2;
    String name;
    Drawable draw;


    int tmp=0, count=0, i;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        setButton();
    }

    private void setButton(){


        //gl = (GridLayout)findViewById(R.id.table);
        gl = new GridLayout(this);

        for(int i=1; i<6; i++){
            for(int j=1; j<6; j++){
                tmp++;
                btn = new Button(this);

                //xml에 구현해 놓은 View와 매칭//
                name = "b"+tmp;
                int nameId = getResources().getIdentifier(name,"id",getPackageName());
                btn = findViewById(nameId);

                //각 View에 이미지 넣기//
                String str = "b"+tmp;
                btn.setTag(str);
                int resId = getResources().getIdentifier("bt_"+i+"x"+j,"drawable",getPackageName());
                btn.setBackgroundResource(resId);

               ViewGroup gl = (ViewGroup)btn.getParent();
               gl.removeView(btn);
               gl.addView(btn);

            }

        }

    }

}


