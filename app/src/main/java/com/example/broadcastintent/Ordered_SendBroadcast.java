package com.example.broadcastintent;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ordered_SendBroadcast extends AppCompatActivity {
    Button btnSend;
    NetworkChangeReceiver networkChangeReceiver;
    //final String ACTION_BROADCAST = "action";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordered_sendbroadcase);

        btnSend = (Button) findViewById(R.id.btn_sendbroadcast);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("action");
                intent.setPackage("com.example.broadcastintent");
                sendOrderedBroadcast(intent, null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBroadcastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }

    private void initBroadcastReceiver() {
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1);
        intentFilter.addAction("action");
        registerReceiver(networkChangeReceiver, intentFilter);
    }
}
