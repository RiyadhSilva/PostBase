package com.example.riyad.postbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by riyad on 27/08/2016.
 */
public class CarroDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    //Nome do banco
    public static final String NOME_BANCO = "livro_android.sqlite";
    //Versao do banco
    private static final int VERSAO_BANCO = 1;

    public CarroDB(Context context){
        //context, nome do banco, factory, versao
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "Criando a Tabela carro...");
        db.execSQL("create table if not exists carro(_id integer primary key " +
                "autoincrement, nome text, desc text, url_foto text, url_info text," +
                "url_video text, latitude text, longitude text, tipo text);");
        Log.d(TAG, "Tabela criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Caso mude a versao do banco de dados, podemos executar um SQL aqui
    }

    //Insere um novo carro, ou atualiza se ja existe
    public long save(Carro carro){
        long id = carro.id;
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", carro.nome);
            values.put("desc", carro.desc);
            values.put("url_foto", carro.urlFoto);
            values.put("url_info", carro.urlInfo);
            values.put("url_video", carro.urlVideo);
            values.put("latitude", carro.latitude);
            values.put("longitude", carro.longitude);
            values.put("tipo", carro.tipo);
            if(id != 0){
                String _id = String.valueOf(carro.id);
                String[]whereArgs = new String[]{_id};
                //update carro set values = ... where id=?
                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            }else{
                //insert into carro values(...)
                id = db.insert("carro", "", values);
                return id;
            }
        }finally {
            db.close();
        }
    }

    
}
