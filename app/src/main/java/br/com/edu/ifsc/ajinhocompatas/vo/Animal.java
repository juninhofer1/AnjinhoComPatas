package br.com.edu.ifsc.ajinhocompatas.vo;

import java.io.Serializable;

/**
 * Created by keila on 26/10/2017.
 */

public class Animal implements Serializable{
    private int imagem;
    private String nome;
    private String descricao;
    private String raca;
    private String tamanho;
    private String cor;
    private int idade;


    //é chamado no fragment pra adicionar os componentes abaixo

    public Animal(int imageId, String nome, String descricao, String raca, String tamanho, String cor, int idade){
        this.imagem = imageId;
        this.nome = nome;
        this.descricao = descricao;
        this.raca = raca;
        this.tamanho = tamanho;
        this.cor = cor;
        this.idade =idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
