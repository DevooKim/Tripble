package com.impact.tripble;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;

public class Startscreen extends AppCompatActivity {

    private final int REQUEST_WIDTH = 512;
    private final int REQUEST_HEIGHT = 512;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        //이미지 크기가 너무 커서 크기 조절 코드
        ImageView imageView = (ImageView) findViewById(R.id.backim);

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.start_im, options);

        imageView.setImageBitmap(bitmap);

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(Startscreen.this, ViewFlipperMain.class));
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){

        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        int size = 1;

        while(requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
    }
}

