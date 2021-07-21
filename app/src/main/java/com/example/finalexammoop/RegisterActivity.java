package com.example.finalexammoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        sessionManager = new SessionManager(RegisterActivity.this);
    }
    public void onClickRegister(View view){
        EditText usernameInput = findViewById(R.id.inputUsername);
        EditText passwordInput = findViewById(R.id.inputPassword);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if( username.matches("") || password.matches("") ){
            Toast.makeText(RegisterActivity.this, "Tolong diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel newUser = new UserModel(username, password);

        if( sessionManager.register(newUser) ){
            Toast.makeText(RegisterActivity.this, "Berhasil mendaftar!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(RegisterActivity.this, "Username telah terdaftar!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickGoToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}