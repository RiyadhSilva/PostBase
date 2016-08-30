package com.example.riyad.postbase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by riyad on 29/08/2016.
 */
public class SimpleAdapter extends BaseAdapter {
    private String[] planetas = new String[]{"Mercúrio", "Vênus", "Terra", "Marte", "Júpiter", "Saturno",
    "Urano", "Netuno", "Plutão"};
    private Context context;
    public SimpleAdapter(Context context){
        super();
        this.context = context; // O contexto é necessário para criar a view
    }
    @Override
    public int getCount(){
        return planetas.length; //Retorna a quantidade de items no adapter
    }
    @Override
    public Object getItem(int position){
        return planetas[position]; //Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(int position, View convertView, ViewGroup parent){
        String planeta = planetas[position];
        TextView t = new TextView(context);
        float dip = 50;
        float densidade = context.getResources().getDisplayMetrics().density; //Densidade da tela

        int px = (int)(dip * densidade + 0.5f);
        t.setHeight(px);
        t.setText(planeta);
        return t;
    }
}
