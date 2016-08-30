package com.example.riyad.postbase;

import android.content.Context;
import android.widget.BaseAdapter;

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
}
