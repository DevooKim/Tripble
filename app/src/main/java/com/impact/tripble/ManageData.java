package com.impact.tripble;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ManageData extends AppCompatActivity {

    Gson gson;

    protected void onSaveHost(String host, String position, String call){

        Host mHost = new Host(host, position, call);

        gson = new GsonBuilder().create();
        String strHost = gson.toJson(mHost, Host.class);

        SharedPreferences sp = getSharedPreferences(host,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("host",strHost);
        editor.commit();

    }

    protected Host onLoadHost(String hostName) {
        SharedPreferences sp = getSharedPreferences(hostName, MODE_PRIVATE);
        String strHost = sp.getString("host", "");

        gson = new GsonBuilder().create();
        Host loadHost = gson.fromJson(strHost, Host.class);

        return loadHost;
        //todo 매개변수로 호스트이름을 받아 저장된 값을 리턴한다.
        //todo 이름이 없을경우 조건 추가.
    }
}
