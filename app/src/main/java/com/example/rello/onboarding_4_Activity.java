package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class onboarding_4_Activity extends AppCompatActivity {

    Button Btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_4_);

        Btn_start = findViewById(R.id.start_btn);
        Btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomepage();
            }
        });

    }
    public void openHomepage(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
