package com.impact.tripble;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class NFC extends AppCompatActivity {

    TextView readResult;
    TextView clear;
    TextView state;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechList;
    private Tag tag;
    private IsoDep tagcomm;
    private static String tagNum = null;

    Intent wifi_intent;

    private final String KEY_A = "E90ACEDE";  //신한
    private final String KEY_B = "BD2A09DB";  //카카오
    private final String KEY_C = "C200D5E7";  //기숙사
    private boolean TAG_A = false;
    private boolean TAG_B = false;
    private boolean TAG_C = false;
    //protected boolean success = false;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc);
        readResult = (TextView)findViewById(R.id.tagDesc);
        clear = (TextView)findViewById(R.id.clear);
        state = (TextView)findViewById(R.id.state);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent targetIntent = new Intent(this, NFC.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mPendingIntent = PendingIntent.getActivity(this,0,targetIntent,0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try{
            ndef.addDataType("*/*");
        }catch (Exception e){
            throw new RuntimeException("fail",e);
        }
    }

    @Override
    protected  void onPause(){
        if(mAdapter != null){
            mAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    @Override
    protected  void onResume(){
        super.onResume();
        if(mAdapter != null){
            mAdapter.enableForegroundDispatch(this,mPendingIntent, null, null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        tag = intent.getParcelableExtra(mAdapter.EXTRA_TAG);
        if(tag != null){
            byte[] tagId = tag.getId();
            //readResult.setText("TagID: " + toHexString(tagId));
            tagNum = toHexString(tagId);
        }

        isRightTag(tagNum);

        if(TAG_A == true && TAG_B == true && TAG_C == true){
            clear.setText("성공");
        }

    }

    public static final String CHARS = "0123456789ABCDEF";
    public static String toHexString(byte[] data){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< data.length; i++) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(
                    CHARS.charAt(data[i] & 0x0F));
        }
            return sb.toString();
    }

    private void isRightTag(String Key) {

        switch (Key) {
            case KEY_A:
                if (TAG_A == false && TAG_B == false && TAG_C == false) {
                    TAG_A = true;
                    state.setText("A성공");
                    //todo wifi추가
                    wifi_intent.putExtra("input", TAG_A);

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                }
                break;

            case KEY_B:
                if (TAG_A == true && TAG_B == false && TAG_C == false) {
                    TAG_B = true;
                    state.setText("A,B성공");
                    //todo wifi추가
                    wifi_intent.putExtra("input", TAG_B);

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                }
                break;

            case KEY_C:
                if (TAG_A == true && TAG_B == true && TAG_C == false) {
                    TAG_C = true;
                    state.setText("A,B,C성공");
                    //todo wifi추가
                    wifi_intent.putExtra("input", TAG_C);

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                }
                break;

            default:
                Toast.makeText(this,"등록되지 않은 카드", Toast.LENGTH_LONG).show();
                break;
        }
    }

//    public void sendIntent(){
//        wifi_intent = new Intent(this, WIFI.class);
//
//        startActivityForResult(wifi_intent,100);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 100:
                    break;
            }
        }
    }
}
