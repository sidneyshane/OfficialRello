package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rello.ui.login.LoginActivity;

public class create_account_Activity extends AppCompatActivity {

    Button Btn_next, Btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_);

        Btn_next = findViewById(R.id.continue_btn);
        Btn_login = findViewById(R.id.login_btn);
        Btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNext();
            }
        });
        Btn_login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        }));

        //Do something with the user information here

    }
    public void openNext(){
        Intent intent = new Intent(this, create_account_2_Activity.class);
        startActivity(intent);
    }
    public  void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
