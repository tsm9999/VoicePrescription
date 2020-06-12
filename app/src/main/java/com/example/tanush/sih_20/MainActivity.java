package com.example.tanush.sih_20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView stt;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stt=findViewById(R.id.stt);
        Intent intent = getIntent();
        str = intent.getStringExtra("mess");
        Log.e("msg:", str.toString());
        stt.setText(str);
    }


}
