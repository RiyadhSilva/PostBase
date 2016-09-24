package com.example.riyad.postbase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by riyad on 31/08/2016.
 */
public class PesquisaAdapter extends BaseAdapter {
    private Context context;
    public List<Atividade> atividades;
    public TextView textView;
    public PesquisaAdapter(Context context, List<Atividade> atividades){
        super();
        this.context = context;
        this.atividades = atividades;
    }
    @Override
    public int getCount() {
        return atividades.size();//Retorna a quantidade de items no adapter
    }

    @Override
    public Object getItem(int position) {
        return atividades.get(position).nome;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        textView = new TextView(context);
        textView.setText(atividades.get(position).desc);

        return textView;
    }
}
