package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.room.database.AppDatabase;
import com.example.room.database.entitas.User;

public class TambahActivity extends AppCompatActivity {
    private EditText editName, editKapasitas;
    private Button btnSave;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        editName = findViewById(R.id.NamaRuang);
        editKapasitas = findViewById(R.id.Kapasitas);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        if (uid>0){
            isEdit = true;
            User user = database.userDao().get(uid);
            editKapasitas.setText(user.Kapasitas);
            editName.setText(user.NamaRuang);
        }else {
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.userDao().update(uid, editName.getText().toString(),editKapasitas.getText().toString());
                }else{
                    database.userDao().insertAll(editName.getText().toString(),editKapasitas.getText().toString());
                }
                finish();
            }
        });
    }
}