package com.example.quizapp.Service;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetch_data extends AsyncTask<Void,Void,Void> {
    public CallBackData delegate = null;
    String data;
    String URI;
    Object payload;
    public fetch_data(String URI, Object payload) {
        this.URI = URI;
        this.payload = payload;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(URI);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while(line != null ) {
//                Log.d("Runner", bufferedReader.readLine());
//                line = bufferedReader.readLine();
//                data += line;
//            }
    data = bufferedReader.readLine();
        }catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {

            delegate.onReceiveData(data);
        }catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
