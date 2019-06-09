package com.impact.tripble;

import android.view.View;

public abstract class findIndexOnClickListener implements View.OnClickListener{

    protected int i;
    public findIndexOnClickListener(int i){
        this.i = i;
    }
}
