package com.impact.tripble;


import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class offline extends AppCompatActivity {

    ImageView[] b = new ImageView[11];
    TextView[] text = new TextView[4];
    Button clear;
    TextView error;

    Animation animation;
    Vibe vibe;

    String key = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_mission);
        b[0] = (ImageView)findViewById(R.id.bt_0);
        b[1] = (ImageView)findViewById(R.id.bt_1);
        b[2] = (ImageView)findViewById(R.id.bt_2);
        b[3] = (ImageView)findViewById(R.id.bt_3);
        b[4] = (ImageView)findViewById(R.id.bt_4);
        b[5] = (ImageView)findViewById(R.id.bt_5);
        b[6] = (ImageView)findViewById(R.id.bt_6);
        b[7] = (ImageView)findViewById(R.id.bt_7);
        b[8] = (ImageView)findViewById(R.id.bt_8);
        b[9] = (ImageView)findViewById(R.id.bt_9);
        b[10] = (ImageView)findViewById(R.id.bt_del);

        text[0] = (TextView)findViewById(R.id.t1);
        text[1] = (TextView)findViewById(R.id.t2);
        text[2] = (TextView)findViewById(R.id.t3);
        text[3] = (TextView)findViewById(R.id.t4);
        clear = (Button)findViewById(R.id.clearButton);
        error = (TextView)findViewById(R.id.error);

        animation = AnimationUtils.loadAnimation(this,R.anim.blink);
        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibe = new Vibe(vibrator);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isClear", true);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        for(int i =0; i<11; i++){
            b[i].setOnClickListener(new findIndexOnClickListener(i) {
                @Override
                public void onClick(View v) {
                    error.setVisibility(View.INVISIBLE);
                    if(i !=10) {
                        key += i;
                        setText();
                    }
                    else if(key.length() != 0  && i == 10) {
                        key = key.substring(0,key.length()-1);
                        delText();
                    }
                    if(key.length()==4){
                        if(key.equals("1234")){
                            clear.setVisibility(View.VISIBLE);
                            clear.setClickable(true);
                            vibe.successVibe();
                        }else{
                            error.setVisibility(View.VISIBLE);
                            error.startAnimation(animation);
                            key = "";
                            for(int i=0; i<4; i++){
                                text[i].setText("");
                            }
                            vibe.failVibe();
                        }
                    }else if(key.length()>4){
                        key = key.substring(0,key.length()-1);
                    }

                }
            });
        }
    }

    public void setText(){
        for(int i= 0; i<4; i++){
            if(text[i].getText().equals("")){
                text[i].setText("*");
                break;
            }
        }
    }

    public void delText(){

        for(int i=3; i>=0; i--){
            if(text[i].getText().equals("*")){
                text[i].setText("");
                break;
            }
        }
    }
}
