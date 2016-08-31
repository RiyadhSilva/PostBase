package com.example.riyad.postbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by riyad on 30/08/2016.
 */
public class PostDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    //Nome do banco
    public static final String NOME_BANCO = "post-database.sqlite";
    //Versao do banco
    private static final int VERSAO_BANCO = 1;

    public PostDB(Context context){
        //context, nome do banco, factory, versao
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela post...");
        db.execSQL("create table if not exists post(_id integer primary key " +
                "autoincrement, autor text, data text, desc text, curtidas text);");
        Log.d(TAG, "Tabela criada com sucesso");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insere um novo post, ou atualiza se ja existe
    public long save(Post post){
        long id = post.id;
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("autor", post.autor);
            values.put("data", post.data);
            values.put("desc", post.desc);
            values.put("curtidas", post.curtidas);
            if(id != 0){
                String _id = String.valueOf(post.id);
                String[]whereArgs = new String[]{_id};
                //update post set values = ... where id=?
                int count = db.update("post", values, "_id=?", whereArgs);
                Log.d(TAG, "Post atualizado com sucesso");
                return count;
            }else{
                //insert into post values(...)
                id = db.insert("post", "", values);
                Log.d(TAG, "Post criado com sucesso");
                return id;
            }
        }finally {
            db.close();
        }
    }

    //Deleta o post
    public int delete(Post post){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //delete from post where _id=?
            int count = db.delete("post", "_id=?", new String[]{String.valueOf(post.id)});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        }finally {
            db.close();
        }
    }

    //Consulta a lista com todos os posts
    public List<Post> findAll(){
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
    public List<Post> findAllByAutor(String autor){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //select * from post where autor=?
            Cursor c = db.query("post", null, "autor='" + autor + "'", null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    //Le o cursor e cria a lista de posts
    private List<Post> toList(Cursor c){
        List<Post> posts = new ArrayList<Post>();
        if(c.moveToFirst()){
            do{
                Post post = new Post();
                posts.add(post);
                //Recupera os atributos de post
                post.id = c.getLong(c.getColumnIndex("_id"));
                post.autor = c.getString(c.getColumnIndex("autor"));
                post.data  = c.getString(c.getColumnIndex("data"));
                post.desc  = c.getString(c.getColumnIndex("desc"));
                post.curtidas = c.getString(c.getColumnIndex("curtidas"));
            } while(c.moveToNext());
        }

        return posts;
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
