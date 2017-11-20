package br.com.edu.ifsc.anjinhocompatas.utilitarios;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Wilson on 07/10/2017.
 */

public final class DialogoUtil {


//    Utilizado para mostrar uma mnssagem com sim e não
    public static Dialog dialogYesNo(Activity aContext, String aMensagem, DialogInterface.OnClickListener aOnclickYes, DialogInterface.OnClickListener aOnclikNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(aContext)
                .setPositiveButton("Sim", aOnclickYes)
                .setNegativeButton("Não", aOnclikNo);
        builder.setTitle("Alerta");
        builder.setMessage(aMensagem);
        return builder.create();
    }


//    Utilizado para mostrar as opções de camera ou galeria
    public static Dialog dialogCamera(Activity aContext, String aMensagem, DialogInterface.OnClickListener aOnclickYes, DialogInterface.OnClickListener aOnclikNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(aContext)
                .setPositiveButton("Galeria", aOnclickYes)
                .setNegativeButton("Camera", aOnclikNo);
        builder.setTitle("Foto");
        builder.setMessage(aMensagem);
        return builder.create();
    }
}
