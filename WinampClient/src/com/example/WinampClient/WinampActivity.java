package com.example.WinampClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;

import java.net.Socket;



/**
 * Created by Chipson on 4/7/2015.
 */
public class WinampActivity extends Activity {
    private Socket socket;
    private Button prev, play, next;
    public OutputStream os = null;
    private byte bytes;
    private SocketService s;
    public WinampActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winamp);



       /* try {
            this.os = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        prev = (Button)findViewById(R.id.prev);
        play = (Button)findViewById(R.id.play);
        next = (Button)findViewById(R.id.next);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bytes = 0;
                if (os != null) {
                    try {
                        os.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bytes = 1;
                if (os != null) {
                    try {
                        os.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bytes = 2;
                System.out.println("DATA SENT");
                System.out.println(os.toString());
                if (os != null) {
                    try {
                        os.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            SocketService.LocalBinder binder = (SocketService.LocalBinder) service;

            s = binder.getService();
            try {
                os = s.getSocket().getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }


    };
    @Override
    public void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent(this, SocketService.class);
        bindService(serviceIntent, mConnection, Context.BIND_NOT_FOREGROUND);
        System.out.println(serviceIntent.toString());


    }
    public void closeOs() {
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
