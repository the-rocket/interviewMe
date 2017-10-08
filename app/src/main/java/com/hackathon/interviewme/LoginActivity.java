package com.hackathon.interviewme;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yernar on 07/10/2017.
 */

public class LoginActivity extends AppCompatActivity {

    TextView logo;
    Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    void init() {
        logo = (TextView) findViewById(R.id.img);
        login = (Button) findViewById(R.id.login);
        initview();
    }

    void initview() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        logo.setTypeface(typeface);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
