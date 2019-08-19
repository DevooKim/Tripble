package com.impact.tripble;

import java.util.ArrayList;
import java.util.List;

public class Host {

    String title;
    String tel;
    List<Theme> mTheme;

    public Host(){
    }

    public Host(String title, String tel){
        this.title = title;
        this.tel = tel;
        this.mTheme = new ArrayList<Theme>();
    }

    private void setTheme(Theme theme){
        mTheme.add(theme);
    }

}
