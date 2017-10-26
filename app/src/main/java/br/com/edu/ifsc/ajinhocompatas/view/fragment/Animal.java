package br.com.edu.ifsc.ajinhocompatas.view.fragment;

/**
 * Created by keila on 26/10/2017.
 */

public class Animal {
    private int imagemId;
    private String nome;
    private String descricao;

    public Animal(int imageId, String nome, String descricao) {
        this.imagemId = imageId;
        this.nome = nome;
        this.descricao = descricao;
    }

}

    public int getImagemId() {
        return imagemId;
    }

    public void setImageId(int imageId) {
        this.imagemId = imageId;
    }

    public String getTitle() {
        return nome;
    }

    public void setTitle(String title) {
        this.nome = title;
    }

    public String getDescription() {
        return descricao;
    }

    public void setDescription(String description) {
        this.descricao = description;
    }
}