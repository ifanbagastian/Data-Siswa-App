package com.ifanbf.datasiswa;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    //untuk menyimpan data
    public void save(final  SiswaModel siswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null)
                {
                    Log.e("Created", "Database sudah terbuat");
                    Number currention = realm.where(SiswaModel.class).max("id");
                    int nextId;
                    if (currention == null){
                        nextId = 1;
                    }
                    else {
                        nextId =currention.intValue() + 1;
                    }

                    siswaModel.setId(nextId);
                    SiswaModel model = realm.copyToRealm(siswaModel);
                }
                else {
                    Log.e("Error", "execute: Database tidak ada");

                }
            }
        });
    }
    public List<SiswaModel> getAllSiswa(){
        RealmResults<SiswaModel> results = realm.where(SiswaModel.class).findAll();
        return  results;
    }

    public  void update (final  Integer id, final  Long nis, final String nama, final  Long notel, final String email, final String alamat){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SiswaModel model = realm.where(SiswaModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNis(nis);
                model.setNama(nama);
                model.setNotel(notel);
                model.setEmail(email);
                model.setAlamat(alamat);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("Pesan", "onSucces: Update Sukses");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void  delete(Integer id){
        final  RealmResults<SiswaModel> model = realm.where(SiswaModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);

            }
        });
    }
}
