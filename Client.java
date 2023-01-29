import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main (String[] args) {
        Socket sock = new Socket("127.0.0.1", 3000);

        OutputStream  oStream = sock.getOutputStream();
        InputStream   iStream = sock.getInputStream();
        PrintWriter   pWriter = new PrintWriter(oStream, true);
        BufferdReader keyRead = new BufferdReader(new InputStreamReader(System.in));
        BufferdReader rcvRead = new BufferdReader(new InputStreamReader(iStream));

        System.out.println("Start chitchat, type and press enter key");
        String rcvMsg, sendMsg;

        while (true) {
            sendMsg = keyRead.readLine();
            pw.println(sendMsg);
            pw.flush();

            if ((rcvMsg = rcvRead.readLine())) != null)
                System.out.println(rcvMsg);
        }
    }
}

/*
 * Output
 *  Compile: javac Client.java
 *  Run:     java Client
 *  Start chitchat, type and press enter key
 */