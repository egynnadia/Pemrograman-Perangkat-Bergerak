package com.example.oslearn;

public class ListSoal {

    private String soal, op1, op2, op3, op4, jawaban;
    private String jawabanTerpilih;

    public ListSoal(String soal, String op1, String op2, String op3, String op4, String jawaban, String jawabanTerpilih) {
        this.soal = soal;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.jawaban = jawaban;
        this.jawabanTerpilih = jawabanTerpilih;
    }

    public String getSoal() {
        return soal;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getJawaban() {
        return jawaban;
    }

    public String getJawabanTerpilih() {
        return jawabanTerpilih;
    }

    public void setJawabanTerpilih(String jawabanTerpilih) {
        this.jawabanTerpilih = jawabanTerpilih;
    }

    public void add(ListSoal soalList) {
    }
}
