package com.example.riyad.postbase;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by riyad on 30/08/2016.
 */
public class Post implements Serializable{
    private static final long serialVersionUID = 6601006766832473959L;
    public long id;
    public String autor;
    public GregorianCalendar data;
    public String desc;
    public List<Comentario> comentarios;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", autor='" + autor + '\'' +
                ", data=" + data +
                ", desc='" + desc + '\'' +
                ", comentarios=" + comentarios +
                '}';
    }
}
