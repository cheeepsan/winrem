package com.example.WinampClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Chipson on 4/7/2015.
 */
public class MainActivity extends Activity {
    TextView textResponse;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;
    private Socket socket = null;
    private WinampActivity a;
    private SocketService s;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui);
        Intent serviceIntent = new Intent(MainActivity.this, SocketService.class);
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
        editTextAddress = (EditText) findViewById(R.id.address);
        editTextPort = (EditText) findViewById(R.id.port);
        buttonConnect = (Button) findViewById(R.id.connect);
        buttonClear = (Button) findViewById(R.id.clear);


        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    textResponse.setText("");
                } catch (NullPointerException e) {

                }
            }
        });
    }

    OnClickListener buttonConnectOnClickListener =
            new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    try {
                        MyClientTask myClientTask = new MyClientTask(
                                editTextAddress.getText().toString(),
                                Integer.parseInt(editTextPort.getText().toString()));
                        myClientTask.execute();
                    } catch (NumberFormatException e) {
                        System.out.println("No addr");
                    }
                }
            };

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            SocketService.LocalBinder binder = (SocketService.LocalBinder) service;
            s = binder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response = "";

        public MyClientTask(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            try {





    /*
     * notice:
     * inputStream.read() will block if no data return
     */
                while (socket == null) {
                    socket = new Socket(dstAddress, dstPort);
                    System.out.println("NO CONNECTION");

                }

                System.out.println(socket.toString());
                s.setSocket(socket);
                Intent intent = new Intent(MainActivity.this, WinampActivity.class);
                startActivity(intent);

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }


            return null;
        }


        public void closeConnection() {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }

        public void openWinamp(View view) {

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
        }

        /**
         * Defines callbacks for service binding, passed to bindService()
         */

    }


}


