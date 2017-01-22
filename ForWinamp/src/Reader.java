import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Chipson on 4/7/2015.
 */
public class Reader extends Thread{
    private ServerSocket s;
    private Socket clientSocket;
    private Controller c;
    private InputStream is = null;
    private boolean stop = false;
    private View v;
    public Reader(ServerSocket s, Controller c) {
        this.s = s;
        this.c = c;
        //setDaemon(true);
    }

    @Override
    public void run() {
        try {

            this.v = new View(c, InetAddress.getLocalHost());
            c.registerView(v);
            this.v.setMessage("Before connect");
            connection();
            this.v.setMessage("After connect");
            while(!stop) {
                if(!clientSocket.isConnected()) {

                    is.close();
                    connection();

                    this.v.setMessage("Renew Connection");
                }

                this.v.setMessage("In switch");
                int n = is.read();
                this.v.setMessage("Reads"  + n);
                switch(n) {
                    case 0:

                        c.previous();
                        break;
                    case 1:
                        c.pausePlay();
                        break;
                    case 2:
                        c.next();
                        break;
                }
            }

            is.close();
            this.v.setMessage("Close");
        } catch (IOException e) {
            this.v.setMessage(e.getMessage());
        }

    }

    public void connection() {

        try {
            clientSocket = s.accept();
            is = clientSocket.getInputStream();
            this.v.setMessage("Connecting");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
