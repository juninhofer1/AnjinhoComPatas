package br.com.edu.ifsc.anjinhocompatas.utilitarios;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import br.com.edu.ifsc.anjinhocompatas.R;

/**
 * Created by Wilson on 12/11/2017.
 */

public class SharedPreferencesUtil {

    public static void criarPreferenciaString(Activity aContext, String aValor, int aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(aContext.getString(aKeyPreference), aValor);
        editor.apply();
        editor.commit();
    }

    public static void criarPreferenciaString(Activity aContext, String aValor, String aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(aKeyPreference, aValor);
        editor.apply();
        editor.commit();
    }

    public static String lerPreferenciaString(Activity aContext, int aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        String lPreference = null;
        if (sharedPref.contains(aContext.getString(aKeyPreference))) {
            lPreference = sharedPref.getString(aContext.getString(aKeyPreference), null);
        }
        return lPreference;
    }

    public static String lerPreferenciaString(Activity aContext, String aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        String lPreference = sharedPref.getString(aKeyPreference, null);
        return lPreference;
    }


    public static void removerPreferencia(Activity aContext, String aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(aKeyPreference);
        editor.apply();
        editor.commit();
    }

    public static void removerPreferencia(Activity aContext, int aKeyPreference) {
        SharedPreferences sharedPref = aContext.getSharedPreferences(aContext.getString(R.string.key_app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(aContext.getString(aKeyPreference));
        editor.apply();
        editor.commit();
    }

}
