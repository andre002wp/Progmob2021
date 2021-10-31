package Praktikum.Andre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PraktikumProgmobAndre.db";

    public DatabaseHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //    private static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
//                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
//                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
//                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";
//
//    private static final String SQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase db) {
//      0->id, 1-> nama, 2-> alamat,3->hobi
//      4-umur, 5->jurusan, 6-> username
//      7->password, 8->ketertarikan,9->status
        String create_table_query = "create table tb_mahasiswa(id_mahasiswa integer primary key,nama string null,alamat string null, hobi string null,umur int null,jurusan string null, username string null,password string null,ketertarikan string null, status string null)";
        Log.d("Sql table ", "create table mahasiwa"+create_table_query);
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
