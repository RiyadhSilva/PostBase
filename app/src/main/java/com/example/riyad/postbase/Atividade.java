package com.example.riyad.postbase;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by riyad on 30/08/2016.
 */
public class Atividade implements Serializable{
    private static final long serialVersionUID = 6601006766832473959L;
    public long id;
    public String nome;
    public String data;
    public String desc;
    public String custo;
    public String prioridade;


    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", autor='" + nome + '\'' +
                ", data='" + data + '\'' +
                ", desc='" + desc + '\'' +
                ", curtidas='" + custo + '\'' +
                ", prioridade='" + prioridade + '\'' +
                '}';
    }
}
