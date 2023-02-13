package com.erza.braculogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText mailbox;
    private EditText passbox;

    void toast(String txt){
        Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }

    class btnAction implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String mail = mailbox.getText().toString();
            String pass = passbox.getText().toString();
            if(mail.isEmpty() || pass.isEmpty())
                toast("Please fill first!!!");
            else
                toast(mail + ": " + pass);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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