package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginButton = null;
    private Button signUpButton = null;
    public static final int DASHBOARD_ACTIVITY = 1;
    public static final int SIGNUP_ACTIVITY = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loginButton = (Button) findViewById(R.id.btnLogin);
        this.signUpButton = (Button) findViewById(R.id.btnSignUp);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    public void login() {
        EditText txtLogin = (EditText) findViewById(R.id.txtLogin);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        String loginText = txtLogin.getText().toString().trim();
        String passwordText = txtPassword.getText().toString().trim();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        User userLogin = dataBaseHelper.getUser(loginText, passwordText);

        if(userLogin!=null){
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("User","userLogin");
            startActivityForResult(intent, DASHBOARD_ACTIVITY);
        }
        else{
            showToast(getString(R.string.wrong_credentials));
        }

    }

    public void signUp() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(intent, SIGNUP_ACTIVITY);
    }

    public void showToast(CharSequence message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}