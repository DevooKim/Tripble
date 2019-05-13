package com.impact.tripble;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class setButton implements View.OnClickListener{

    private Activity mActivity;
    Intent intent;

    public setButton(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.bt1:
                intent = new Intent(mActivity, Make_id.class);
                mActivity.startActivity(intent);
                break;

            case R.id.bt2:
                intent = new Intent(mActivity, Tutorial_1.class);
                mActivity.startActivity(intent);
                break;

            case R.id.bt3:
                intent = new Intent(mActivity, setHost.class);
                mActivity.startActivity(intent);


        }
    }
}
