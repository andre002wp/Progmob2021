package Praktikum.Andre;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Mahasiswa implements Parcelable{
    private int id_mahasiswa;
    private String nama;
    private String alamat;
    private String hobi;
    private int umur;
    private String jurusan;
    private String username;
    private String password;
    private String ketertarikan;
    private Boolean status = false;

    protected Mahasiswa(Parcel in) {
        id_mahasiswa = in.readInt();
        nama = in.readString();
        alamat = in.readString();
        hobi = in.readString();
        umur = in.readInt();
        jurusan = in.readString();
        username = in.readString();
        password = in.readString();
        ketertarikan = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    public int getId_mahasiswa(){return id_mahasiswa;}
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKetertarikan() {
        return ketertarikan;
    }

    public void setKetertarikan(String ketertarikan) {
        this.ketertarikan = ketertarikan;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Mahasiswa(int id,String nama, String alamat, String jurusan,String hobi,String ketertarikan){
        this.id_mahasiswa = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jurusan = jurusan;
        this.hobi = hobi;
        this.umur = 20;
        this.jurusan = jurusan;
        this.username = nama;
        this.password = "1234";
        this.ketertarikan=ketertarikan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_mahasiswa);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(hobi);
        dest.writeInt(umur);
        dest.writeString(jurusan);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(ketertarikan);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
    }

//    public Mahasiswa(String nama, String alamat, String jurusan, String username, String password){
//        this.nama = nama;
//        this.alamat = alamat;
//        this.jurusan = jurusan;
//        this.username = username;
//        this.password = password;
//    }
}

