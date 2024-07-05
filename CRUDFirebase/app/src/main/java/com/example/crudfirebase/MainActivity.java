package com.example.crudfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton tambah;
    AdapterMahasiswa adapterMahasiswa;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelMahasiswa> listMahasiswa;
    RecyclerView tv_tampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tambah = findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
        tv_tampil = findViewById(R.id.tv_tampil);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tv_tampil.setLayoutManager(mLayout);
        tv_tampil.setItemAnimator(new DefaultItemAnimator());

        tampilData();
    }

    private void tampilData() {
        database.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMahasiswa = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    ModelMahasiswa mhs = item.getValue(ModelMahasiswa.class);
                    mhs.setKey(item.getKey());
                    listMahasiswa.add(mhs);
                }
                adapterMahasiswa = new AdapterMahasiswa(listMahasiswa, MainActivity.this);
                tv_tampil.setAdapter(adapterMahasiswa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}