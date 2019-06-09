package com.impact.tripble;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class NFC extends AppCompatActivity {

    Button clearButton;
    boolean isClear;
    Intent recvIntent;

    /*NFC*/
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
    private final int REQUEST_MISSION = 100;

    private final String KEY_A = "04BD47021B5C80";
    private final String KEY_B = "044655021B5C80";
    private final String KEY_C = "04217F021B5C80";
    private final String KEY_SPARE = "04D451021B5C80";
    private boolean TAG_A = false;
    private boolean TAG_B = false;
    private boolean TAG_C = false;
    //protected boolean success = false;


    /*bluetooth*/
    private final int REQUEST_ENABLE_BT = 100;
    private static final String TAG = "BluetoothClient";
    ListView connectedState;
    private ArrayAdapter<String> mConversationArrayAdapter;
    String[] recvTest;

    ConnectedTask[] mConnectedTask = new ConnectedTask[5];
    static boolean isConnectionError = false;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mSocket0;
    private BluetoothSocket mSocket1;
    private BluetoothSocket mSocket2;
    private BluetoothDevice B0;
    private BluetoothDevice B1;
    private BluetoothDevice B2;
    private ConnectTask BC0;
    private ConnectTask BC1;
    private ConnectTask BC2;

    final String B0MA = "B0:FC:36:29:89:98";
    //final String B1MA = "7C:67:A2:43:3B:80";    //범준
    String B1MA = "0C:54:15:0A:EF:41";  //원규
    //String B2MA = "F8:63:3F:13:C1:0C";  //이슬
    String B2MA = "0C:96:E6:C9:99:7C";


    final int deviceCount = 2;
    final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc);

        recvIntent = new Intent();
        clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NFC.this, Popup.class);
                startActivityForResult(intent,REQUEST_MISSION);
            }
        });

        //NFC//
        readResult = (TextView) findViewById(R.id.tagDesc);
        clear = (TextView) findViewById(R.id.clear);
        state = (TextView) findViewById(R.id.state);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        Intent targetIntent = new Intent(this, NFC.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try {
            ndef.addDataType("*/*");
        } catch (Exception e) {
            throw new RuntimeException("fail", e);
        }

        //bluetooth//
        Log.d(TAG, "Initalizing Bluetooth adapter...");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        connectedState = (ListView) findViewById(R.id.bluetooth_state);
        mConversationArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        connectedState.setAdapter(mConversationArrayAdapter);


        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            Log.d(TAG, "Initialisation Successful");
            pairingDevice();
        }
    }

    public void popUp(){

        AlertDialog.Builder builder = new AlertDialog.Builder(NFC.this);
        LayoutInflater factory = LayoutInflater.from(NFC.this);
        View view = factory.inflate(R.layout.clear,null);

        builder.setTitle("미션 완료!!");
        builder.setView(view);
        builder.setNegativeButton("받기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NFC.this,"미션을 완수하셨습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //BlueTooth is now Enabled
                pairingDevice();
            }

        }
        if (resultCode == RESULT_CANCELED) {
            //showQuitDialog( "You need to enable bluetooth");
        }

        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        boolean temp;
        if (resultCode == RESULT_OK) {
            temp = data.getBooleanExtra("isClear", false);
            intent = new Intent(NFC.this, Mission_list.class);
            intent.putExtra("isClear", temp);
            intent.putExtra("key", 1);
            startActivityForResult(intent, REQUEST_MISSION);
        }
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

        if (TAG_A == true && TAG_B == true && TAG_C == true) {
            clear.setText("성공");
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
            case KEY_A:
                if (TAG_A == false && TAG_B == false && TAG_C == false) {
                    TAG_A = true;
                    state.setText("A성공");
                    //sendIntent(TAG_A);
                    sendMessage(TAG_A);

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                    sendMessage(TAG_A);
                }
                break;

            case KEY_B:
                if (TAG_A == true && TAG_B == false && TAG_C == false) {
                    TAG_B = true;
                    state.setText("A,B성공");
                    //sendIntent(TAG_B);
                    sendMessage(TAG_B);

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                    sendMessage(TAG_B);
                }
                break;

            case KEY_C:
                if (TAG_A == true && TAG_B == true && TAG_C == false) {
                    TAG_C = true;
                    state.setText("A,B,C성공");
                    //sendIntent(TAG_C);
                    sendMessage(TAG_C);
                    isClear = true;
                    if(isClear){
                        clearButton.setVisibility(View.VISIBLE);
                        clearButton.setClickable(true);
                    }

                } else {
                    TAG_A = false;
                    TAG_B = false;
                    TAG_C = false;
                    state.setText("순서가 틀렸습니다. 처음부터 시작하세요.");
                    sendMessage(TAG_C);

                    isClear = false;
                    if(!isClear){
                        clearButton.setVisibility(View.INVISIBLE);
                        clearButton.setClickable(false);
                    }
                }
                break;

            default:
                if(Key.equals(KEY_SPARE)){
                    if (TAG_A == false && TAG_B == false && TAG_C == false) {
                        TAG_A = true;
                        state.setText("A성공");
                        sendMessage(TAG_A);
                        break;

                    }

                    if (TAG_A == true && TAG_B == false && TAG_C == false) {
                        TAG_B = true;
                        state.setText("A,B성공");
                        sendMessage(TAG_B);

                        break;

                    }

                    if (TAG_A == true && TAG_B == true && TAG_C == false) {
                        TAG_C = true;
                        state.setText("A,B,C성공");
                        sendMessage(TAG_C);

                        isClear = true;
                        if(isClear){
                            clearButton.setVisibility(View.VISIBLE);
                            clearButton.setClickable(true);
                        }

                    }
                    break;
                }else{
                    Toast.makeText(this, "등록되지 않은 카드", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //bluetooth
//    protected class ConnectThread extends Thread{
//        BluetoothDevice BD;
//        BluetoothSocket BS;
//
//        int bt_index;
//
//        ConnectThread connectThread;
//
//        ConnectThread(BluetoothDevice BD, int bt_index){
//            this.BD = BD;
//            this.bt_index =bt_index;
//        }
//
//        @Override
//        public void run() {
//            try{
//                //sendValue("connecting..OK");
//
//                BS = BD.createInsecureRfcommSocketToServiceRecord(uuid);
//                BS.connect();
//
//                connectThread = new ConnectThread(BS, bt_index);
//                connectThread.start();
//            } catch (IOException e){
//                Log.d(TAG, "connecting error..");
//                try{
//                    cancel();
//                }catch (IOException e1){
//                    e1.printStackTrace();
//                }
//                if(connectThread != null){
//                    connectThread.cancel();
//                }
//            }
//        }
//
//        public void cancel() throws IOException{
//            if(BS != null){
//                BS.close();
//                BS = null;
//            }
//
//            if(connectThread != null){
//                connectThread.cancel();
//            }
//
//            //sendMessage("Disconnect");
//        }
//
//    }

    public void pairingDevice() {
        B0 = mBluetoothAdapter.getRemoteDevice(B0MA);
        B1 = mBluetoothAdapter.getRemoteDevice(B1MA);
        B2 = mBluetoothAdapter.getRemoteDevice(B2MA);

        Log.d(TAG, "pairing Successful");

        BC0 = new ConnectTask(B0, 0);
        BC1 = new ConnectTask(B1, 1);
        BC2 = new ConnectTask(B2, 2);
        BC0.execute();
        BC1.execute();
        BC2.execute();
    }

    private class ConnectTask extends AsyncTask<Void, Void, Boolean> {

        private BluetoothSocket BS;
        private BluetoothDevice BD;
        private int bt_index = -1;

        ConnectTask(BluetoothDevice BD, int bt_index) {
            this.BD = BD;
            this.bt_index = bt_index;

            try {
                BS = BD.createRfcommSocketToServiceRecord(uuid);
                Log.d(TAG, "create socket for Device" + bt_index);

            } catch (IOException e) {
                Log.e(TAG, "socket create failed " + e.getMessage());
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            mBluetoothAdapter.cancelDiscovery();
            try {

                BS.connect();
            } catch (IOException e) {
                try {
                    BS.close();
                } catch (IOException e1) {
                    Log.e(TAG, "unable to close() " + " socket during connection failure", e1);
                }
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
            if (isSuccess) {
                connected(BS);
            } else {
                Log.d(TAG, "Unable to connect device");
            }
        }

        public void connected(BluetoothSocket BS) {
            mConnectedTask[bt_index] = new ConnectedTask(BS, bt_index);
            mConnectedTask[bt_index].execute();
        }

//        //타이머//
//        TimerTask t = new TimerTask() {
//            @Override
//            public void run() {
//                sendMessage("state");
//
//            }
//        };
    }

    private class ConnectedTask extends AsyncTask<Void, String, Boolean> {

        private InputStream mInputStream;
        private OutputStream mOutputStream;
        private BluetoothSocket BS;
        private int bt_index;

        ConnectedTask(BluetoothSocket BS, int bt_index) {
            this.BS = BS;
            this.bt_index = bt_index;

            try {
                mInputStream = BS.getInputStream();
                mOutputStream = BS.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "socket not created", e);
            }
            Log.d(TAG, "Connected to Device");
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            byte[] readBuffer = new byte[1024];
            int readBufferPosition = 0;

            while (true) {

                if (isCancelled()) return false;

                try {

                    int bytesAvailable = mInputStream.available();

                    if (bytesAvailable > 0) {

                        byte[] packetBytes = new byte[bytesAvailable];

                        mInputStream.read(packetBytes);

                        for (int i = 0; i < bytesAvailable; i++) {

                            byte b = packetBytes[i];
                            if (b == '\n') {
                                byte[] encodedBytes = new byte[readBufferPosition];
                                System.arraycopy(readBuffer, 0, encodedBytes, 0,
                                        encodedBytes.length);
                                String recvMessage = new String(encodedBytes, "UTF-8");

                                readBufferPosition = 0;

                                Log.d(TAG, "recv message: " + recvMessage);
                                publishProgress(recvMessage);
                            } else {
                                readBuffer[readBufferPosition++] = b;
                            }
                        }
                    }
                } catch (IOException e) {

                    Log.e(TAG, "disconnected", e);
                    return false;
                }
            }

        }

        @Override
        protected void onProgressUpdate(String... recvMessage) {
            mConversationArrayAdapter.insert(recvMessage[0],0 );
        }

        @Override
        protected void onPostExecute(Boolean isSucess) {
            super.onPostExecute(isSucess);

            if (!isSucess) {


                closeSocket();
                Log.d(TAG, "Device connection was lost");
                isConnectionError = true;
            }
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);

            closeSocket();
        }

        void closeSocket() {

            try {

                BS.close();
                Log.d(TAG, "close socket()");

            } catch (IOException e2) {

                Log.e(TAG, "unable to close() " +
                        " socket during connection failure", e2);
            }
        }

        void write(String msg) {

            msg += "\n";

            try {
                mOutputStream.write(msg.getBytes());
                mOutputStream.flush();
            } catch (IOException e) {
                Log.e(TAG, "Exception during send", e);
            }

        }
    }

    void sendMessage(Boolean key) {
        String msg;
        if (key) {
            msg = "true";
        } else {
            msg = "false";
        }

        for (int i = 0; i < deviceCount; i++) {
            if (mConnectedTask[i] != null) {
                mConnectedTask[i].write(msg);
                Log.d(TAG, "send message: " + msg);
            }
        }
    }

    void sendMessage(String msg) {

        for (int i = 0; i < deviceCount; i++) {
            if (mConnectedTask[i] != null) {
                mConnectedTask[i].write(msg);
                Log.d(TAG, "send message: " + msg);
            }
        }
    }




}


