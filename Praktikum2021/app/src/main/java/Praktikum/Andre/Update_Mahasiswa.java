package Praktikum.Andre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static Praktikum.Andre.MainActivity.mainContext;
import static java.lang.Integer.parseInt;

public class Update_Mahasiswa extends AppCompatActivity {
    CheckBox checkTenggelam, checkBerenang, checkTidur, checkBacabuku, checkMainGame, checkYoutube;
    RadioButton radioTI,radioTE, radioTA, radioTS, radioTM, radioTIN;
    private ArrayList<String> hobi = new ArrayList<>();
    private String prodiGroupchecked = null;
    DatabaseHelper databaseHelper;
    Mahasiswa mahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);
        // declare semua diatas bcs tempatnya random klo ga and i need them to organize
        EditText textNama = findViewById(R.id.textNama);
        EditText textAlamat = findViewById(R.id.textAlamat);
        radioTI = findViewById(R.id.radioProdiTI);
        radioTE = findViewById(R.id.radioProdiTE);
        radioTA = findViewById(R.id.radioProdiTA);
        radioTS = findViewById(R.id.radioProdiTS);
        radioTM = findViewById(R.id.radioProdiTM);
        radioTIN = findViewById(R.id.radioProdiTIN);
        checkTenggelam = findViewById(R.id.checkBoxTenggelam);
        checkBerenang = findViewById(R.id.checkBoxBerenang);
        checkTidur = findViewById(R.id.checkBoxTidur);
        checkBacabuku = findViewById(R.id.checkBoxBacabuku);
        checkMainGame = findViewById(R.id.checkBoxMainGame);
        checkYoutube = findViewById(R.id.checkBoxYoutube);
        TextView ketertarikan = findViewById(R.id.ketertarikanProdi);
        SeekBar seekBarProdi = findViewById(R.id.seekBarProdi);
        Button btnback = findViewById(R.id.btnBack);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        Intent getmahasiswa = getIntent();
        mahasiswa = getmahasiswa.getParcelableExtra("edit_mahasiswa");

        //set nama yg ada
        textNama.setText(mahasiswa.getNama());
        //set alamat yg ada
        textAlamat.setText(mahasiswa.getAlamat());
        //set jurusan yg ada
//      todo new Register Radio
        RegisterRadioChecked(mahasiswa.getJurusan());
        //set hobi yg ada
//      todo new Register Hobi
        RegisterHobiChecked(mahasiswa.getHobi());
        //set ketertarikan yg ada
        ketertarikan.setText(mahasiswa.getKetertarikan());
        int num_tertarik = Integer.parseInt(mahasiswa.getKetertarikan().replace("%",""));
        seekBarProdi.setProgress(num_tertarik);

        radioTI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknologi Informasi";
            }
        });

        radioTE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknik Elektro";
            }
        });

        radioTA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknik Arsitektur";
            }
        });

        radioTS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknik Sipil";
            }
        });

        radioTM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknik Mesin";
            }
        });

        radioTIN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ClearRadioChecked();
                if(isChecked){
                    buttonView.setChecked(true);
                }
                prodiGroupchecked = "Teknik Industri";
            }
        });

        seekBarProdi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ketertarikan.setText(String.valueOf(progress)+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    UpdateHobi();
                    String _temphobi = "";
                    for (String item:hobi) {
                        _temphobi += item;
                        _temphobi += ", ";
//                    Log.d("_tmphobi string", "item :"+_temphobi);
                    }

                    databaseHelper = new DatabaseHelper(mainContext,null);
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("nama", textNama.getText().toString());
                    values.put("alamat", textAlamat.getText().toString());
                    values.put("jurusan",prodiGroupchecked);
                    values.put("hobi",_temphobi);
                    values.put("ketertarikan",ketertarikan.getText().toString());
                    values.put("status","1");
                    db.update("tb_mahasiswa",values,"id_mahasiswa = ?",new String[]{String.valueOf(mahasiswa.getId_mahasiswa())});
                    db.close();
                    Toast.makeText(getApplicationContext(),"Data telah Diupdate",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error Mohon isi semua field",Toast.LENGTH_SHORT).show();
                    Log.d("data update", "update :"+e);
                }
                MainActivity.mainContext.updateDatalist();
                finish();
            }
        });
    }

    //  new here
    protected void RegisterRadioChecked(String jurusan){
        if(jurusan.equals("Teknologi Informasi")){
            radioTI.setChecked(true);
        }
        else if (jurusan.equals("Teknik Elektro")){
            radioTE.setChecked(true);
        }
        else if(jurusan.equals("Teknik Arsitektur")){
            radioTA.setChecked(true);
        }
        else if(jurusan.equals("Teknik Sipil")){
            radioTS.setChecked(true);
        }
        else if(jurusan.equals("Teknik Mesin")){
            radioTM.setChecked(true);
        }
        else if(jurusan.equals("Teknik Industri")){
            radioTIN.setChecked(true);
        }
    }

    //  new here
    protected void RegisterHobiChecked(String hobbies){
        for (String hobi:hobbies.split(",")) {
            Log.d("Data", "hobi: "+hobi);
            if(hobi.trim().equals("Tenggelam")){
                checkTenggelam.setChecked(true);
            }
            if (hobi.trim().equals("Berenang")){
                checkBerenang.setChecked(true);
            }
            if(hobi.trim().equals("Tidur")){
                checkTidur.setChecked(true);
            }
            if(hobi.trim().equals("Baca Buku")){
                checkBacabuku.setChecked(true);
            }
            if(hobi.trim().equals("Main Game")){
                checkMainGame.setChecked(true);
            }
            if(hobi.trim().equals("Youtube")){
                checkYoutube.setChecked(true);
            }
        }
    }

    protected void ClearRadioChecked(){
        radioTI.setChecked(false);
        radioTA.setChecked(false);
        radioTE.setChecked(false);
        radioTM.setChecked(false);
        radioTS.setChecked(false);
        radioTIN.setChecked(false);
    }

    protected void UpdateHobi(){
        ArrayList<String> _tmphobi = new ArrayList<>();
        if(checkTenggelam.isChecked()){
            _tmphobi.add("Tenggelam");
        }
        if(checkBerenang.isChecked()){
            _tmphobi.add("Berenang");
        }
        if(checkTidur.isChecked()){
            _tmphobi.add("Tidur");
        }
        if(checkBacabuku.isChecked()){
            _tmphobi.add("Baca Buku");
        }
        if(checkMainGame.isChecked()){
            _tmphobi.add("Main Game");
        }
        if(checkYoutube.isChecked()){
            _tmphobi.add("Youtube");
        }
        hobi = _tmphobi;
    }

}