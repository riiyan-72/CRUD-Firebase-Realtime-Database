package com.example.crudfirebase;

public class ModelMahasiswa {
    private String nama;
    private String matkul;
    private String key;

    public ModelMahasiswa(){

    }

    public ModelMahasiswa(String nama, String matkul) {
        this.nama = nama;
        this.matkul = matkul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
