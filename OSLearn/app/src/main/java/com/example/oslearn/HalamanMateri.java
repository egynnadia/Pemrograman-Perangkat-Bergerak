package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class HalamanMateri extends AppCompatActivity {
    TextView judulMateri;
    String Materi, Judul;

    JustifyTextView isiMateri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_materi);


        isiMateri = findViewById(R.id.isi);
        judulMateri = findViewById(R.id.judul);

        Intent intent = getIntent();
        String getMateri = getIntent().getStringExtra("namaMateri");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://oslearn-fee4d-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("halMateri");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Materi = snapshot.child(getMateri).child("isi").getValue(String.class);
                Judul = snapshot.child(getMateri).child("judul").getValue(String.class);
                isiMateri.setText(Materi);
                judulMateri.setText(Judul);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}