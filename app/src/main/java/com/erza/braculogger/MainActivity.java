package com.erza.braculogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private  String logTag = "Edebug";
    private Button btn;
    private EditText mailbox;
    private EditText passbox;
    private final OkHttpClient signal = new OkHttpClient();

    void toast(String txt){
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }

    class postMan implements Runnable{

        @Override
        public void run() {
            String mail = mailbox.getText().toString();
            String pass = passbox.getText().toString();

            FormBody body = new FormBody.Builder()
                    .add("dst","http://192.168.0.1/")
                    .add("popup","true")
                    .add("email", mail)
                    .add("password", pass)
                    .build();
            Request request = new Request.Builder()
                    .url("http://wifi.bracu.ac.bd/login")
                    .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
                    .addHeader("Accept-Language","en-US,en;q=0.9")
                    .addHeader("Cache-Control","max-age=0")
                    .addHeader("Connection","keep-alive")
                    .addHeader("Content-Type","application/x-www-form-urlencoded")
                    .addHeader("Origin","http://115.127.36.92")
                    .addHeader("Referer","http://115.127.36.92/")
                    .addHeader("Sec-GPC","1")
                    .addHeader("Upgrade-Insecure-Requests","1")
                    .addHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")
                    .post(body)
                    .build();
            try {
                Response response = signal.newCall(request).execute();
                Log.i(logTag, mail+","+pass);
                Log.i(logTag, response.body().string());
            } catch (Exception e) {
                Log.i(logTag, "Fail: " + e.toString());
            }
        }
    }

    class btnAction implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String mail = mailbox.getText().toString();
            String pass = passbox.getText().toString();
            if(mail.isEmpty() || pass.isEmpty())
                toast("Please fill first!!!");
            else
                new Thread(new postMan()).start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(logTag, "Test");

        try {
            btn = findViewById(R.id.btn_connect);
            mailbox = findViewById(R.id.mailbox);
            passbox = findViewById(R.id.passbox);

            btn.setOnClickListener(new btnAction());
        }catch (Exception e){
            toast(e.toString());
        }
    }
}