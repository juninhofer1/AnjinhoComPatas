package br.com.edu.ifsc.ajinhocompatas.view.fragment;

/**
 * Created by keila on 26/10/2017.
 */

public class AnimalGato {
    private int imagemGatoId;
    private String nomeGato;
    private String descricaoGato;

    //é chamado no fragment pra adicionar os componentes abaixo
    public AnimalGato(int imageGatoId, String nomeGato, String descricaoGato) {
        this.imagemGatoId = imageGatoId;
        this.nomeGato = nomeGato;
        this.descricaoGato = descricaoGato;
    }



    public int getImagemGatoId() {
        return imagemGatoId;
    }

    public void setImageGatoId(int imageGatoId) {
        this.imagemGatoId = imageGatoId;
    }

    public String getTitle() {
        return nomeGato;
    }

    public void setTitle(String title) {
        this.nomeGato = title;
    }

    public String getDescription() {
        return descricaoGato;
    }

    public void setDescription(String description) {
        this.descricaoGato = description;
    }
}