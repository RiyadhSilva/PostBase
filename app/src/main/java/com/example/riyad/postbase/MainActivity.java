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

public  class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    protected final String TAG = "livro";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Define o título da actionBar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_action_rss);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Time Line");
        actionBar.setDisplayShowTitleEnabled(true);
        //Ativa a exibicao do icone na action bar
        actionBar.setDisplayShowHomeEnabled(true);

        PostDB postDB = new PostDB(this);
        //Listview
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new SimpleAdapter(this));
        listView.setOnItemClickListener(this);


       List<Post> posts = postDB.findAll();

       for (Post p: posts) {
            System.out.println("id: " + p.id +" Autor: " + p.autor +" Desc: " + p.desc + " Data: " + p.data);

        }

//        Intent intent = new Intent(this, ListViewActivity.class);
//        startActivity(intent);
//        System.out.println("Quantidade de posts: " + posts.size());
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_new){
            Intent intent = new Intent(this, NovoPost.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_search){
            Intent i = new Intent(this, PesquisarActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_refresh){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_delete){
            PostDB postDB = new PostDB(this);
            List<Post> posts = postDB.findAll();
            for (Post p:
                 posts) {
                postDB.delete(p);
            }
            toast("Todos os posts foram deletados!");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
