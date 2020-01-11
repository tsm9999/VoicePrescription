package com.example.tanush.sih_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView stt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stt=findViewById(R.id.stt);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        stt.setText(str);
    }
}
