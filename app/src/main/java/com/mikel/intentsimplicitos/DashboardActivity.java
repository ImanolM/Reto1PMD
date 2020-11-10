package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAIN_ACTIVITY = 0;
    public static final int GAME_ACTIVITY = 3;

    private Button btn_easy = null;
    private Button btn_hard = null;
    private Button btn_normal = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        String concatenado = intent.getExtras().getString("name") + " " + intent.getExtras().getString("lastname");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getText(R.string.welcome_text) + " " + concatenado);


        this.btn_easy = (Button) findViewById(R.id.btn_easy);
        this.btn_hard = (Button) findViewById(R.id.btn_hard);
        this.btn_normal = (Button) findViewById(R.id.btn_normal);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGame("easy");
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGame("hard");
            }
        });
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGame("normal");
            }
        });
    }

    private void goToGame(String level) {
        Intent intent = new Intent(DashboardActivity.this, gameActivity.class);
        intent.putExtra("level", level);
        startActivityForResult(intent, GAME_ACTIVITY);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}