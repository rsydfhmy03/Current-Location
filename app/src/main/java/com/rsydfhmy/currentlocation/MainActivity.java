package com.rsydfhmy.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button) findViewById(R.id.btnnext);
        next.setOnClickListener((View)->{
            Intent intent = new Intent(MainActivity.this, CurrentLocationn.class);
            startActivity(intent);
        });
    }
}