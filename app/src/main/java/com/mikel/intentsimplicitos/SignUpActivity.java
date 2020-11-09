package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpButton = null;
    private EditText nameField = null;
    private EditText lastnameField = null;
    private EditText emailField = null;
    private EditText loginField = null;
    private EditText passwordField = null;


    public static final int MAIN_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.signUpButton = (Button) findViewById(R.id.signUpButton);
        this.loginField = (EditText) findViewById(R.id.txtLogin);
        this.passwordField = (EditText) findViewById(R.id.txtPassword);
        this.nameField = (EditText) findViewById(R.id.txtName);
        this.lastnameField = (EditText) findViewById(R.id.txtLastname);
        this.emailField = (EditText) findViewById(R.id.txtEmail);

        this.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;

                boolean correct = validateData();

                if (correct) {
                    user = new User(nameField.getText().toString().trim(), lastnameField.getText().toString().trim(), emailField.getText().toString().trim(), loginField.getText().toString().trim(), passwordField.getText().toString().trim());
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUpActivity.this);
                    boolean success = dataBaseHelper.addOne(user);
                    if (success) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getText(R.string.error_insert), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getText(R.string.error_empty_fields), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateData() {
        if (loginField.getText().toString().trim().equalsIgnoreCase("") == false
                && passwordField.getText().toString().trim().equalsIgnoreCase("") == false && nameField.getText().toString().trim().equalsIgnoreCase("") == false
                && lastnameField.getText().toString().trim().equalsIgnoreCase("") == false && emailField.getText().toString().trim().equalsIgnoreCase("") == false) {
            return true;
        } else {
            return false;
        }
    }
}