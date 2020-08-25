package com.ifanbf.datasiswa;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SiswaModel extends RealmObject {
    @PrimaryKey
    private  Integer id;
    private  Long nis;
    private String nama;
    private Long notel;
    private  String email;
    private String alamat;

    public void  setId (Integer id){
        this.id = id;

    }

    public int getId(){
        return  id;

    }

    public  void  setNis(Long nis){
        this.nis = nis;
    }

    public long getNis(){
        return nis;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String  getNama(){

        return nama;
    }
    public  void  setNotel(Long notel){
        this.notel = notel;
    }

    public long getNotel(){

        return notel;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String  getEmail(){

        return email;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String  getAlamat(){

        return alamat;
    }
}
