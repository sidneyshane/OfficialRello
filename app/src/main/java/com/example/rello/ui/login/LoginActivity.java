package com.example.rello.ui.login;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rello.home_Activity;
import com.example.rello.create_account_Activity;
import com.example.rello.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button registerButton, loginButton;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(getApplicationContext(), home_Activity.class));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.sign_up);
        loginButton = (Button) findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (TextUtils.isEmpty(emailString)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordString)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                }

                if (passwordString.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), home_Activity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "E-mail or password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), create_account_Activity.class));
            }
        });

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), home_Activity.class));
        }
    }
}
