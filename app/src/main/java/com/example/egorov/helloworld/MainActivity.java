package com.example.egorov.helloworld;

import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView robotImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotImage = (ImageView) findViewById(R.id.robotId);


        new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        }.start();

    }


}
