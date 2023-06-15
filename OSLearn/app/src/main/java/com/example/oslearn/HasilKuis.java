package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class HasilKuis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        final TextView benar = findViewById(R.id.totalBenar);
        final TextView score = findViewById(R.id.skor);
        final Button selesaiKuis = findViewById(R.id.selesai);

        final int getJawabanBenar = getIntent().getIntExtra("correct", 0);
        final int getJawabanSalah = getIntent().getIntExtra("incorrect", 0);
        final String jenisKuis = getIntent().getStringExtra("getKuis");

        benar.setText(String.valueOf(getJawabanBenar + "/5"));

        int hasilSkor = getJawabanBenar * 20;
        String ResultScore = String.valueOf(hasilSkor);
        score.setText(ResultScore);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://oslearn-fee4d-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reference.child(userID).child(jenisKuis).setValue(ResultScore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HasilKuis.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

        selesaiKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HasilKuis.this, Kuis.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(HasilKuis.this, Kuis.class);
        startActivity(i);
    }
}