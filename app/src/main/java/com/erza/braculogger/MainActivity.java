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

    class btnAction implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String mail = mailbox.getText().toString();
            String pass = passbox.getText().toString();
            if(mail.isEmpty() || pass.isEmpty())
                Toast.makeText(getApplicationContext(), "Please fill first!!!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), mail + ": " + pass, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_connect);
        mailbox = findViewById(R.id.mailbox);
        passbox = findViewById(R.id.passbox);

        btn.setOnClickListener(new btnAction());
    }
}