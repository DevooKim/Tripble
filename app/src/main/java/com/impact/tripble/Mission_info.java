package com.impact.tripble;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Mission_info extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_info);

        final Intent intent = getIntent();

        String markerid = intent.getExtras().getString("markerId");

        TextView textView_1 = (TextView)findViewById(R.id.mission_title);
        final ImageView imageView_1 = (ImageView)findViewById(R.id.mission_img);
        TextView textView_2 = (TextView)findViewById(R.id.mission_story);
        TextView textView_3 = (TextView)findViewById(R.id.gift_name);
        final ImageView imageView_2 = (ImageView)findViewById(R.id.gift_img);
        Button button = (Button)findViewById(R.id.mission_start);



        switch (markerid)
        {

            case "m0":

                textView_1.setText("히스토리 카페들을 방문하자");
                imageView_1.setImageResource(R.drawable.history);
                textView_2.setText("한남대의 히스토리 카페를 \n차례로 방문해 주세요!");
                textView_3.setText("모든 미션 클리어 시\nHistory 아메리카노\n1회 교환권");
                imageView_2.setImageResource(R.drawable.coffee);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Mission_info.this, GEOMain.class);
                        startActivity(intent1);
                    }
                });
                break;

            case "m1":
                textView_1.setText("숨겨진 코드를 해석해라");
                imageView_1.setImageResource(R.drawable.changup);
                textView_2.setText("한남대 창업 마켓 곳곳에 \nQR코드들이 숨겨져있습니다.\n 찾아서 미션을 완수하세요!");
                textView_3.setText("모든 미션 클리어 시\nHistory 아메리카노\n1회 교환권");
                imageView_2.setImageResource(R.drawable.coffee);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Mission_info.this, QRcode.class);
                        startActivity(intent1);
                    }
                });
                break;

            case "m2":
                textView_1.setText("흩어진 카드의 순서를 맞추기");
                imageView_1.setImageResource(R.drawable.hdf);
                textView_2.setText("흩어진 카드들을 찾아 \n휴대폰으로 순서에 맞게 조합해주세요!");
                textView_3.setText("모든 미션 클리어 시\nHistory 아메리카노\n1회 교환권");
                imageView_2.setImageResource(R.drawable.coffee);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Mission_info.this, NFC.class);
                        startActivity(intent1);
                    }
                });
                break;

            case "m3":
                textView_1.setText("독수리 탈을 찾아라");
                imageView_1.setImageResource(R.drawable.memo);
                textView_2.setText("56주년 기념관 안에는\n독수리의 탈이 숨겨져 있습니다.\n찾아서 사진을 찍어 스테프에게 증명하세요!");
                textView_3.setText("모든 미션 클리어 시\nHistory 아메리카노\n1회 교환권");
                imageView_2.setImageResource(R.drawable.coffee);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Mission_info.this, offline.class);
                        startActivity(intent1);
                    }
                });
                break;
        }
    }

}
