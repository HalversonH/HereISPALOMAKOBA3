package com.example.hereis.helper;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hereis.model.Lista;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO implements IListaDAO {
    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;

    public ListaDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        leitura = dbHelper.getReadableDatabase();
        escrita = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean salvar(Lista lista) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("estado", lista.getEstado());
        contentValues.put("cidade", lista.getCidade());
        contentValues.put("localidade", lista.getLocalidade());
        this.escrita.insert(DbHelper.TABELA_LISTA, null, contentValues);
        return true;
    }

    @Override
    public boolean atualizar(Lista lista) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("estado", lista.getEstado());
        contentValues.put("cidade", lista.getCidade());
        contentValues.put("localidade", lista.getLocalidade());
        String [] args = {String.valueOf(lista.getId())};
        this.escrita.update(DbHelper.TABELA_LISTA, contentValues, "id=?", args );
        return true;
    }

    @Override
    public boolean deletar(Lista lista) {
        String [] args = {String.valueOf(lista.getId())};
        this.escrita.delete(DbHelper.TABELA_LISTA, "id=?", args );
        return true;
    }

    @SuppressLint("Range")
    @Override
    public List<Lista> listar() {
        List<Lista> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_LISTA + ";";
        Cursor cursor = leitura.rawQuery(sql, null);

        while(cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));
            String localidade = cursor.getString(cursor.getColumnIndex("localidade"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            lista.add(new Lista(estado,cidade,localidade, id));
        }

        return lista;
    }
}
