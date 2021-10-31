package Praktikum.Andre;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static Praktikum.Andre.MainActivity.mainContext;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewMahasiswaList extends AppCompatActivity implements MahasiswaAdapter.EditMahasiswa,MahasiswaAdapter.HapusMahasiswa{
    RecyclerView rvMahasiswa;
    MahasiswaAdapter mahasiswaAdapter;
    ArrayList<Mahasiswa> listMahasiswa;
    DatabaseHelper databaseHelper;
    FloatingActionButton add_mahasiswa;
    int Register_request = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mahasiswa_list);

        listMahasiswa = Mahasiswa_Data.getInstance().getDataMahasiswa();
        add_mahasiswa = findViewById(R.id.btn_addmahasiswa);

        rvMahasiswa = findViewById(R.id.rv_mahasiswa);
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        mahasiswaAdapter = new MahasiswaAdapter(this,listMahasiswa,this::edit,this::hapus);
        rvMahasiswa.setAdapter(mahasiswaAdapter);

        EditText searchMahasiswa = findViewById(R.id.search_input);
        searchMahasiswa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mahasiswaAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Intent intent = new Intent(ViewMahasiswaList.this,Register.class);
        add_mahasiswa.setOnClickListener(v -> {
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(intent,Register_request);
        });
    }


    public void updatelist() {
        listMahasiswa = Mahasiswa_Data.getInstance().getDataMahasiswa();
        mahasiswaAdapter = new MahasiswaAdapter(this,listMahasiswa,this::edit,this::hapus);
        rvMahasiswa.setAdapter(mahasiswaAdapter);
        rvMahasiswa.invalidate();
//        for (Mahasiswa m:listMahasiswa) {
//            Log.d("data", "setelah update: "+m.getNama());
//        }
//        Log.d("data", "setelah update: "+listMahasiswa.size());
    }

    @Override
    public void edit(int position) {
        Intent goto_editData = new Intent(this,Update_Mahasiswa.class);
        goto_editData.putExtra("edit_mahasiswa",listMahasiswa.get(position));
        update_launcher.launch(goto_editData);
//        Toast.makeText(this,"edit di "+position+"pls",Toast.LENGTH_SHORT).show();
    }


    ActivityResultLauncher<Intent> update_launcher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
            result -> updatelist());

    @Override
    public void hapus(int position) {
        databaseHelper = new DatabaseHelper(mainContext,null);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("delete from tb_mahasiswa where id_mahasiswa ="+listMahasiswa.get(position).getId_mahasiswa()+" ");
        MainActivity.mainContext.updateDatalist();
        updatelist();
//        Toast.makeText(this,"hapus di "+position+"pls",Toast.LENGTH_SHORT).show();
    }


}