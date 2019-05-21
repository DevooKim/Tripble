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

public class NFC extends AppCompatActivity {

    TextView readResult;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechList;
    private Tag tag;
    private IsoDep tagcomm;
    private static String tagNum = null;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc);
        readResult = (TextView)findViewById(R.id.tagDesc);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent targetIntent = new Intent(this, NFC.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mPendingIntent = PendingIntent.getActivity(this,0,targetIntent,0);

        //IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
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
            readResult.setText("TagID: " + toHexString(tagId));
            tagNum = toHexString(tagId);
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
}
