package com.example.riyad.postbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riyad on 30/08/2016.
 */
public class AtividadeDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    //Nome do banco
    public static final String NOME_BANCO = "post-data-base.sqlite";
    //Versao do banco
    private static final int VERSAO_BANCO = 1;

    public AtividadeDB(Context context){
        //context, nome do banco, factory, versao
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela post...");
        db.execSQL("create table if not exists post(_id integer primary key " +
                "autoincrement, autor text, data text, desc text, curtidas text, " +
                "prioridade text);");
        Log.d(TAG, "Tabela criada com sucesso");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insere um novo atividade, ou atualiza se ja existe
    public long save(Atividade atividade){
        long id = atividade.id;
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("autor", atividade.autor);
            values.put("data", atividade.data);
            values.put("desc", atividade.desc);
            values.put("curtidas", atividade.curtidas);
            values.put("prioridade", atividade.prioridade);
            if(id != 0){
                String _id = String.valueOf(atividade.id);
                String[]whereArgs = new String[]{_id};
                //update atividade set values = ... where id=?
                int count = db.update("atividade", values, "_id=?", whereArgs);
                Log.d(TAG, "Atividade atualizado com sucesso");
                return count;
            }else{
                //insert into atividade values(...)
                id = db.insert("atividade", "", values);
                Log.d(TAG, "Atividade criado com sucesso");
                return id;
            }
        }finally {
            db.close();
        }
    }

    //Deleta o atividade
    public int delete(Atividade atividade){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //delete from atividade where _id=?
            int count = db.delete("atividade", "_id=?", new String[]{String.valueOf(atividade.id)});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        }finally {
            db.close();
        }
    }

    //Consulta a lista com todos os atividades
    public List<Atividade> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //Select * from post
            Cursor c = db.query("post", null, null, null, null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    //Consulta o post pelo autor
    public List<Atividade> findAllByAutor(String autor){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //select * from post where autor=?
            Cursor c = db.query("post", null, "autor='" + autor + "'", null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    //Le o cursor e cria a lista de atividades
    private List<Atividade> toList(Cursor c){
        List<Atividade> atividades = new ArrayList<Atividade>();
        if(c.moveToFirst()){
            do{
                Atividade atividade = new Atividade();
                atividades.add(atividade);
                //Recupera os atributos de atividade
                atividade.id = c.getLong(c.getColumnIndex("_id"));
                atividade.autor = c.getString(c.getColumnIndex("autor"));
                atividade.data  = c.getString(c.getColumnIndex("data"));
                atividade.desc  = c.getString(c.getColumnIndex("desc"));
                atividade.curtidas = c.getString(c.getColumnIndex("curtidas"));
                atividade.prioridade = c.getString(c.getColumnIndex("prioridade"));
            } while(c.moveToNext());
        }

        return atividades;
    }

    //Executa um SQL
    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }
}
