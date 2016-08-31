package com.example.riyad.postbase;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

    public List<Post> posts() {
        PostDB postDB = new PostDB(this.context);
        List<Post> posts = postDB.findAll();
        return posts;

    }

    @Override
    public int getCount(){

        return this.posts().size();//Retorna a quantidade de items no adapter
    }
    @Override
    public Object getItem(int position){
        return this.posts().get(position).autor;//Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(int position, View convertView, ViewGroup parent){
        TextView t = new TextView(context);
        CardView c = new CardView(context);

        //Inicia o banco
        PostDB postDB = new PostDB(this.context);
        List<Post> posts = postDB.findAll();

        //Parte do TextView (Nome do dono do post)
        t.setText(posts.get(position).autor);
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setTextColor(Color.parseColor("#FF5E5B"));

        //Parte do TextView (Post)
        TextView p = new TextView(context);
        p.setGravity(Gravity.CENTER_VERTICAL);
        p.setText(posts.get(position).desc);
        p.setTextColor(Color.parseColor("#FFFFEA"));

        //Parte do CardView
        int count = 0;
        c.setCardBackgroundColor(Color.parseColor("#00CECB"));
        c.setRadius(1f);
        c.setMinimumHeight(100);
        c.setMinimumHeight(280);
        c.addView(t, count);
        c.addView(p, count);
        count++;

        return c;
    }
}
