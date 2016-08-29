package com.example.riyad.postbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public CardView cardView;
    public SeekBar seekBar1;
    public SeekBar seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarroDB carrodb = new CarroDB(this);
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

        List<Carro> carros = carrodb.findAll();

        for (Carro c: carros) {
            System.out.println("id: " + c.id +" Nome: " + c.nome);
        }

        cardView = (CardView) findViewById(R.id.cardView);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        if(seekBar == this.seekBar1){
            cardView.setCardElevation(progress); //elevacao
        }else if (seekBar == this.seekBar2){
            cardView.setRadius(progress); //Arrendodamento
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
