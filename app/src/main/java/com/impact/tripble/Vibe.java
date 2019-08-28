package com.impact.tripble;

import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;

public class Vibe extends AppCompatActivity {
    Vibrator vibrator;

    Vibe(Vibrator vibrator){
        this.vibrator = vibrator;
    }

    public void successVibe(){
        vibrator.vibrate(200);
    }

    public void failVibe(){
        long[] pattern = {0,150,100,150};
        vibrator.vibrate(pattern,-1);
    }
}
