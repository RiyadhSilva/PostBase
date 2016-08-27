package com.example.riyad.postbase;

import java.io.Serializable;

/**
 * Created by riyad on 27/08/2016.
 */
public class Carro implements Serializable {
    private static final long serialVersionUID = 6601006766832473959L;
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }


}
