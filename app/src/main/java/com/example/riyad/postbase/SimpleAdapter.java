package com.example.riyad.postbase;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by riyad on 29/08/2016.
 */
public class SimpleAdapter extends BaseAdapter {
    private String[] pessoas = new String[]{"By: Jefferson Pires", "By: Yanne Aciole", "By: Caroline" +
            " Fernanda", "By: Andressa Camila", "By: Felipe Fernandes", "By: Accyel Bertoldo", "By: " +
            "Kalliany Kellzer", "By: Firmo Lopes", "By: Samila medeiros"};
    private Context context;
    public SimpleAdapter(Context context){
        super();
        this.context = context; // O contexto é necessário para criar a view
    }
    @Override
    public int getCount(){
        return pessoas.length; //Retorna a quantidade de items no adapter
    }
    @Override
    public Object getItem(int position){
        return pessoas[position]; //Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(int position, View convertView, ViewGroup parent){
        String pessoa = pessoas[position];
        TextView t = new TextView(context);
        CardView c = new CardView(context);

        //Parte do TextView (Nome do dono do post)
        t.setText(pessoa);
        t.setGravity(Gravity.CENTER_HORIZONTAL);

        //Parte do TextView (Post)
        TextView p = new TextView(context);
        p.setGravity(Gravity.CENTER_VERTICAL);
        p.setText("Meu post...");
        //Parte do CardView
        int count = 0;
        c.setMinimumHeight(100);
        c.setMinimumHeight(280);
        c.addView(t, count);
        c.addView(p, count);
        count++;

        return c;
    }
}
