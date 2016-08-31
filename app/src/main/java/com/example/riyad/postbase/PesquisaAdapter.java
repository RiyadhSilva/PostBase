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
    public List<Post> posts;
    public TextView textView;
    public PesquisaAdapter(Context context, List<Post> posts){
        super();
        this.context = context;
        this.posts = posts;
    }
    @Override
    public int getCount() {
        return posts.size();//Retorna a quantidade de items no adapter
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position).autor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        textView = new TextView(context);
        textView.setText(posts.get(position).desc);

        return textView;
    }
}
