package com.example.apptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO: Use checkbox value to determine username state on next launch.
        CheckBox remember = findViewById(R.id.chk_remember);

        login();
    }

    private void login() {
        // Log In Button
        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Debug Login Button
                Log.d("AppTest", "Login was pressed gently.");

                EditText username = findViewById(R.id.username_edit);
                EditText password = findViewById(R.id.password_edit);

                //TODO: Store login credentials and retrieve
                if (username.getText().toString().compareTo("2xb3") == 0 && password.getText().toString().compareTo("2xb3") == 0) {
                    startActivity(new Intent(LoginActivity.this, Recommend.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
