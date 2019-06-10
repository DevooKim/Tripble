package com.impact.tripble;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class offline extends AppCompatActivity {

    private final String passward = "1234";
    private final int REQUEST_MISSION = 400;
    EditText p1, p2, p3, p4;
    Button clearButton;
    TextView state;

    String check="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_mission);

        p1 = (EditText)findViewById(R.id.p1);
        p2 = (EditText)findViewById(R.id.p2);
        p3 = (EditText)findViewById(R.id.p3);
        p4 = (EditText)findViewById(R.id.p4);

        //p1.setFocusable(true);
        p1.setNextFocusDownId(R.id.p2);
       // p2.setFocusable(false);
        p2.setNextFocusDownId(R.id.p3);
       // p3.setFocusable(false);
        p3.setNextFocusDownId(R.id.p4);
       // p4.setFocusable(false);
        //p4.setNextFocusDownId(R.id.p1);

        clearButton = (Button) findViewById(R.id.clearButton);
        state = (TextView)findViewById(R.id.state);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(offline.this, Popup.class);
                startActivityForResult(intent,REQUEST_MISSION);
            }
        });

        p1.addTextChangedListener(etTextWatcher1);
        p2.addTextChangedListener(etTextWatcher2);
        p3.addTextChangedListener(etTextWatcher3);
        p4.addTextChangedListener(etTextWatcher4);

    }

    TextWatcher etTextWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            if(s.length() == 1){
//                p1.setFocusable(false);
//            }
//            if(s.length() == 0){
//                p1.setFocusable(true);
//            }
        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            if(s.length() == 1){
//                p1.setFocusable(false);
//                p2.setFocusable(true);
//            }
//            if(s.length() == 0 ){
//                p1.requestFocus();
//            }
        }
    };

    TextWatcher etTextWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            if(s.length() == 0){
//                p2.setFocusable(true);
//            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            if(s.length() == 1){
//                p2.setFocusable(false);
//            }
//            if(s.length() == 0 ){
//                p2.requestFocus();
//            }
        }
    };

    TextWatcher etTextWatcher3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            if(s.length() == 1){
//                p3.setFocusable(false);
//                //p4.setFocusable(true);
//            }
//            if(s.length() == 0){
//                p3.requestFocus();
//            }
        }
    };

    TextWatcher etTextWatcher4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() == 1){
//                p4.setFocusable(false);

                check+=p1.getText().toString();
                check+=p2.getText().toString();
                check+=p3.getText().toString();
                check+=p4.getText().toString();

                checkPW();
            }
//            if(s.length() == 0) {
//                p4.requestFocus();
//            }
        }
    };

    public void checkPW(){
        if(check.equals(passward)){
            clearButton.setVisibility(View.VISIBLE);
            clearButton.setClickable(true);
        }else{
            //p4.setFocusable(false);
            state.setText("잘못된 비밀번호");
            check="";
            p1.setText(null);
            p2.setText(null);
            p3.setText(null);
            p4.setText(null);
//            p1.setFocusable(true);
//            p2.setFocusable(true);
//            p3.setFocusable(true);
//            p4.setFocusable(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        boolean temp;
        if (resultCode == RESULT_OK) {
            temp = data.getBooleanExtra("isClear", false);
            intent = new Intent(offline.this, Mission_list.class);
            intent.putExtra("isClear", temp);
            intent.putExtra("key", 4);
            startActivityForResult(intent, REQUEST_MISSION);
        }
    }
}
