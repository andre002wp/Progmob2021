package Praktikum.Andre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterPreview extends AppCompatActivity {
    Mahasiswa maba;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_preview);
        TextView txt_nama,txt_alamat,txt_prodi,txt_hobi,txt_ketertarikan;

        databaseHelper = new DatabaseHelper(this,null);

        txt_nama = findViewById(R.id.txt_nama);
        txt_alamat = findViewById(R.id.txtAlamat);
        txt_prodi = findViewById(R.id.txtProdi);
        txt_hobi = findViewById(R.id.txtHobi);
        txt_ketertarikan = findViewById(R.id.txt_ketertarikan);

        try {
            Intent getmaba = getIntent();
            maba = getmaba.getParcelableExtra("maba");
            txt_nama.setText(maba.getNama());
            txt_alamat.setText(maba.getAlamat());
            txt_prodi.setText(maba.getJurusan());
            txt_hobi.setText(maba.getHobi());
            txt_ketertarikan.setText(maba.getKetertarikan());
        }catch (Exception e){
            txt_nama.setText("maba.getNama()");
            txt_alamat.setText("maba.getAlamat()");
            txt_prodi.setText("maba.getJurusan()");
            txt_hobi.setText("maba.getHobi()");
            txt_ketertarikan.setText("maba.getKetertarikan()");
        }


        Button btnViewList = findViewById(R.id.btnViewList);
        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seelist = new Intent(getApplicationContext(),ViewMahasiswaList.class);
                startActivity(seelist);
            }
        });

        Button btnSimpanData = findViewById(R.id.btnSimpan);
        btnSimpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Simpan Clicked",Toast.LENGTH_SHORT).show();
                try {
////                    to static
//                    ArrayList<Mahasiswa> mahasiswalist= Mahasiswa_Data.getInstance().getDataMahasiswa();
//                    Mahasiswa daftar = new Mahasiswa(mahasiswalist.size(),maba.getNama(),maba.getAlamat(),maba.getJurusan(),maba.getHobi(),maba.getKetertarikan());
//                    Mahasiswa_Data.getInstance().add_mahasiswa(daftar);
//                    to database
                    int mahasiswa_id = Mahasiswa_Data.getInstance().getMaxid();
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("id_mahasiswa",mahasiswa_id+1);
                    values.put("nama",maba.getNama());
                    values.put("alamat",maba.getAlamat());
                    values.put("jurusan",maba.getJurusan());
                    values.put("hobi",maba.getHobi());
                    values.put("umur",maba.getNama());
                    values.put("ketertarikan",maba.getKetertarikan());
                    values.put("status","0");
                    db.insert("tb_mahasiswa",null,values);
                    MainActivity.mainContext.updateDatalist();
                    db.close();
                    Toast.makeText(getApplicationContext(),"Data telah Ditambahkan",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.d("data", "error Exception: "+e);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            //set title dialog
            alertDialogBuilder.setTitle("Yakin ingin keluar");

            //set message
            alertDialogBuilder
                    .setMessage("Klik Ya untuk keluar")
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            //disini ??
                            Toast.makeText(getApplicationContext(),"Selamat Tinggal !!",Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        }
                    }).setNeutralButton("New Mahasiswa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            // make alert
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show alert dialog
            alertDialog.show();
    }
}