package com.example.rello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class create_account_2_Activity extends AppCompatActivity {

    Button Btn_finish, Btn_previous;
    private FirebaseAuth firebaseAuth;
    EditText dob, country, province, city, street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_2_);

        dob = findViewById(R.id.editText);
        country = findViewById(R.id.editText3);
        province = findViewById(R.id.editText4);
        city = findViewById(R.id.editText5);
        street = findViewById(R.id.editText6);

        firebaseAuth = FirebaseAuth.getInstance();
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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser!= null) {
            startActivity(new Intent(getApplicationContext(), home_Activity.class));
        }
    }

}
