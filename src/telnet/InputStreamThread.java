package telnet;

import java.io.InputStream;


public class InputStreamThread extends Thread {
    private InputStream input;
    public InputStreamThread(InputStream input) {
        this.input = input;
    }

    public void run() {
        try {
            while(true) {
                if (this.input.available() > 0) {
                    System.out.print((char)this.input.read());
                }

                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}