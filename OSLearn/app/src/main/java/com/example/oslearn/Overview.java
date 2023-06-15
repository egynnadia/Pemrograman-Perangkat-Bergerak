package com.example.oslearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Overview.this, Dashboard.class);
        startActivity(i);
    }
}