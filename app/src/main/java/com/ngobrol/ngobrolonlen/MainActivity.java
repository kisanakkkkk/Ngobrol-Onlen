package com.ngobrol.ngobrolonlen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngobrol.ngobrolonlen.Models.SocketHandler;


public class MainActivity extends AppCompatActivity {

    private EditText hostname;
    private EditText port;

    private ConnectTask connect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chat = findViewById(R.id.chatButton);

        hostname = findViewById(R.id.hostname);
        port = findViewById(R.id.port);


        chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validateAndConnect();

            }
        });

    }
    private void validateAndConnect(){
        if(hostname.getText().toString().trim().isEmpty() || port.getText().toString().trim().isEmpty()){
            Toast.makeText(MainActivity.this, "Hostname and port cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(hostname.getText().toString(), port.getText().toString());
        connect = new ConnectTask();
        connect.connectToServer(hostname, port);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SocketHandler.getSocket() != null && SocketHandler.getSocket().isConnected()) {
                    Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to Connect", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);
    }
}