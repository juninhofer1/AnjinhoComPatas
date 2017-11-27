package br.com.edu.ifsc.anjinhocompatas.modelos;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.AnimalDao;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.UsuarioDao;

/**
 * Created by keila on 26/10/2017.
 */

public class Animal implements Serializable{

    private Integer id;
    private int idDoador;
    private int imagem;
    private String nome;
    private String raca;
    private String tamanho;
    private String cor;
    private int idade;
    private String foto;
    private String tipoAnimal;

    //é chamado no fragment pra adicionar os componentes abaixo

    public Animal(int imageId, String nome, String raca, String tamanho, String cor, int idade){
        this.imagem = imageId;
        this.nome = nome;
        this.raca = raca;
        this.tamanho = tamanho;
        this.cor = cor;
        this.idade =idade;
    }

    public Animal(Integer id, int idDoador, int imagem, String nome, String raca, String tamanho, String cor, int idade, String foto) {
        this.id = id;
        this.idDoador = idDoador;
        this.imagem = imagem;
        this.nome = nome;
        this.raca = raca;
        this.tamanho = tamanho;
        this.cor = cor;
        this.idade = idade;
        this.foto = foto;
    }

    public Animal() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public boolean salvarAnimalDoacao() {
        return false;
    }

    public boolean solicitarAdocao() {
        return false;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public static Animal carregarPorId(Context aContext, int id){
        AnimalDao lAnimalDao = criarConexaoTabelaAnimal(aContext);
        Animal lUsuario = lAnimalDao.carregarPorId(id);
        return lUsuario;
    }

    public static List<Animal> carregarTodosAnimaisPorTipo(Context aContext, String aTipo){
        AnimalDao lAnimalDao = new AnimalDao(aContext);
        List<Animal> lAnimais = lAnimalDao.carregarPorTipo(aTipo);
        return lAnimais;
    }

    private static AnimalDao criarConexaoTabelaAnimal(Context aContext) {
        AnimalDao lAnimalDao = new AnimalDao(aContext);
        lAnimalDao.open();
        return lAnimalDao;
    }

    public static boolean savarAnimalBaseDados (Context aContext, Animal aAnimal){
        AnimalDao lAnimalDao = criarConexaoTabelaAnimal(aContext);
        long retornoBD = -1;
        retornoBD = lAnimalDao.salvar(aAnimal);
        if(retornoBD >= 0) {
            return true;
        }
        return false;
    }

}
