package ru.stc.droidclient;

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import android.util.Log;

public class TcpClient extends Thread{

    private static final int TCP_SERVER_PORT = 6000;

    TcpClient() {
        super("ClientThread");
        System.out.println("Создан второй поток " + this);
        start();
    }

    private void runTcpClient() {
        try {
            //TODO use InetAddress
            Socket s = new Socket("localhost", TCP_SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            //send output msg
            String outMsg = "TCP connecting to " + TCP_SERVER_PORT + System.getProperty("line.separator");
            out.write(outMsg);
            out.flush();
            Log.i("TcpClient", "sent: " + outMsg);
            //accept server response
            String inMsg = in.readLine() + System.getProperty("line.separator");
            Log.i("TcpClient", "received: " + inMsg);
            //close connection
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while(true) {
                runTcpClient();

        System.out.println("Второй поток завершён");
    }

}
