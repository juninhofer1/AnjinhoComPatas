package br.com.edu.ifsc.ajinhocompatas.view.fragment;

/**
 * Created by keila on 26/10/2017.
 */

public class AnimalCao{
    private int imagemCaoId;
    private String nomeCao;
    private String descricaoCao;

    //é chamado no fragment pra adicionar os componentes abaixo
    public AnimalCao(int imageCaoId, String nomeCao, String descricaoCao) {
        this.imagemCaoId = imageCaoId;
        this.nomeCao = nomeCao;
        this.descricaoCao = descricaoCao;
    }

    public int getImagemCaoId() {
        return imagemCaoId;
    }

    public void setImageCaoId(int imageCaoId) {
        this.imagemCaoId = imageCaoId;
    }

    public String getTitle() {
        return nomeCao;
    }

    public void setTitle(String title) {
        this.nomeCao = title;
    }

    public String getDescription() {
        return descricaoCao;
    }

    public void setDescription(String description) {
        this.descricaoCao = description;
    }
}
