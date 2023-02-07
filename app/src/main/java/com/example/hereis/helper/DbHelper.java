package com.example.hereis.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String NOME_DB = "DB_LISTA";
    static  final String TABELA_LISTA = "lista";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_LISTA +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "estado TEXT NOT NULL, " +
                "cidade TEXT," +
                "localidade TEXT NOT NULL);";
        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_LISTA + ";";
        try{
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB", "Sucesso ao atualizar app!");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar app" + e.getMessage());
        }
    }
}
