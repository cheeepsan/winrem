import com.qotsa.jni.controller.WinampController;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.File;

/**
 * Created by Chipson on 4/7/2015.
 */
public class Main {
    public static void main(String[] args) {
        File f = new File("wpcom.dll");
        System.load(f.getAbsolutePath());
        Controller c = new Controller();
        try {
            ServerSocket s = new ServerSocket(1111);
            Reader r = new Reader(s, c);

            r.start();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
