package Praktikum.Andre;

import java.util.ArrayList;

public class Mahasiswa_Data {
    private ArrayList<Mahasiswa> dataMahasiswa;

    public Mahasiswa_Data() {
        dataMahasiswa = new ArrayList<>();
        dataMahasiswa.add(new Mahasiswa(1,"I","Jalan A","Teknologi Informasi","membaca","10%"));
        dataMahasiswa.add(new Mahasiswa(2,"Made","Jalan B","Teknologi Mesin","tenggelem","9%"));
        dataMahasiswa.add(new Mahasiswa(3,"Andre","Jalan C","Teknologi Arsitektur","Youtube","15%"));
        dataMahasiswa.add(new Mahasiswa(4,"Dwi","Jalan D","Teknologi Industri","membaca","3%"));
        dataMahasiswa.add(new Mahasiswa(5,"Winama","Jalan E","Teknologi Sipil","membaca","1%"));
    }

    public ArrayList<Mahasiswa> getDataMahasiswa() {
        return dataMahasiswa;
    }

    public void setDataMahasiswa(ArrayList<Mahasiswa> dataMahasiswa) {
        this.dataMahasiswa = dataMahasiswa;
    }

    public int getMaxid(){
        int max_id = 0;
        for (Mahasiswa ma:dataMahasiswa) {
            if (ma.getId_mahasiswa()>max_id){
                max_id = ma.getId_mahasiswa();
            }
        }
        return max_id;
    }

    public void add_mahasiswa(Mahasiswa maba) {
        dataMahasiswa.add(maba);
    }

    private static Mahasiswa_Data single_instance = null;
    public static Mahasiswa_Data getInstance() {
        if (single_instance == null)
            single_instance = new Mahasiswa_Data();

        return single_instance;
    }
}
