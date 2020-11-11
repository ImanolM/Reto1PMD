package com.mikel.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAIN_ACTIVITY = 0;

    private Button nextButton = null;
    private ArrayList<String> things = null;
    private ImageView imageView = null;
    private ImageButton imageButton=null;
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
        this.imageButton = (ImageButton) findViewById( R.id.imgbtn_Info );

        String concatenado = intent.getExtras().getString("user");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getText(R.string.welcome_text) + " " + concatenado);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextObject();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goInfoWeb();
            }
        });
    }

    private void goInfoWeb() {
        String url = setUrl(this.objectTitle);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private String setUrl(TextView title) {
        Resources res = getResources();
        String toCompare= title.getText().toString();
        String url=null;
        switch(toCompare){
            case "balon":
                url=String.format(res.getString(R.string.balon_url));
                break;
            case "limon":
                url=String.format(res.getString(R.string.limon_url));
                break;
            case "guitarra":
                url=String.format(res.getString(R.string.guitarra_url));
                break;
            case "lampara":
                url=String.format(res.getString(R.string.lampara_url));
                break;
            case "ordenador":
                url=String.format(res.getString(R.string.ordenador_url));
                break;
            case "patata":
                url=String.format(res.getString(R.string.patata_url));
                break;
            case "raqueta":
                url=String.format(res.getString(R.string.raqueta_url));
                break;
            case "sol":
                url=String.format(res.getString(R.string.sol_url));
                break;
            default:
                Toast.makeText(getApplicationContext(),getText(R.string.error_image),Toast.LENGTH_SHORT).show();
                break;
        }

        return url;
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