package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String userid;
//    Button button;
    ImageView logout;
    TextView userName;
    FirebaseUser user;

    ImageView btnMateri, btnOverview, btnKuis, btnSkor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);

        userName = findViewById(R.id.user_details);
        logout = findViewById(R.id.logout);
        btnMateri = findViewById(R.id.btn_materi);
        btnKuis = findViewById(R.id.btn_kuis);
        btnSkor = findViewById(R.id.btn_skor);
//        button = findViewById(R.id.logout);
//        btnOverview = findViewById(R.id.btn_overview);


        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://oslearn-fee4d-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = database.getReference("users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if(userprofile != null){
                    String username = userprofile.username;

                    userName.setText(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

//        username.setText(user.getDisplayName());



        btnMateri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intentMateri = new Intent(getApplicationContext(), Materi.class);
                startActivity(intentMateri);
                finish();
            }
        });

//        btnOverview.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                Intent intentOverview = new Intent(getApplicationContext(), Overview.class);
//                startActivity(intentOverview);
//                finish();
//            }
//        });

        btnKuis.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intentKuis = new Intent(getApplicationContext(), Kuis.class);
                startActivity(intentKuis);
                finish();
            }
        });

        btnSkor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intentSkor = new Intent(getApplicationContext(), Skor.class);
                startActivity(intentSkor);
                finish();
            }
        });


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}