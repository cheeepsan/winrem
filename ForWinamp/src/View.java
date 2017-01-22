import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

/**
 * Created by Chipson on 1/22/2017.
 */
public class View {
    public Controller c;
    private JFrame frame;
    private JTextArea jTextArea;
    public View(Controller c, InetAddress ip) {
        this.c = c;

        makeFrame(ip.toString());
    }

    protected void makeFrame(String ip) {
        this.frame = new JFrame(ip);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jTextArea = new JTextArea();
        this.frame.add( jTextArea );
        this.frame.pack();

        this.frame.setVisible(true);
    }
    public void setMessage(String msg) {
        this.jTextArea.append(msg + "\n");
    }
}
