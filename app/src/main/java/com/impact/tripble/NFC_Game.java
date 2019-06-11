package com.impact.tripble;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class NFC_Game extends AppCompatActivity {

    TextView state, timer;
    TextView state1,state2,state3,state4,state5,state6;

    Button btn;
    private int initTime = 60;
    TimerTask tt;
    Handler handler;

    Vibe vibe;
    Animation animation;

    /*NFC*/
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechList;
    private Tag tag;
    private IsoDep tagcomm;
    private static String tagNum = null;

    //Group A: s6 keyCode: 2 6 3 1 5 4
    private final String KEY_1 = "04537A021B5C81";
    private final String KEY_2 = "04FA5B021B5C80";
    private final String KEY_3 = "04803C021B5C80";
    private final String KEY_4 = "045170021B5C81";
    private final String KEY_5 = "04287A021B5C81";
    private final String KEY_6 = "044D9A021B5C81";

    //Group B: s8 keyCode 8 9 7 11 12 10
    private final String KEY_7 = "04EE52021B5C80";
    private final String KEY_8 = "04B53C021B5C80";
    private final String KEY_9 = "047679021B5C81";
    private final String KEY_10 = "04F73E021B5C80";
    private final String KEY_11 = "04A991021B5C80";
    private final String KEY_12 = "041C70021B5C80";

    private boolean s1 = false;
    private boolean s2 = false;
    private boolean s3 = false;
    private boolean s4 = false;
    private boolean s5 = false;
    private boolean s6 = false;
    private boolean s7 = false;
    private boolean s8 = false;
    private boolean s9 = false;
    private boolean s10 = false;
    private boolean s11 = false;
    private boolean s12 = false;

    boolean isClear = false;
    //protected boolean success = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_game);

        state1 = (TextView)findViewById(R.id.state1);
        state2 = (TextView)findViewById(R.id.state2);
        state3 = (TextView)findViewById(R.id.state3);
        state4 = (TextView)findViewById(R.id.state4);
        state5 = (TextView)findViewById(R.id.state5);
        state6 = (TextView)findViewById(R.id.state6);

        //NFC//
        state = (TextView) findViewById(R.id.state);
        timer = (TextView) findViewById(R.id.timer);
        btn = (Button) findViewById(R.id.btn);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibe = new Vibe(vibrator);
        animation = AnimationUtils.loadAnimation(this, R.anim.count);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!isClear) {
                    initTime = 90;
                    handler = new Handler() {
                        public void handleMessage(Message msg) {

                            btn.setVisibility(View.INVISIBLE);
                            btn.setClickable(false);
                            btn.setText("종료!");
                            initTime--;
                            handler.sendEmptyMessageDelayed(0, 1000);
                            timer.setText(Integer.toString(initTime));
                            if (initTime < 11) {
                                vibe.failVibe();
                                timer.setTextColor(Color.parseColor("#ff0000"));
                                timer.startAnimation(animation);
                            }
                            if (initTime == 0) {
                                btn.setText("다시하기!");
                                btn.setVisibility(View.VISIBLE);
                                btn.setClickable(true);
                                handler.removeMessages(0);
                            }
                            if (isClear) {
                                btn.setVisibility(View.VISIBLE);
                                btn.setClickable(true);
                                handler.removeMessages(0);
                            }
                        }
                    };

                    boolean send = handler.sendEmptyMessage(0);
                } else {
                    Intent intent = new Intent(NFC_Game.this, Mission_list.class);
                    startActivity(intent);

                    finish();
                }
            }
        });

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent targetIntent = new Intent(this, NFC_Game.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try {
            ndef.addDataType("*/*");
        } catch (Exception e) {
            throw new RuntimeException("fail", e);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED) {
            //showQuitDialog( "You need to enable bluetooth");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    //NFC//
    @Override
    protected void onPause() {
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    //NFC//
    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    //NFC//
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        tag = intent.getParcelableExtra(mAdapter.EXTRA_TAG);
        if (tag != null) {
            byte[] tagId = tag.getId();
            //readResult.setText("TagID: " + toHexString(tagId));
            tagNum = toHexString(tagId);
        }

        isRightTag(tagNum);

        if (isClear) {
            btn.setVisibility(View.VISIBLE);
            btn.setClickable(true);
        }

    }

    //NFC//
    public static final String CHARS = "0123456789ABCDEF";

    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(
                    CHARS.charAt(data[i] & 0x0F));
        }
        return sb.toString();
    }

    //NFC//
    private void isRightTag(String Key) {
        switch (Key) {
            case KEY_1:
                if (s1!=true && s2!=true && s3!=true && s4!=true && s5!=true && s6!=true) {
                    s1 = true;
                    state.setText("첫번째 카드 입니다.");
                    vibe.successVibe();
                    state1.setVisibility(View.VISIBLE);

                } else {
                    s1 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                }
                break;

            case KEY_2:
                if (s1==true && s2!=true && s3!=true && s4!=true && s5!=true && s6!=true) {
                    s2 = true;
                    state.setText("두번째 카드 입니다.");
                    vibe.successVibe();
                    state2.setVisibility(View.VISIBLE);


                } else {
                    s1 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_3:
                if (s1==true && s2==true && s3!=true && s4!=true && s5!=true && s6!=true) {
                    s3 = true;
                    state.setText("세번째 카드 입니다.");
                    vibe.successVibe();
                    state3.setVisibility(View.VISIBLE);

                } else {
                    s1 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;

                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                }
                break;

            case KEY_4:
                if (s1==true && s2==true && s3==true && s4!=true && s5!=true && s6!=true) {
                    s4 = true;
                    state.setText("네번째 카드 입니다.");
                    vibe.successVibe();
                    state4.setVisibility(View.VISIBLE);

                } else {
                    s1 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_5:
                if (s1==true && s2==true && s3==true && s4==true && s5!=true && s6!=true) {
                    s5 = true;
                    state.setText("다섯번째 카드 입니다.");
                    vibe.successVibe();
                    state5.setVisibility(View.VISIBLE);

                } else {
                    s1 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_6:
                if (s6==true && s2==true && s3==true && s4!=true && s5==true && s6==true) {
                    s6 = true;
                    state.setText("여섯번째 카드 입니다.");
                    vibe.successVibe();
                    state6.setVisibility(View.VISIBLE);


                    isClear = true;
                    if (isClear) {
                        btn.setVisibility(View.VISIBLE);
                        btn.setClickable(true);
                    }

                } else {
                    s6 =false;
                    s2 =false;
                    s3 =false;
                    s4 =false;
                    s5 =false;
                    s6 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                    isClear = false;
                    if (!isClear) {
                        btn.setVisibility(View.INVISIBLE);
                        btn.setClickable(false);
                    }
                }
                break;

            case KEY_7:
                if (s7!=true && s8!=true && s9!=true && s10!=true && s11!=true && s12!=true) {
                    s7 = true;
                    state.setText("첫번째 카드 입니다.");
                    vibe.successVibe();
                    state1.setVisibility(View.VISIBLE);

                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                }
                break;

            case KEY_8:
                if (s7==true && s8!=true && s9!=true && s10!=true && s11!=true && s12!=true) {
                    s8 = true;
                    state.setText("두번째 카드 입니다.");
                    vibe.successVibe();
                    state2.setVisibility(View.VISIBLE);


                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_9:
                if (s7==true && s8==true && s9!=true && s10!=true && s11!=true && s12!=true) {
                    s9 = true;
                    state.setText("세번째 카드 입니다.");
                    vibe.successVibe();
                    state3.setVisibility(View.VISIBLE);

                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;

                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                }
                break;

            case KEY_10:
                if (s7==true && s8==true && s9==true && s10!=true && s11!=true && s12!=true) {
                    s10 = true;
                    state.setText("네번째 카드 입니다.");
                    vibe.successVibe();
                    state4.setVisibility(View.VISIBLE);

                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_11:
                if (s7==true && s8==true && s9==true && s10==true && s11!=true && s12!=true) {
                    s11 = true;
                    state.setText("다섯번째 카드 입니다.");
                    vibe.successVibe();
                    state5.setVisibility(View.VISIBLE);

                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);
                }
                break;

            case KEY_12:
                if (s7==true && s8==true && s9==true && s10!=true && s11==true && s12==true) {
                    s12 = true;
                    state.setText("여섯번째 카드 입니다.");
                    vibe.successVibe();
                    state6.setVisibility(View.VISIBLE);


                    isClear = true;
                    if (isClear) {
                        btn.setVisibility(View.VISIBLE);
                        btn.setClickable(true);
                    }

                } else {
                    s7 =false;
                    s8 =false;
                    s9 =false;
                    s10 =false;
                    s11 =false;
                    s12 =false;
                    state.setText("순서가 잘못 되었습니다.");
                    vibe.failVibe();

                    state1.setVisibility(View.INVISIBLE);
                    state2.setVisibility(View.INVISIBLE);
                    state3.setVisibility(View.INVISIBLE);
                    state4.setVisibility(View.INVISIBLE);
                    state5.setVisibility(View.INVISIBLE);
                    state6.setVisibility(View.INVISIBLE);

                    isClear = false;
                    if (!isClear) {
                        btn.setVisibility(View.INVISIBLE);
                        btn.setClickable(false);
                    }
                }
                break;

            default:
                //todo 꽝인 경우도 세팅
                //todo 첫번째 성공인 경우 순서잘못 / 처음은 문제출제
                break;
        }
    }
}


