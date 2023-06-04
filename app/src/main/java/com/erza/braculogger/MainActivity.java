package com.erza.braculogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    private final String logTag = "Edebug";
    private Button btn;
    private EditText mailbox;
    private EditText passbox;
    private final OkHttpClient signal = new OkHttpClient();
    SharedPreferences memory;
    SharedPreferences.Editor memEdit;

    void toast(String txt){
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }


    class postMan extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {

            String mail = mailbox.getText().toString();
            String pass = passbox.getText().toString();

            FormBody body = new FormBody.Builder()
                    .add("username", mail)
                    .add("password", pass)
                    .build();
            Request request = new Request.Builder()
                    .url("http://wifi.bracu.ac.bd/login")
                    .post(body)
                    .build();
            memEdit.putString("mail", mail);
            memEdit.putString("pass", pass);
            memEdit.commit();
            try {
                Response response = signal.newCall(request).execute();
                return mail+","+pass+"n"+response.body().string();
            } catch (Exception e) {
                return e.toString();
            }
        }
        @Override
        protected void onPostExecute(String result){
            toast(result);
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
                new postMan().execute();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            memory = getSharedPreferences("BracU",MODE_PRIVATE);
            memEdit = memory.edit();
            btn = findViewById(R.id.btn_connect);
            mailbox = findViewById(R.id.mailbox);
            passbox = findViewById(R.id.passbox);

            mailbox.setText(memory.getString("mail",""));
            passbox.setText(memory.getString("pass",""));

            btn.setOnClickListener(new btnAction());
        }catch (Exception e){
            toast(e.toString());
        }
    }
}
