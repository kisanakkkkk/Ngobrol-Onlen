package com.ngobrol.ngobrolonlen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button chat = findViewById(R.id.chatButton);

        chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Asiap santuy", Toast.LENGTH_SHORT).show();
                Log.d("kodok", "bebek");
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }

        });

    }
}