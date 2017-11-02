package br.com.edu.ifsc.ajinhocompatas.vo;

/**
 * Created by keila on 26/10/2017.
 */

public class Animal {
    private int imagem;
    private String nome;
    private String descricao;

    //é chamado no fragment pra adicionar os componentes abaixo
    public Animal(int imageId, String nome, String descricao) {
        this.imagem = imageId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
