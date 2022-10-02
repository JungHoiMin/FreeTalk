package com.veritas.freetalkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.veritas.freetalkapp.websocket.WsListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("ws://192.168.219.100:8080/ws/chat")
                .build();
        WebSocketListener listener = new WsListener();

        client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();

    }
}