package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.oslearn.databinding.ActivityKuisBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Kuis extends AppCompatActivity {

    CardView q1, q2, q3, q4, q5, q6;
    String Kuis1, Kuis2, Kuis3, Kuis4, Kuis5, Kuis6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        q1 = findViewById(R.id.selectkuis1);
        q2 = findViewById(R.id.selectKuis2);
        q3 = findViewById(R.id.selectKuis3);
        q4 = findViewById(R.id.selectKuis4);
        q5 = findViewById(R.id.selectKuis5);
        q6 = findViewById(R.id.selectKuis6);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis1");
                startActivity(i);
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis2");
                startActivity(i);
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis3");
                startActivity(i);
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis4");
                startActivity(i);
            }
        });

        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis5");
                startActivity(i);
            }
        });

        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Kuis.this, halamanKuis.class );
                i.putExtra("KuisTerpilih", "Kuis6");
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Kuis.this, Dashboard.class);
        startActivity(i);
    }
}