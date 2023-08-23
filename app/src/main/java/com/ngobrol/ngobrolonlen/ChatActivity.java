package com.ngobrol.ngobrolonlen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;


import com.ngobrol.ngobrolonlen.Models.SocketHandler;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import com.ngobrol.ngobrolonlen.Models.Message;
import com.ngobrol.ngobrolonlen.Models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    MessagesList mMessagesList;
    private MessageInput mMessageInput;
    private MessagesListAdapter<Message> sentMessageAdapter;

    private ImageLoader mImageLoader;
    private RecyclerView mRecyclerView;

    private Socket socket;
    ReceiveThread receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mMessagesList = new MessagesList(this);
        mMessagesList = findViewById(R.id.messagesList);
        mMessageInput = findViewById(R.id.message_input);

        mRecyclerView = findViewById(R.id.suggestionRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mRecyclerView.setVisibility(View.INVISIBLE);

        sentMessageAdapter = new MessagesListAdapter<>("John", mImageLoader);
        mMessagesList.setAdapter(sentMessageAdapter);

        init();
        mMessageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {

                if(!input.toString().trim().isEmpty()){
                    Date date = Calendar.getInstance().getTime();

                    User user = new User("John", "df", null);
                    String message = input.toString();
                    SendTask.sendMessage(message);
                    Message message1 = new Message("Will", message, date, user);

                    sentMessageAdapter.addToStart(message1, true);
                } else {
                    Toast.makeText(ChatActivity.this, "You can't send empty message", Toast.LENGTH_SHORT).show();
                }


                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void init(){
        socket = SocketHandler.getSocket();

        if(socket == null){
            Log.d("null", "coi");
        }

        receive = new ReceiveThread();
        Log.d("nyampe sini", "boi");
        receive.start();
    }
    private void processMessage(String message){

        User user = new User("Paul", "df", null);

        Date date = Calendar.getInstance().getTime();

        String messageToDisplay = message;

        Message message1 = new Message("Doe", messageToDisplay, date, user);

        sentMessageAdapter.addToStart(message1, true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("ayam", "kodok");
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public class ReceiveThread extends Thread {
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    // Read and display incoming messages
                    final String finalMessage  = receivedMessage;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            processMessage(finalMessage);
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}