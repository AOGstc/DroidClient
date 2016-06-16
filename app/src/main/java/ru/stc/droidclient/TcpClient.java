package ru.stc.droidclient;

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import android.util.Log;

public class TcpClient {

    Socket s;
    BufferedReader in;
    BufferedWriter out;

    private static final int TCP_SERVER_PORT = 6000;

    TcpClient(String addr, int port) {
        Connect(addr, port);
    }

    public void Connect(String addr, int port) {
        try {
            /* close previous socket */
            if (s != null)
                s.close();
            /* open new socket */
            s = new Socket(addr, port);
            if (s != null) {
                Log.i("MSG", "Socket opened");
            }
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String Recieve() {
        try {
            String res = null;
            if (in != null)
                res = in.readLine() + System.getProperty("line.separator");
            return res;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized void Send(String msg) {
        try {
            out.write(msg);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        try {
            s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
