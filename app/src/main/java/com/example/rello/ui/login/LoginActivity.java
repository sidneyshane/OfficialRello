package com.example.rello.ui.login;


import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rello.home_Activity;
import com.example.rello.onboarding_1_Activity;
import com.example.rello.create_account_Activity;
import com.example.rello.R;
import com.example.rello.ui.login.LoginViewModel;
import com.example.rello.ui.login.LoginViewModelFactory;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

public class LoginActivity extends AppCompatActivity {
    final static String APP_ID = "243BA639-D510-4A48-9834-CF82B1ABB4E9";
    private LoginViewModel loginViewModel;
    private Button Btn_Signup;
    private Button btnLogin;
    private SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        mPrefs = getSharedPreferences("label", 0);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        SendBird.init(APP_ID, this.getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = usernameEditText.getText().toString();
                userId = userId.replaceAll("\\s", "");

                String password = passwordEditText.getText().toString();
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putString("userId", userId).commit();
                mEditor.putString("password", password).commit();
                connectToSendBird(userId, password);
            }
        });

        Btn_Signup = findViewById(R.id.sign_up);
        Btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    /**
     * Attempts to connect a user to SendBird.
     * @param userId The unique ID of the user.
     * @param userNickname The user's nickname, which will be displayed in chats.
     */
    private void connectToSendBird(final String userId, final String userNickname) {
        btnLogin.setEnabled(false);

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            LoginActivity.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    btnLogin.setEnabled(true);
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);

                Intent intent = new Intent(LoginActivity.this, home_Activity.class);
                intent.putExtra("userID", userId);
                intent.putExtra("userNickname", userNickname);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Updates the user's nickname.
     * @param userNickname The new nickname of the user.
     */
    private void updateCurrentUserInfo(String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            LoginActivity.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        openOnboarding();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void openOnboarding(){
        Intent intent = new Intent(this, onboarding_1_Activity.class);
        startActivity(intent);
    }

    private void openSignUp(){
        Intent intent = new Intent(this, create_account_Activity.class);
        startActivity(intent);
    }
}
