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

/*        Post post = new Post();
        post.autor = "Riyadh Levi";
        int ano = 2015;
        int mes = 9;
        int dia = 25;
        post.data  = new GregorianCalendar(ano, mes, dia);
        post.desc="Eba, logo, logo eh outubro xD";
        postDB.save(post);
*/

/*        List<Post> posts = postDB.findAllByAutor("Felipe Fernandes");

        for (Post p: posts) {
            System.out.println("id: " + p.id +" Autor: " + p.autor +" Desc: " + p.desc);

        }
*/
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }


}
