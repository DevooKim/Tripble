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
        setClick();
    }

    private void setButton(){

        //gl = (GridLayout)findViewById(R.id.table);
        gl = new GridLayout(this);
        list1 = new ArrayList();
        list2 = new ArrayList();

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

               int path = getResources().getIdentifier("bt_y"+i+"x"+j,"drawable", getPackageName());
               list2.add(path);
               list1.add(btn);

            }
        }
    }

        public void setClick(){
        for(i=0; i<24; i++){
            bt_tmp = (Button) list1.get(i);
            bt_tmp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id_y = (Integer) list2.get(i);
                    //draw = getResources().getDrawable(id_y);
                    int id = getResources().getIdentifier("b"+ i,"layout",getPackageName());
                    bt_check = (Button)findViewById(id);

                    if(id_y != id && count < 5){
                        //String key = (String) v.getTag();   //흰배경의 아이콘
                        bt_tmp.setBackgroundResource(id_y);
                        count++;
                    } else if(id_y == id && count >0){
                        bt_tmp.setBackgroundResource(id);
                    }

                    Drawable d1 = bt_tmp.getBackground();
                    Drawable d2 = getResources().getDrawable((Integer)list2.get(i));

                    if(d1.equals(d2)){
                        //bt_tmp.setBackgroundResource(((Integer)list2.get(i)));
                        bt_tmp.setBackgroundResource(R.drawable.bt_1x1);
                    }else if(!(d1.equals(d2))){
                        bt_tmp.setBackgroundResource(R.drawable.bt_1x1);

                    }

                }
            });
        }
    }

}


