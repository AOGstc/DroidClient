package ru.stc.droidclient;

import android.os.AsyncTask;

/**
 * Created by Alex on 15.06.2016.
 */
public class ClientTask extends AsyncTask<Void, Void, Void> {

    String msgIn, msgOut;

    TcpClient Client;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Client = new TcpClient();

        while(true) {
            msgIn = Client.Recieve();
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
