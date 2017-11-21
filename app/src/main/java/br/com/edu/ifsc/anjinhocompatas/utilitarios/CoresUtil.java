package br.com.edu.ifsc.anjinhocompatas.utilitarios;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;

import br.com.edu.ifsc.anjinhocompatas.R;

/**
 * Created by Wilson on 06/10/2017.
 */

public class CoresUtil {

    public static Drawable alterarCorDrawable(Context aAplicacao, int aResourceIdImagem, int aResourceColor) {
        Drawable lMyIcon = null;
        int lColor;
        lMyIcon = aAplicacao.getResources().getDrawable(aResourceIdImagem);
        lColor = aAplicacao.getResources().getColor(aResourceColor);

        ColorFilter filter = new LightingColorFilter(lColor, lColor);
        lMyIcon.setColorFilter(filter);
        return lMyIcon;
    }

    public static Drawable alterarCorDrawableMenuItem(Context aAplicacao, int aResourceIdImagem) {
        return alterarCorDrawable(aAplicacao,aResourceIdImagem, R.color.colorPrimary);
    }

    public static Drawable alterarCorDrawableMenuItem(Context aAplicacao, int aResourceIdImagem, int aColor) {
        return alterarCorDrawable(aAplicacao,aResourceIdImagem, aColor);
    }

}
