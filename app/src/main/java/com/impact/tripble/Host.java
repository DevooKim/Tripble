//package com.impact.tripble;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Host {
//
//    String host;
//    String tel;
//    List<Theme> mTheme;
//
//    public Host(){
//    }
//
//    public Host(String title, String tel){
//        this.title = title;
//        this.tel = tel;
//        this.mTheme = new ArrayList<Theme>();
//    }
//
//    private void setTheme(Theme theme){
//        mTheme.add(theme);
//    }
//
//}

package com.impact.tripble;

import java.util.ArrayList;
import java.util.List;
public class Host {

    String title;
    String reward;
    String position;

    public Host(String title, String reward, String position){
            this.title = title;
            this.reward = reward;
            this.position = position;
    }
}

