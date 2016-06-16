package ru.stc.droidclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Alex on 15.06.2016.
 */
public class ClientTask extends AsyncTask<String, String, Void> {
    AppCompatActivity A;
    String msgIn, msgOut;

    TcpClient Client;

    ClientTask(AppCompatActivity A) {
        this.A = A;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(String... params) {
        String ip = params[0] ;
        int port;
        String str_port = params[1];
        try {
            port = Integer.parseInt(str_port);
        }
        catch (NumberFormatException e) {
            port = 10000;
        }

        Client = new TcpClient(ip, port);
        while(true) {
            //synchronized (msgIn) {
                msgIn = Client.Recieve();

                if (msgIn == null)
                    break;
            if (msgIn.length() > 1)
                publishProgress(msgIn);
            //}
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        EditText Log = (EditText) A.findViewById(R.id.rxMsg);
        Log.setText(msgIn);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
