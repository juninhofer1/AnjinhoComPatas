package br.com.edu.ifsc.ajinhocompatas.props;

/**
 * Created by Wilson on 07/10/2017.
 */

public enum ActivityProps {

    INICIAL (1, "Inicial"),
    FAVORITOS (2, "Favoritos"),
    ECONTRE_UM_AMIGO (3, "Encontre um amigo"),
    AJUDA (4, "Ajuda"),
    SAIR (5, "Sair");

    private int mId;

    ActivityProps(int aId, String aInicial) {
        this.mId = aId;
        this.mNomeTela = aInicial;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNomeTela() {
        return mNomeTela;
    }

    public void setmNomeTela(String mNomeTela) {
        this.mNomeTela = mNomeTela;
    }

    private String mNomeTela;




}
