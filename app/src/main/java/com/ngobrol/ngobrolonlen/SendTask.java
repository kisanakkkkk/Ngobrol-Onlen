package com.ngobrol.ngobrolonlen;

import static com.ngobrol.ngobrolonlen.MainActivity.out;

import android.os.AsyncTask;

public class SendTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... messages) {
        if (out != null) {
            out.println(messages[0]);
        }
        return null;
    }

    public static void sendMessage(final String message) {
        new SendTask().execute(message);
    }
}

