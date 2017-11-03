package br.com.edu.ifsc.ajinhocompatas.utilitarios;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Wilson on 07/10/2017.
 */

public final class DialogUtil {

    public static Dialog dialogYesNo(Activity aContext, String aMensagem, DialogInterface.OnClickListener aOnclickYes, DialogInterface.OnClickListener aOnclikNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(aContext)
                .setPositiveButton("Sim", aOnclickYes)
                .setNegativeButton("Não", aOnclikNo);
        builder.setTitle("Alerta");
        builder.setMessage(aMensagem);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
