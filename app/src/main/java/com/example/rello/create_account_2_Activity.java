package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class create_account_2_Activity extends AppCompatActivity {

    Button Btn_finish, Btn_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_2_);

        Btn_finish = findViewById(R.id.finish_btn);
        Btn_previous = findViewById(R.id.previous_btn);
        Btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnboarding();
            }
        });
        Btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviousScreen();
            }
        });
    }
    public void openOnboarding(){
        Intent intent = new Intent(this, onboarding_1_Activity.class);
        startActivity(intent);
    }
    public void openPreviousScreen(){
        Intent intent = new Intent(this, create_account_Activity.class);
        startActivity(intent);
    }
}
