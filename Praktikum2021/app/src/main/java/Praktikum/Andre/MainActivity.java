package Praktikum.Andre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    ArrayList<Mahasiswa> newlist = new ArrayList<>();
    EditText username,pass;
    public static MainActivity mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.textUsername);
        pass = findViewById(R.id.textPassword);
        Button register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,Register.class);
            startActivity(intent);
        });

        mainContext = this;
        databaseHelper = new DatabaseHelper(mainContext,null);
        updateDatalist();
    }

    public void updateDatalist() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from tb_mahasiswa",null );
        if(cursor.getCount()>0){
            newlist.clear();
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToPosition(i);
                Mahasiswa newindex = new Mahasiswa(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(5),cursor.getString(3),cursor.getString(8));
                newlist.add(newindex);
                Log.d("list data", "Data count :"+cursor.getCount());
            }
            Mahasiswa_Data.getInstance().setDataMahasiswa(newlist);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        username.setText("");
        pass.setText("");
    }
}