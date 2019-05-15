package com.impact.tripble;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class select_HostUser extends AppCompatActivity {

    ImageButton bt_boy, bt_girl;
    Drawable boy, girl, tmp_boy, tmp_girl;
    Button bt_next;
    EditText et_nickname;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_host_user);

        setImageButton();

    }

    /*성별입력*/
    private void setImageButton(){

        bt_boy = (ImageButton)findViewById(R.id.bt_boy);
        bt_girl = (ImageButton)findViewById(R.id.bt_girl);
        boy = getResources().getDrawable(R.drawable.bt_boy);
        girl = getResources().getDrawable(R.drawable.bt_girl);

        bt_boy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                tmp_boy = bt_boy.getBackground();
//                tmp_girl = bt_girl.getBackground();
//
//                if(tmp_boy.equals(boy)) {
//                    bt_boy.setBackgroundResource(R.drawable.bt_boy_2);
//                }else if(!(tmp_girl.equals(girl))){
//                    bt_boy.setBackgroundResource(R.drawable.bt_boy_2);
//                    bt_girl.setBackgroundResource(R.drawable.bt_girl);
//                }

                Intent intent = new Intent(select_HostUser.this, setHost.class);
                startActivity(intent);
            }
        });

        bt_girl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tmp_boy = bt_boy.getBackground();
                tmp_girl = bt_girl.getBackground();

                if(tmp_girl.equals(girl)) {
                    bt_girl.setBackgroundResource(R.drawable.bt_girl_2);
                }else if(!(tmp_boy.equals(boy))){
                    bt_girl.setBackgroundResource(R.drawable.bt_girl_2);
                    bt_boy.setBackgroundResource(R.drawable.bt_boy);
                }
            }
        });
    }

    /*next버튼*/
    private void setBt_next(){


    }
}
