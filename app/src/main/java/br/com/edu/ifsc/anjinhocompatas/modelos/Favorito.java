package br.com.edu.ifsc.anjinhocompatas.modelos;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.AnimalDao;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.FavoritosDAO;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.UsuarioDao;

/**
 * Created by Wilson on 26/11/2017.
 */

public class Favorito {

    private Integer id;
    private Integer usuario;
    private Animal animal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public static List<Animal> carregarFavoritos(Context aContext,int aIdUsuario){
        FavoritosDAO lFavoritosDAO = criarConexaoTabelaFavorito(aContext);
        List<Favorito> lFavoritos = lFavoritosDAO.carregarFavoritosIdUsuario(aIdUsuario);
        List<Animal> lAnimais = new ArrayList<>();
        for (Favorito lFavorito : lFavoritos) {
            lAnimais.add(lFavorito.getAnimal());
        }
        return lAnimais;
    }

    public static boolean savarFavoritoBaseDados (Context aContext, Favorito lFavorito){
        FavoritosDAO lFavoritoDao = criarConexaoTabelaFavorito(aContext);
        long retornoBD = -1;
        retornoBD = lFavoritoDao.salvar(lFavorito);
        if(retornoBD >= 0) {
            return true;
        }
        return false;
    }

    public static Favorito carregarPorIdUsuarioIdAnial(Context aContext, int idUsuario, int idAnimal){
        FavoritosDAO lFavoritosDAO = criarConexaoTabelaFavorito(aContext);
        Favorito lFavorito = lFavoritosDAO.carregarPorIdUsuarioEAnimal(idUsuario, idAnimal);
        return lFavorito;
    }

    public static boolean remover(Context aContext, Favorito lFavorito){
        FavoritosDAO lFavoritosDAO = criarConexaoTabelaFavorito(aContext);
        return lFavoritosDAO.apagar(lFavorito);
    }

    private static FavoritosDAO criarConexaoTabelaFavorito(Context aContext) {
        FavoritosDAO lFavoritosDAO = new FavoritosDAO(aContext);
        lFavoritosDAO.open();
        return lFavoritosDAO;
    }
}
