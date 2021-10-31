package Praktikum.Andre;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
    CheckBox checkHobi1,checkHobi2,checkHobi3,checkHobi4,checkHobi5,checkHobi6;
    RadioButton radioTI,radioTE, radioTA, radioTS, radioTM, radioTIN;
    private ArrayList<String> hobi = new ArrayList<>();
    private String prodiGroupchecked = null;
    EditText textNama,textAlamat;
    TextView ketertarikan,textProdi,textId;
    private Boolean created = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textNama = findViewById(R.id.textNama);
        textAlamat = findViewById(R.id.textAlamat);
        textProdi = findViewById(R.id.textProdi);
        textId = findViewById(R.id.textId);

        radioTI = findViewById(R.id.radioProdiTI);
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

        radioTE = findViewById(R.id.radioProdiTE);
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
        radioTA = findViewById(R.id.radioProdiTA);
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
        radioTS = findViewById(R.id.radioProdiTS);
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
        radioTM = findViewById(R.id.radioProdiTM);
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
        radioTIN = findViewById(R.id.radioProdiTIN);
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

        checkHobi1 = findViewById(R.id.checkBoxTenggelam);
        checkHobi2 = findViewById(R.id.checkBoxBerenang);
        checkHobi3 = findViewById(R.id.checkBoxTidur);
        checkHobi4 = findViewById(R.id.checkBoxBacabuku);
        checkHobi5 = findViewById(R.id.checkBoxMainGame);
        checkHobi6 = findViewById(R.id.checkBoxYoutube);

        ketertarikan = findViewById(R.id.ketertarikanProdi);
        SeekBar seekBarProdi = findViewById(R.id.seekBarProdi);
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

        Button alert = findViewById(R.id.btnAlert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateHobi();
                ShowPreview(textNama.getText().toString(),textAlamat.getText().toString(),prodiGroupchecked,hobi,ketertarikan.getText().toString());
            }
        });

        Button kirim = findViewById(R.id.btnKirim);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateHobi();
                String _temphobi = "";
                for (String item:hobi) {
                    _temphobi += item;
                    _temphobi += ", ";
//            Log.d("_tmphobi string", "item :"+_temphobi);
                }
                int _check_empty = updateEmptyField();
                if(_check_empty==1){
                    Mahasiswa maba = new Mahasiswa(-1,textNama.getText().toString(),textAlamat.getText().toString(),prodiGroupchecked,_temphobi,ketertarikan.getText().toString());
                    Intent intent = new Intent(Register.this,RegisterPreview.class);
                    intent.putExtra("maba",maba);
                    startActivity(intent);
                }
            }
        });
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
        if(checkHobi1.isChecked()){
            _tmphobi.add("Tenggelam");
        }
        if(checkHobi2.isChecked()){
            _tmphobi.add("Berenang");
        }
        if(checkHobi3.isChecked()){
            _tmphobi.add("Tidur");
        }
        if(checkHobi4.isChecked()){
            _tmphobi.add("Baca Buku");
        }
        if(checkHobi5.isChecked()){
            _tmphobi.add("Main Game");
        }
        if(checkHobi6.isChecked()){
            _tmphobi.add("Youtube");
        }
        hobi = _tmphobi;
    }

    protected void ShowPreview(String nama,String alamat,String prodi,ArrayList<String> hobi,String ketertarikan){
        TextView txt_nama,txt_alamat,txt_prodi,txt_hobi,txt_ketertarikan;
        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.alert_register, null);
        txt_nama = alertlayout.findViewById(R.id.txt_nama);
        txt_nama.setText(nama);
        txt_alamat = alertlayout.findViewById(R.id.txtAlamat);
        txt_alamat.setText(alamat);
        txt_prodi = alertlayout.findViewById(R.id.txtProdi);
        txt_prodi.setText(prodi);
        txt_hobi = alertlayout.findViewById(R.id.txtHobi);
        String _tempprodi = "";
        for (String item:hobi) {
            _tempprodi += item;
            _tempprodi += ", ";
//            Log.d("_tmphobi string", "item :"+_tempprodi);
        }
        txt_hobi.setText(_tempprodi);
        txt_ketertarikan = alertlayout.findViewById(R.id.txt_ketertarikan);
        txt_ketertarikan.setText(ketertarikan);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(alertlayout);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1000, 1000);
    }

    protected int updateEmptyField(){
        int _emptyfield_exist = 0;
        if(textNama.getText().length()<1){
            textNama.setError( "field nama is required!" );
            _emptyfield_exist+=1;
        }
        if(textAlamat.getText().length()<1){
            textAlamat.setError( "field alamat is required!" );
            _emptyfield_exist+=1;
        }
        if(prodiGroupchecked == null){
            textProdi.setError( "check one of the available prodi!" );
            _emptyfield_exist+=1;
        }else{
            textProdi.setError(null);
        }
        if(hobi.size()<1){
            textId.setError( "at least 1 value must be checked!" );
            _emptyfield_exist+=1;
        }
        else {
            textId.setError(null);
        }
        if(_emptyfield_exist<1){
            return 1;
        }
        else{
            Toast.makeText(getApplicationContext(),"Mohon isi Semua Field",Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        created = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(created==true){
            updateEmptyField();
        }
    }
}