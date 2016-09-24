package com.example.riyad.postbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PesquisarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private EditText autor;
    private TextView textView;
    private Button   bt_pesquisar;
    private ListView listView;
    public List<Atividade> pesquisa_atividade;

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
        AtividadeDB atividadeDB = new AtividadeDB(this);
        //Pega todos os atividades de um determinado autor
        this.pesquisa_atividade = atividadeDB.findAllByAutor(autor_nome);
        //Trata o resultado
        if(this.pesquisa_atividade.size() == 0){
            toast("Pesquisa sem resultados!");
        }else{

            toast("A pesquisa encontrou: " + pesquisa_atividade.size() + " resultados!");
            listView = (ListView) findViewById(R.id.pesquisar_listview);
            listView.setAdapter(new PesquisaAdapter(this, this.pesquisa_atividade));

        }

    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da actionbar
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);
        return true;
    }

}
