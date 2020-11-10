package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAIN_ACTIVITY = 0;

    private Button nextButton = null;
    private ArrayList<String> things = null;
    private ImageView imageView = null;
    private Integer currentImagePosition = 0;
private TextView objectTitle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = getIntent();
        this.nextButton = (Button) findViewById(R.id.nextButton);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.objectTitle = (TextView) findViewById(R.id.objectTitle);

        String concatenado = intent.getExtras().getString("user");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getText(R.string.welcome_text) + " " + concatenado);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextObject();
            }
        });
    }

    public void nextObject() {
        Context context = imageView.getContext();
        if (imageView.getDrawable() == null) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(DashboardActivity.this);
            this.things = dataBaseHelper.getWords();
            int id = context.getResources().getIdentifier(things.get(0), "drawable", context.getPackageName());
            imageView.setImageResource(id);
        } else {
            currentImagePosition++;
            if (currentImagePosition > this.things.size() - 1) {
                currentImagePosition = 0;
                int id = context.getResources().getIdentifier(things.get(currentImagePosition), "drawable", context.getPackageName());
                imageView.setImageResource(id);
            } else {
                int id = context.getResources().getIdentifier(things.get(currentImagePosition), "drawable", context.getPackageName());
                imageView.setImageResource(id);
            }

        }
        objectTitle.setText(things.get(currentImagePosition));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}