package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rello.ui.login.LoginActivity;
import com.sendbird.android.SendBird;

public class MainActivity extends AppCompatActivity {
    public static final String APP_ID = "243BA639-D510-4A48-9834-CF82B1ABB4E9";
    Button Btn_Create, Btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_Create = findViewById(R.id.create_account);
        Btn_Login = findViewById(R.id.log_in);
        Btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountCreation();
            }
        });
        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        SendBird.init(APP_ID, this.getApplicationContext());
    }
    public void openAccountCreation(){
        Intent intent = new Intent(this, create_account_Activity.class);
        startActivity(intent);
    }
    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
