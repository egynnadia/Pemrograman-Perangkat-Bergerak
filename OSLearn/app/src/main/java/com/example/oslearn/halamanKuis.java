package com.example.oslearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class halamanKuis extends AppCompatActivity {

    private TextView nomor;
    private TextView soal;
    private Button opsi1, opsi2, opsi3, opsi4, backbtn, nextbtn;
    private Timer timerKuis;
    private int totalTimerinMins = 1;
    private int detik = 0;
    private List<ListSoal> SoalList = new ArrayList<>();
    private int sedangBerlangsung = 0;
    private String opsiTerpilih = "";
    String getKuisTerpilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_kuis);

        final ImageView keluarbtn = findViewById(R.id.keluar);
        final TextView tvKuis = findViewById(R.id.namaKuis);
        getKuisTerpilih = getIntent().getStringExtra("KuisTerpilih");
        final TextView timer = findViewById(R.id.timer);

        nomor = findViewById(R.id.txtNomorSoal);
        soal = findViewById(R.id.txtSoal);
        opsi1 = findViewById(R.id.pil1);
        opsi2 = findViewById(R.id.pil2);
        opsi3 = findViewById(R.id.pil3);
        opsi4 = findViewById(R.id.pil4);
        backbtn = findViewById(R.id.back);
        nextbtn = findViewById(R.id.next);

        tvKuis.setText(getKuisTerpilih);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://oslearn-fee4d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        ProgressDialog progressDialog = new ProgressDialog(halamanKuis.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalTimerinMins = Integer.parseInt(snapshot.child("time").getValue(String.class));

                for (DataSnapshot dataSnapshot: snapshot.child(getKuisTerpilih).getChildren()){
                    final String getSoal = dataSnapshot.child("soal").getValue(String.class);
                    final String getOpsi1 = dataSnapshot.child("opsi1").getValue(String.class);
                    final String getOpsi2 = dataSnapshot.child("opsi2").getValue(String.class);
                    final String getOpsi3 = dataSnapshot.child("opsi3").getValue(String.class);
                    final String getOpsi4 = dataSnapshot.child("opsi4").getValue(String.class);
                    final String getJawaban = dataSnapshot.child("jawaban").getValue(String.class);

                    ListSoal SoalList1 = new ListSoal(getSoal, getOpsi1, getOpsi2, getOpsi3, getOpsi4, getJawaban, "");
                    SoalList.add(SoalList1);
                }
                progressDialog.hide();

                nomor.setText((sedangBerlangsung+1)+"/"+ SoalList.size());
                soal.setText(SoalList.get(sedangBerlangsung).getSoal());
                opsi1.setText(SoalList.get(sedangBerlangsung).getOp1());
                opsi2.setText(SoalList.get(sedangBerlangsung).getOp2());
                opsi3.setText(SoalList.get(sedangBerlangsung).getOp3());
                opsi4.setText(SoalList.get(sedangBerlangsung).getOp4());

                startTimer(timer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        opsi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opsiTerpilih.isEmpty()){
                    opsiTerpilih = opsi1.getText().toString();
                    SoalList.get(sedangBerlangsung).setJawabanTerpilih(opsiTerpilih);
                    revealAnswer();
                }
            }
        });

        opsi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opsiTerpilih.isEmpty()){
                    opsiTerpilih = opsi2.getText().toString();
                    revealAnswer();
                    SoalList.get(sedangBerlangsung).setJawabanTerpilih(opsiTerpilih);
                }
            }
        });

        opsi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opsiTerpilih.isEmpty()){
                    opsiTerpilih = opsi3.getText().toString();
//                    revealAnswer();
                    SoalList.get(sedangBerlangsung).setJawabanTerpilih(opsiTerpilih);
                }
            }
        });

        opsi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opsiTerpilih.isEmpty()){
                    opsiTerpilih = opsi4.getText().toString();
                    revealAnswer();
                    SoalList.get(sedangBerlangsung).setJawabanTerpilih(opsiTerpilih);
                }
            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opsiTerpilih.isEmpty()){
                    Toast.makeText(halamanKuis.this, "Pilih salah satu jawaban", Toast.LENGTH_SHORT).show();
                }
                else {
                    opsi1.setTextColor(Color.parseColor("#FFFFFF"));
                    opsi2.setTextColor(Color.parseColor("#FFFFFF"));
                    opsi3.setTextColor(Color.parseColor("#FFFFFF"));
                    opsi4.setTextColor(Color.parseColor("#FFFFFF"));
                    gantiSoal();
                }
            }
        });



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerKuis.purge();
                timerKuis.cancel();

                Intent i=new Intent(halamanKuis.this, Kuis.class);
                startActivity(i);
                finish();
            }
        });

        }

        private void gantiSoal(){
            sedangBerlangsung++;
            if((sedangBerlangsung+1) == SoalList.size()){
                nextbtn.setText("Finish");
            }
            if(sedangBerlangsung < SoalList.size()){
                opsiTerpilih = "";

                nomor.setText((sedangBerlangsung+1)+"/"+ SoalList.size());
                soal.setText(SoalList.get(sedangBerlangsung).getSoal());
                opsi1.setText(SoalList.get(sedangBerlangsung).getOp1());
                opsi2.setText(SoalList.get(sedangBerlangsung).getOp2());
                opsi3.setText(SoalList.get(sedangBerlangsung).getOp3());
                opsi4.setText(SoalList.get(sedangBerlangsung).getOp4());
            }

            else{
                Intent i = new Intent(halamanKuis.this, HasilKuis.class);
                i.putExtra("correct", getJawabanBenar());
                i.putExtra("incorrect", getJawabanSalah());
                i.putExtra("getKuis", getKuisTerpilih);
                startActivity(i);

                finish();
            }
        }


        private void startTimer(TextView timerTextView){
            timerKuis = new Timer();

            timerKuis.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(detik == 0){
                        totalTimerinMins--;
                        detik = 60;
                    } else if (detik == 0 && totalTimerinMins == 0) {
                        timerKuis.purge();
                        timerKuis.cancel();

                        Toast.makeText(halamanKuis.this, "Waktu Anda Habis", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(halamanKuis.this, HasilKuis.class);
                        intent.putExtra("benar", getJawabanBenar());
                        intent.putExtra("salah", getJawabanSalah());
                        intent.putExtra("getKuis", getKuisTerpilih);
                        startActivity(intent);

                        finish();
                    }
                    else{
                        detik--;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String Menitfinal = String.valueOf(totalTimerinMins);
                            String Detikfinal = String.valueOf(detik);

                            if(Menitfinal.length() == 1){
                                Menitfinal = "0"+Menitfinal;
                            }
                            if(Detikfinal.length() == 1){
                                Detikfinal = "0"+Detikfinal;
                            }

                            timerTextView.setText(Menitfinal + ":"+Detikfinal);
                        }
                    });

                }
            }, 1000, 1000);
        }

        private int getJawabanBenar(){
            int jawabanBenar = 0;

            for (int i=0; i< SoalList.size(); i++){
                final String getJawabanTerpilih = SoalList.get(i).getJawabanTerpilih();
                final String getJawaban = SoalList.get(i).getJawaban();

                if(getJawabanTerpilih.equals(getJawaban)){
                    jawabanBenar++;
                }

            }

            return jawabanBenar;
        }

        private int getJawabanSalah(){
            int jawabanBenar = 0;

            for (int i=0; i< SoalList.size(); i++){
                final String getJawabanTerpilih = SoalList.get(i).getJawabanTerpilih();
                final String getJawaban = SoalList.get(i).getJawaban();

                if(!getJawabanTerpilih.equals(getJawaban)){
                    jawabanBenar++;
                }

            }

            return jawabanBenar;
        }

    public void onBackPressed() {
        super.onBackPressed();

        timerKuis.purge();
        timerKuis.cancel();

        Intent i=new Intent(halamanKuis.this, Kuis.class);
        startActivity(i);
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = SoalList.get(sedangBerlangsung).getJawaban();

        if(opsi1.getText().toString().equals(getAnswer)){
            opsi1.setTextColor(Color.parseColor("#41A600"));
        } else if(opsi2.getText().toString().equals(getAnswer)){
            opsi2.setTextColor(Color.parseColor("#41A600"));
        } else if(opsi3.getText().toString().equals(getAnswer)){
            opsi3.setTextColor(Color.parseColor("#41A600"));
        } else if(opsi4.getText().toString().equals(getAnswer)){
            opsi4.setTextColor(Color.parseColor("#41A600"));
        }

    }

    }
