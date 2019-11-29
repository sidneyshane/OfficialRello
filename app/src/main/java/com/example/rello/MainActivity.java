package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rello.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

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
