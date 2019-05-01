/*****************임의 수정 금지*******************
 *      하단 바 애니매이션 고정 파일
 *      참고: https://dev-imaec.tistory.com/12
 *
 *
 */


package com.impact.tripble;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

class FixBottomIcon {
    @SuppressLint("RestictedApi")
    static void disableShiftMode(BottomNavigationView view){
        BottomNavigationView barView = (BottomNavigationView) view.getChildAt(0);
        try{
            Field shiftingMode = barView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(barView, false);
            shiftingMode.setAccessible(false);

            for(int i=0; i<barView.getChildCount(); i++){
                BottomNavigationItemView item = (BottomNavigationItemView) barView.getChildAt(i);

                item.setShiftingMode(false);

                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}
