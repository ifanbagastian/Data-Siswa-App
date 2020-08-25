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

public class DetailActivity extends AppCompatActivity implements
        View.OnClickListener {
    EditText etNis, etNama, etNotel, etEmail, etAlamat;
    String nis, nama, notel, email, alamat;
    Integer id;
    Button btn_update, btn_hapus, btn_kembali;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
// Set up
        Realm.init(this);
        RealmConfiguration configuration = new

                RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
// Inisialisasi
        etNis = findViewById(R.id.etNis);
        etNama = findViewById(R.id.etNama);
        etNotel = findViewById(R.id.etNotel);
        etEmail = findViewById(R.id.etEmail);
        etAlamat = findViewById(R.id.etAlamat);


        btn_update = findViewById(R.id.btnUpdate);
        btn_hapus = findViewById(R.id.btnHapus);
        btn_kembali = findViewById(R.id.btnKembali);


        id = Integer.parseInt(getIntent().getStringExtra("id"));
        nis = getIntent().getStringExtra("nis");
        nama = getIntent().getStringExtra("nama");
        notel = getIntent().getStringExtra("notel");
        email = getIntent().getStringExtra("email");
        alamat = getIntent().getStringExtra("alamat");

        etNis.setText(nis);
        etNama.setText(nama);
        etNotel.setText(notel);
        etEmail.setText(email);
        etAlamat.setText(alamat);

        btn_kembali.setOnClickListener(this);
        btn_hapus.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_update) {
            realmHelper.update(id, Long.parseLong(etNis.getText().toString()), etNama.getText().toString(),
            Long.parseLong(etNotel.getText().toString()),
            etEmail.getText().toString(),
            etAlamat.getText().toString());

            Toast.makeText(DetailActivity.this, "Update Success",
                    Toast.LENGTH_SHORT).show();
            etNis.setText("");
            etNama.setText("");
            etNotel.setText("");
            etEmail.setText("");
            etAlamat.setText("");
            finish();

        } else if (v == btn_hapus) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success",
                    Toast.LENGTH_SHORT).show();
            finish();
        } else if (v == btn_kembali) {
            startActivity(new Intent(DetailActivity.this,
                    SiswaActivity.class));
            finish();
        }
    }
}
