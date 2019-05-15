package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setHost extends AppCompatActivity {

    EditText et_host, et_position, et_call;
    Button bt_next;

    String host, position, call;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_host);

        setHost();

    }

    public void setHost(){

        et_host = (EditText)findViewById(R.id.host);
        et_position = (EditText)findViewById(R.id.position);
        et_call = (EditText)findViewById(R.id.call);

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(setHost.this, setGroup.class);

                host = et_host.getText().toString();
                position = et_position.getText().toString();
                call = et_call.getText().toString();

                ManageData MD = new ManageData();
                MD.onSaveHost(host, position, call);

                intent.putExtra("host", host);

                startActivity(intent);
                finish();
            }
        });
    }

}
