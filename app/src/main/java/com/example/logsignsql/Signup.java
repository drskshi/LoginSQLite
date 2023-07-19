package com.example.logsignsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logsignsql.DatabaseHelper;
import com.example.logsignsql.Login;
import com.example.logsignsql.R;

public class Signup extends AppCompatActivity {
    EditText name, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Login.class));
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = name.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                if (databaseHelper.insertUser(fullName, email, password)) {
                    Toast.makeText(Signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this, Login.class));
                } else {
                    Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
