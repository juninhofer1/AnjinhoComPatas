package br.com.edu.ifsc.anjinhocompatas.modelos;

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
}
