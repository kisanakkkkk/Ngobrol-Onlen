package com.ngobrol.ngobrolonlen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngobrol.ngobrolonlen.Models.SocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private EditText hostname;
    private EditText port;

    public static Socket socket;



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

                Toast.makeText(MainActivity.this, "Asiap santuy", Toast.LENGTH_SHORT).show();

                connectToServer();

                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    public class ConnectTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String hostnameString = hostname.getText().toString();
                Integer portName = Integer.parseInt(port.getText().toString());
                socket = new Socket(hostnameString, portName);
                SocketHandler.setSocket(socket);

                Log.d("hostname", hostnameString);
                Log.d("port", String.valueOf(portName));


                // Start a thread to listen for incoming messages
//                receive = new ReceiveThread();
//                receive.start();
            } catch (IOException e) {
                Log.d("apakah ke sini", "sini");
                e.printStackTrace();
            }
            return null;
        }
    }
    private void connectToServer() {
        new ConnectTask().execute();
    }



}