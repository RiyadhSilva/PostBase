package com.example.riyad.postbase;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by riyad on 30/08/2016.
 */
public class Comentario implements Serializable {
    private static final long serialVersionUID = 6601006766832473959L;
    public long id;
    public long id_post;
    public String autor;
    public Date data;
    public String desc;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", id_post=" + id_post +
                ", autor='" + autor + '\'' +
                ", data=" + data +
                ", desc='" + desc + '\'' +
                '}';
    }
}
