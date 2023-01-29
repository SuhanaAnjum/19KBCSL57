import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main (String[] args) throws Exception {
        ServerSocket serSock = new ServerSocket(3000);
        Socket sock = serSock.accept();

        OutputStream   oStream = sock.getOutputStream();
        InputStream    iStream = sock.getInputStream();
        PrintWriter    pWriter = new PrintWriter(oStream, true);
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader rcvRead = new BufferedReader(new InputStreamReader(iStream));

        System.out.println("Server is ready for chatting");
        String rcvMsg, sendMsg;

        while (true) {
            if ((rcvMsg = rcvRead.readLine()) != null)
                System.out.println(rcvMsg);

            sendMsg = keyRead.readLine();
            pw.println(sendMsg);
            pw.flush();
        }
    }
}

/*
 * Output
 *  Compile: javac Server.java
 *  Run:     java Server
 *  Server is ready for chatting
 */