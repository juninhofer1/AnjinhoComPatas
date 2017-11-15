package br.com.edu.ifsc.anjinhocompatas.constantes;

import br.com.edu.ifsc.anjinhocompatas.R;

/**
 * Created by Wilson on 07/10/2017.
 */

public enum MenuLateralProps {

    INICIAL (MenuLateralOpcoesProps.INICIAL, R.string.opc_menu_inicial),
    FAVORITOS (MenuLateralOpcoesProps.FAVORITOS, R.string.opc_menu_favoritos),
    ECONTRE_UM_AMIGO (MenuLateralOpcoesProps.ECONTRE_UM_AMIGO, R.string.opc_menu_encontre_amigo),
    Entrar (MenuLateralOpcoesProps.ENTRAR, R.string.opc_menu_entrar),
    AJUDA (MenuLateralOpcoesProps.AJUDA, R.string.opc_menu_ajuda),
    SAIR (MenuLateralOpcoesProps.SAIR, R.string.opc_menu_sair);

    private int mId;
    private int mNomeOpc;

    MenuLateralProps(int aId, int aNomeOpc) {
        this.mId = aId;
        this.mNomeOpc = aNomeOpc;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmNomeTela() {
        return mNomeOpc;
    }

    public void setmNomeTela(int mNomeTela) {
        this.mNomeOpc = mNomeTela;
    }






}
