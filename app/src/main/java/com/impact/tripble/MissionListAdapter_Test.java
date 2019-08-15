package com.impact.tripble;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MissionListAdapter_Test extends BaseAdapter {

    private List mission;
    private Context context;

    public MissionListAdapter_Test(List mission, Context context){
        this.mission = mission;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.mission.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mission.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;

        if (convertView == null){
            convertView = new LinearLayout(context);
            TextView tv_id = new TextView(context);
            TextView tv_name = new TextView(context);
            TextView tv_latitude= new TextView(context);
            TextView tv_longitude= new TextView(context);
            TextView tv_tel= new TextView(context);
            TextView tv_host= new TextView(context);
            ((LinearLayout) convertView).addView(tv_name);
            ((LinearLayout) convertView).addView(tv_latitude);
            ((LinearLayout) convertView).addView(tv_longitude);
            ((LinearLayout) convertView).addView(tv_tel);
            ((LinearLayout) convertView).addView(tv_host);

            holder = new Holder();
            holder.tv_id = tv_id;
            holder.tv_Name = tv_name;
            holder.tv_Latitude = tv_latitude;
            holder.tv_Longitude = tv_longitude;
            holder.tv_Tel = tv_tel;
            holder.tv_Host = tv_host;

            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        Mission_Test mission_test = (Mission_Test) getItem(position);
        holder.tv_id.setText(mission_test.get_id()+"");
        holder.tv_Name.setText(mission_test.getName());
        holder.tv_Latitude.setText(mission_test.getLatitude()+"");
        holder.tv_Longitude.setText(mission_test.getLongitude());
        holder.tv_Tel.setText(mission_test.getTel()+"");
        holder.tv_Host.setText(mission_test.getHost());

        return convertView;
    }

}
