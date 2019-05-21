//package com.impact.tripble;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//
//public class WIFI extends AppCompatActivity {
//
//    TextView state;
//
//    private static final String TAG = "TcpClient";
//    private boolean isConnected = false;
//
//    private ArrayAdapter<String> mConversationArrayAdapter;
//    private String mServerIP = null;
//    private Socket mSocket = null;
//    private PrintWriter mOut;
//    private BufferedReader mIn;
//    private Thread mReceiverThread = null;
//    private boolean input;
//
//    @Override
//    public void onCreate(Bundle savedInstandeState){
//        super.onCreate(savedInstandeState);
//        setContentView(R.layout.nfc);
//
//        Intent intent = getIntent();
//        state = (TextView) findViewById(R.id.wifi_state);
//       intent.getBooleanExtra("input", input);
//
//       if(!isConnected) showErrorDialog("서버로 접속된후 다시 해보세요.");
//       else{
//           new Thread(new SenderThread(input)).start();
//       }
//
////       mConversationArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
////       mMessageListview.setAdapter(mConversationArrayAdapter);
//
//        new Thread(new ConnectThread("d",1)).start();
//    }
//
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        isConnected = false;
//    }
//
//    private class ConnectThread implements Runnable{
//        private String serverIp;
//        private int serverPort;
//
//        ConnectThread(String ip, int port){
//            serverIp = ip;
//            serverPort = port;
//
//        }
//
//        @Override
//        public void run(){
//
//            try{
//                mSocket = new Socket(serverIp, serverPort);
//
//                mServerIP = mSocket.getRemoteSocketAddress().toString();
//            } catch (UnknownHostException e){
//                Log.d(TAG, "ConnectThread: can`t find host");
//            }
//            catch(SocketTimeoutException e{
//                Log.d(TAG, "ConnectThread: timeout");
//            }
//            catch(Exception e){
//                Log.e(TAG, ("connectThread: " + e.getMessage()));
//            }
//            if(mSocket != null){
//                try{
//                    mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream(), "UTF-8")), true);
//                    mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));
//
//                    isConnected = true;
//                }catch(IOException e){
//                    Log.e(TAG, "ConnectThread: " + e.getMessage());
//                }
//            }
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if(isConnected){
//                        Log.d(TAG, "connected to " + severIP);
//
//                        mReceiverThread = new Thread(new ReciverThread());
//                        mReceiverThread.start();
//                    }else{
//                        Log.d(TAG, "failed to connect to sercer" + serverIP);
//                    }
//                }
//            });
//        }
//    }
//
//    private class SenderThread implements  Runnable{
//        private String msg;
//    }
//
//}
//
//
