package com.ngobrol.ngobrolonlen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private EditText hostname;
    private EditText port;

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
                String hostnameString = hostname.getText().toString();
                String portString = port.getText().toString();
                Toast.makeText(MainActivity.this, "Asiap santuy", Toast.LENGTH_SHORT).show();
                Log.d("hostname", hostnameString);
                Log.d("port", portString);
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }

        });

    }
}