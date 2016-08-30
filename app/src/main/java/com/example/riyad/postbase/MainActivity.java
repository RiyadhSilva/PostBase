package com.example.riyad.postbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import java.util.List;

public  class MainActivity extends AppCompatActivity{

    public CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CarroDB carrodb = new CarroDB(this);
/*
        Carro carro = new Carro();
        carro.tipo="sedan";
        carro.nome="fiat galaxy";
        carro.desc = "Completao e economico";
        carro.urlFoto = "X";
        carro.urlInfo = "Y";
        carro.urlVideo =  "Z";
        carro.latitude="1";
        carro.longitude="1";

        carrodb.save(carro);
*/

/*        List<Carro> carros = carrodb.findAll();

        for (Carro c: carros) {
            System.out.println("id: " + c.id +" Nome: " + c.nome);
        }
*/
        cardView = (CardView) findViewById(R.id.cardView);
        cardView.setCardElevation(0);
        cardView.setRadius(25);

        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }


}
