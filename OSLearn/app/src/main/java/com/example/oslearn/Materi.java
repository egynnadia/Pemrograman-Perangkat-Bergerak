package com.example.oslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Materi extends AppCompatActivity {

    ListView listMateri;
    String[] namaMateri = {"materi1","materi2","materi3","materi4","materi5","materi6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        listMateri = findViewById(R.id.listMateri);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<MateriData> arr = new ArrayList<>();

        arr.add(new MateriData("BAB 1: KOMPONEN SISTEM OPERASI"));
        arr.add(new MateriData("BAB 2: LAYANAN SISTEM OPERASI"));
        arr.add(new MateriData("BAB 3: SYSTEM CALL"));
        arr.add(new MateriData("BAB 4: SISTEM PROGRAM"));
        arr.add(new MateriData("BAB 5: STRUKTUR SISTEM OPERASI"));
        arr.add(new MateriData("BAB 6: MESIN VIRTUAL"));


        MateriAdapter adapter = new MateriAdapter(this, 0, arr);
        listMateri.setAdapter(adapter);

        listMateri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(), HalamanMateri.class);
                intent.putExtra("namaMateri",namaMateri[i]);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(Materi.this, Dashboard.class);
        startActivity(i);
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return namaMateri.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.activity_halaman_materi, null);
            return null;
        }
    }
}