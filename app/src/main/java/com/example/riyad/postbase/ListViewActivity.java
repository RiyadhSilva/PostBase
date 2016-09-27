package com.example.riyad.postbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    protected final String TAG = "livro";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //Listview
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new SimpleAdapter(this));
        listView.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> parent, View view, int idx, long id){
        //Objeto selecionado, que nesse casso era um array de strings
        CardView c = (CardView) parent.getAdapter().getItem(idx);
        Toast.makeText(this, "Texto selecionado: " + c.getId() + ", posição: " + idx, Toast.LENGTH_SHORT).show();
    }
}
