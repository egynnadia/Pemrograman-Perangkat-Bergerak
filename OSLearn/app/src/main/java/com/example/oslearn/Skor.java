package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Skor extends AppCompatActivity {

    String[] namakuis = {"kuis1", "kuis2", "kuis3", "kuis4", "kuis5", "kuis6"};
    TextView kuis1, kuis2, kuis3, kuis4, kuis5, kuis6;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kuis1 = findViewById(R.id.nilaiKuis1);
        kuis2 = findViewById(R.id.nilaiKuis2);
        kuis3 = findViewById(R.id.nilaiKuis3);
        kuis4 = findViewById(R.id.nilaiKuis4);
        kuis5 = findViewById(R.id.nilaiKuis5);
        kuis6 = findViewById(R.id.nilaiKuis6);

        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://oslearn-fee4d-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Kuis1 = snapshot.child("Kuis1").getValue(String.class);
                String Kuis2 = snapshot.child("Kuis2").getValue(String.class);
                String Kuis3 = snapshot.child("Kuis3").getValue(String.class);
                String Kuis4 = snapshot.child("Kuis4").getValue(String.class);
                String Kuis5 = snapshot.child("Kuis5").getValue(String.class);
                String Kuis6 = snapshot.child("Kuis6").getValue(String.class);


                kuis1.setText(Kuis1);
                kuis2.setText(Kuis2);
                kuis3.setText(Kuis3);
                kuis4.setText(Kuis4);
                kuis5.setText(Kuis5);
                kuis6.setText(Kuis6);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Skor.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Skor.this, Dashboard.class);
        startActivity(i);
    }
}