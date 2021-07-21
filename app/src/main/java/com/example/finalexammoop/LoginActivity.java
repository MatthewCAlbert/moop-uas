package com.example.finalexammoop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        sessionManager = new SessionManager(LoginActivity.this);
    }

    public void onClickLogin(View view){
        EditText usernameInput = findViewById(R.id.inputUsername);
        EditText passwordInput = findViewById(R.id.inputPassword);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if( username.matches("") || password.matches("") ){
            Toast.makeText(LoginActivity.this, "Tolong diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if( sessionManager.login(username, password) ){
            Toast.makeText(LoginActivity.this, "Berhasil login!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Username / password salah")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    })
                    .show();
        }

    }

    public void onClickGoToRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}