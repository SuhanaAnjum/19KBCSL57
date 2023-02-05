import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main (String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 3000);

        OutputStream   oStream = sock.getOutputStream();
        InputStream    iStream = sock.getInputStream();
        PrintWriter    pWriter = new PrintWriter(oStream, true);
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader rcvRead = new BufferedReader(new InputStreamReader(iStream));

        System.out.println("Start chitchat, type and press enter key");
        String rcvMsg, sendMsg;

        while (true) {
            sendMsg = keyRead.readLine();
            pWriter.println(sendMsg);
            pWriter.flush();

            if ((rcvMsg = rcvRead.readLine()) != null)
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