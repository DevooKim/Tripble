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

        clearButton = (Button) findViewById(R.id.clearButton);
        state = (TextView)findViewById(R.id.state);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("isClear", true);
                setResult(RESULT_OK,intent);
                finish();
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

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() == 1){
                p1.setFocusable(false);
                //p2.setFocusable(true);
            }
        }
    };

    TextWatcher etTextWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length() == 1){
                p2.setFocusable(false);
                //p3.setFocusable(true);
            }
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
            if(s.length() == 1){
                p3.setFocusable(false);
                //p4.setFocusable(true);
            }
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
                p4.setFocusable(false);

                check+=p1.getText().toString();
                check+=p2.getText().toString();
                check+=p3.getText().toString();
                check+=p4.getText().toString();

                checkPW();
            }
        }
    };

    public void checkPW(){
        if(check.equals(passward)){
            clearButton.setVisibility(View.VISIBLE);
            clearButton.setClickable(true);
        }else{
            state.setText("잘못된 비밀번호");
        }
    }

}
