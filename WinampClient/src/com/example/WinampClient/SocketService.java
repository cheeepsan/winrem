package com.example.WinampClient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.net.Socket;

/**
 * Created by Chipson on 4/9/2015.
 */
public class SocketService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private Socket s = null;
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        SocketService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SocketService.this;
        }
    }

    public void setSocket(Socket s) {
        System.out.println(s.toString());
        if(s != null) {
            this.s = s;

        }

    }
    public Socket getSocket() {
        return this.s;
    }
    @Override
    public String toString() {
        return this.s.toString();
    }
}
