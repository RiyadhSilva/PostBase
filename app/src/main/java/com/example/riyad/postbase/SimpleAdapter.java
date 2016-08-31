package com.example.riyad.postbase;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by riyad on 29/08/2016.
 */
public class SimpleAdapter extends BaseAdapter{
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
        return (this.posts().get(position).autor);//Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(final int position, View convertView, ViewGroup parent){
        TextView t = new TextView(context);
        CardView c = new CardView(context);

        //Inicia o banco
        final PostDB postDB = new PostDB(this.context);
        final List<Post> posts = postDB.findAll();
        Collections.reverse(posts);

        //Parte do TextView (Nome do autor do post)
        t.setText(posts.get(position).autor);
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setTextColor(Color.parseColor("#FAA4BD"));

        //Parte do TextView (Post)
        TextView p = new TextView(context);
        p.setGravity(Gravity.CENTER_VERTICAL);
        p.setText(posts.get(position).desc);
        p.setTextColor(Color.parseColor("#FAE3C6"));

        //Parte do Button (Deletar)
        final Button bt_deletar = new Button(context);
        bt_deletar.setX(0f);
        bt_deletar.setY(0f);
        bt_deletar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        bt_deletar.setText("Deletar");
        bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deleta o elemento na posicao
                postDB.delete(posts.get(position));
                //
                toast("Post excluído!");
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        //Parte do Button (Curtir)
        Button bt_curtir = new Button(context);
        bt_curtir.setX(190f);
        bt_curtir.setY(0f);
        bt_curtir.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bt_curtir.setText("Curtir");
        bt_curtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deleta o elemento na posicao
                postDB.delete(posts.get(position));

            }
        });

        //Parte do CardView
        int count = 0;
        c.setCardBackgroundColor(Color.parseColor("#533B4D"));
        c.setRadius(1f);
        c.setCardElevation(1f);
        c.setMinimumHeight(100);
        c.setMinimumHeight(280);
        c.addView(t, count);
        c.addView(p, count);
        c.addView(bt_deletar, count);
        c.addView(bt_curtir, count);
        count++;

        return c;
    }

    private void toast(String msg){

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
