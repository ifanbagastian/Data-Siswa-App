package com.ifanbf.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSimpan, btnTampil;
    EditText nis, nama, notel, email, alamat;
    String sNama;
    Long sNis;
    Long sNotel;
    String sEmail;
    String sAlamat;
    Realm realm;
    RealmHelper realmHelper;
    SiswaModel siswaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTampil = findViewById(R.id.btnTampil);
        btnSimpan = findViewById(R.id.btnSimpan);
        nis = findViewById(R.id.etNis);
        nama = findViewById(R.id.etNama);
        notel = findViewById(R.id.etNotel);
        email = findViewById(R.id.etEmail);
        alamat = findViewById(R.id.etAlamat);
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            sNis = Long.parseLong(nis.getText().toString());
            sNama = nama.getText().toString();
            sNotel = Long.parseLong(notel.getText().toString());
            sEmail = email.getText().toString();
            sAlamat = alamat.getText().toString();

            if (!sNis.equals("")&& !sNama.isEmpty() && !sNotel.equals("") && !sEmail.isEmpty() && !sAlamat.isEmpty()){
                siswaModel = new SiswaModel();
                siswaModel.setNis(sNis);
                siswaModel.setNama(sNama);
                siswaModel.setNotel(sNotel);
                siswaModel.setEmail(sEmail);
                siswaModel.setAlamat(sAlamat);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(siswaModel);

                Toast.makeText(MainActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                nis.setText("");
                nama.setText("");
                notel.setText("");
                email.setText("");
                alamat.setText("");
            }else {
                Toast.makeText(MainActivity.this, "Terdapat inputan kosong!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v == btnTampil){
               startActivity(new Intent(MainActivity.this, SiswaActivity.class));
               finish();
        }
    }
}
