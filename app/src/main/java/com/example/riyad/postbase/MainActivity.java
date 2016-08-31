package com.example.riyad.postbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import java.util.GregorianCalendar;
import java.util.List;

public  class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PostDB postDB = new PostDB(this);
/*
        Post post = new Post();
        post.autor = "Jefferson Pires";
        int ano = 2016;
        int mes = 10;
        int dia = 3;
        post.data  = new GregorianCalendar(ano, mes, dia);
        post.desc="Oh glória, ganhei na raspadinha... Agora vou atrás de um salgado...";
        postDB.save(post);
*/

       List<Post> posts = postDB.findAll();

/*       for (Post p: posts) {
            System.out.println("id: " + p.id +" Autor: " + p.autor +" Desc: " + p.desc);

        }
*/
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
        System.out.println("Quantidade de posts: " + posts.size());
    }


}
