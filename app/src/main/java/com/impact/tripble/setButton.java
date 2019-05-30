package com.impact.tripble;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class setButton implements View.OnClickListener{

    private Activity mActivity;
    Intent intent;

    public setButton(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.bt3:
                intent = new Intent(mActivity, QRcode.class);
                mActivity.startActivity(intent);
                break;

            case R.id.bt4:
                intent = new Intent(mActivity, GEOMain.class);
                mActivity.startActivity(intent);
                break;

            case R.id.bt5:
                intent = new Intent(mActivity, NFC.class);
                mActivity.startActivity(intent);
//                intent = new Intent(mActivity, bluetooth.class);
//                intent.putExtra("init","init");
                mActivity.startActivity(intent);
                break;

        }
    }
}
