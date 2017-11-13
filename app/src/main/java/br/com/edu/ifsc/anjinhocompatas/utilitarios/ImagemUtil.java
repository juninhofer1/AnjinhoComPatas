package br.com.edu.ifsc.anjinhocompatas.utilitarios;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Base64;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Wilson on 13/11/2017.
 */

public class ImagemUtil {

    public static Bitmap converter(String aBase64) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(aBase64.substring(aBase64.indexOf(",")  + 1), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String converter(Bitmap aBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        aBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Drawable converterBase64(Resources aResources, String aBase64) {
        Drawable lDrawable = new BitmapDrawable(aResources, converter(aBase64));
        return lDrawable;
    }

    public static Bitmap getBitmapPelaUrl(String aUrl) {
        Bitmap lBitMapImagemPergil = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String lImagemPergil = aUrl;
            URL lfaceboolImagemUrl = new URL(lImagemPergil);//small | noraml | large
            HttpsURLConnection lConexao = (HttpsURLConnection) lfaceboolImagemUrl.openConnection();
            HttpsURLConnection.setFollowRedirects(true);
            lConexao.setInstanceFollowRedirects(true);
            lBitMapImagemPergil = BitmapFactory.decodeStream(lConexao.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lBitMapImagemPergil;
    }

}


