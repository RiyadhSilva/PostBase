package com.example.riyad.postbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class PrioridadeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define o título da actionBar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_action_rss);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Prioridade");
        actionBar.setDisplayShowTitleEnabled(true);
        //Ativa a exibicao do icone na action bar
        actionBar.setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new PrioridadeAdapter(this));
        listView.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView<?> parent, View view, int idx, long id){
        //Objeto selecionado, que nesse casso era um array de strings
        String s = (String) parent.getAdapter().getItem(idx);
        Toast.makeText(this, "Texto selecionado: " + s + ", posição: " + idx, Toast.LENGTH_SHORT).show();
    }

    //ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da actionbar
        getMenuInflater().inflate(R.menu.menu_priority, menu);
        return true;
    }

    //ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_new){
            Intent intent = new Intent(this, NovaAtividade.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_search){
            Intent i = new Intent(this, PesquisarActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_feed){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_delete){
            AtividadeDB atividadeDB = new AtividadeDB(this);
            List<Atividade> atividades = atividadeDB.findAll();
            for (Atividade p:
                    atividades) {
                atividadeDB.delete(p);
            }
            toast("Todos os atividades foram deletados!");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_graph){
            Intent intent = new Intent(this, GraphActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
