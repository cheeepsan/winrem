import com.qotsa.exception.InvalidHandle;
import com.qotsa.jni.controller.WinampController;

/**
 * Created by Chipson on 4/7/2015.
 */
public class Controller {
    private WinampController w;
    private View v;


    public Controller() {
        w = new WinampController();



    }
    public void registerView(View v) {
        this.v = v;
        try {
            this.w.run();
        } catch (Exception e) {
            this.v.setMessage("test");
        }
    }
    public void pausePlay() {
        try {
            switch (w.getStatus()) {

                case 1:
                    w.pause();
                    break;
                case 0:
                    w.play();
                    break;
                case 3:
                    w.resume();
                    break;
                default:
                    break;
            }
        } catch (InvalidHandle e) {

        }

    }

    public void next() {
        try {

            w.nextTrack();
            this.v.setMessage(w.getTitle());

        } catch (InvalidHandle e) {
            this.v.setMessage(e.getMessage());
        }
    }

    public void previous() {
        try {
            w.previousTrack();
            this.v.setMessage(w.getTitle());
        } catch (InvalidHandle e) {

        }
    }

    public void volumeUp() {
        try {
            w.increaseVolume();
        } catch (InvalidHandle e) {

        }
    }

    public void volumeDown() {
        try {
            w.decreaseVolume();
        } catch (InvalidHandle e) {

        }
    }

    public void runWinamp() {
        try {
            w.run();
        } catch (Exception e) {

        }
    }

    public void searchForName(String name) {

    }
}
