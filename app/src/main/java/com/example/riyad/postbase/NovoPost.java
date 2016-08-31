package com.example.riyad.postbase;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.StringTokenizer;

public class NovoPost extends AppCompatActivity {
    private EditText autor;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_post);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Novo Post");
        actionBar.setDisplayHomeAsUpEnabled(true);

        autor = (EditText) findViewById(R.id.novo_post_et_1);
        desc  = (EditText) findViewById(R.id.novo_post_et_2);


    }

    public void postar(View view){
        Post post = new Post();
        post.autor = autor.getText().toString();
        //Tratamento da data
        Date data = new Date(System.currentTimeMillis());
        String data_formatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
        post.data = data_formatada;
        System.out.println("A data definda foi: " + post.data);
        post.desc = desc.getText().toString();
        //Cria um valor aleatório para o número de curtidas
        Random gerador = new Random();
        int numero = gerador.nextInt(100);
        post.curtidas = String.valueOf(numero);
        //Inicia conexao com o Banco
        PostDB postDB = new PostDB(this);
        //Salva o post criado no banco
        postDB.save(post);
        toast("Postado com sucesso!");
        //Chama a Activity principal
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
   }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da actionbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}