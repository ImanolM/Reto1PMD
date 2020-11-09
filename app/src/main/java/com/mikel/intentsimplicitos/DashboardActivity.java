package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int MAIN_ACTIVITY = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();

        String concatenado = intent.getExtras().getString("name") +  " " + intent.getExtras().getString("lastname");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getText(R.string.welcome_text) + " " + concatenado);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}