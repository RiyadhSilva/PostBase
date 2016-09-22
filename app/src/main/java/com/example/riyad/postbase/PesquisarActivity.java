package com.example.riyad.postbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private EditText autor;
    private TextView textView;
    private Button   bt_pesquisar;
    private ListView listView;
    public List<Post> pesquisa_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        autor = (EditText) findViewById(R.id.pesquisar_et_1);
        textView = (TextView) findViewById(R.id.pesquisar_tv_1);
        bt_pesquisar = (Button) findViewById(R.id.bt_pesquisar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_action_search);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Pesquisar Atividade");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onItemClick(AdapterView<?> parent, View view, int idx, long id){
        Toast.makeText(this, "posição: " + idx, Toast.LENGTH_SHORT).show();
    }

    public void pesquisar(View view){
        String autor_nome = autor.getText().toString();
        //Inicia conexão com o Banco de Dados
        PostDB postDB = new PostDB(this);
        //Pega todos os posts de um determinado autor
        this.pesquisa_post = postDB.findAllByAutor(autor_nome);
        //Trata o resultado
        if(this.pesquisa_post.size() == 0){
            toast("Pesquisa sem resultados!");
        }else{

            toast("A pesquisa encontrou: " + pesquisa_post.size() + " resultados!");
            listView = (ListView) findViewById(R.id.pesquisar_listview);
            listView.setAdapter(new PesquisaAdapter(this, this.pesquisa_post));

        }

    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
