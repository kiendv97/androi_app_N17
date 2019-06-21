package com.example.quizapp.Service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class post_data extends AsyncTask<String, Void, String> {
    OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
        String url;
    JSONObject data;
        public post_data(String url, JSONObject data){
    this.url = url;
    this.data = data;
        }


    @Override
        protected String doInBackground(String... params) {
            String urlString = this.url; // URL to call
        JSONObject data = this.data; //data to post
            OutputStream out = null;
Log.d("data1", String.valueOf(data));
            try {
                RequestBody requestBody = new FormBody.Builder()
                        .add("data",String.valueOf(data))
                        .build();
                Request request = new Request.Builder()
                        .url(urlString)
                        .post(requestBody)
                        .build();
                Response response = httpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return urlString;
        }
    @Override
    protected void onPostExecute(String s) {
            Log.d("EXXXX", s);
        super.onPostExecute(s);
    }

}

