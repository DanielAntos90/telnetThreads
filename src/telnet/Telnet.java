package telnet;

import java.io.InputStream;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Telnet {
    private Socket s;
    private static Logger logger = Logger.getLogger(Telnet.class.getName());

    public Telnet(String host, int port) throws UnknownHostException, IOException {
        try {
            s = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            s.close();
        }
    }
    public void start() throws IOException, InterruptedException {

        OutputStream out = s.getOutputStream();
        InputStreamThread input = new InputStreamThread(s.getInputStream());
        input.start();

        while(true) {
            if (input.isAlive() && System.in.available() > 0) {
                out.write(System.in.read());
                out.flush();
            } else {
                logger.log(Level.INFO,"Telnet ended!");
                return;
            }

            Thread.sleep(10);
        }

    }
}
