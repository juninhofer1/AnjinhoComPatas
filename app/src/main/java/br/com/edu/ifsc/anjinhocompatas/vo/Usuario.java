package br.com.edu.ifsc.anjinhocompatas.vo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.edu.ifsc.anjinhocompatas.dao.implementacao.UsuarioDao;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.ImagemUtil;

/**
 * Created by Mobile on 09/10/2017.
 */

public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String email;
    private String foto;
    private String dataNascimento;
    private int idade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public static Usuario converterObjetoJsonFacebook (JSONObject aJsonObject) throws JSONException{
        Usuario lUsuario = new Usuario();
        String lEmail = aJsonObject.getString("email");
        if(lEmail != null) {
            lUsuario.setEmail(lEmail);
        }

        String lNome = aJsonObject.getString("name");
        if(lNome != null) {
            lUsuario.setNome(lNome);
        }

        if(aJsonObject.getJSONObject("picture") != null) {
            String aUrl = aJsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
            Bitmap LBitmap = ImagemUtil.getBitmapPelaUrl(aUrl);
            if(LBitmap != null) {
                String lImageBase64 = ImagemUtil.converter(LBitmap);
                lUsuario.setFoto(lImageBase64);
            }
        }

        String lDataNascimento = aJsonObject.getString("birthday");
        if(lDataNascimento != null) {
            lUsuario.setDataNascimento(lDataNascimento);
        }
        return lUsuario;
    }

    public static boolean savarUsuarioBaseDados (Context aContext, Usuario aUsuario){
        UsuarioDao usuarioDao = new UsuarioDao(aContext);
        usuarioDao.open();
        long retornoBD = -1;
        if(usuarioDao.carregarPorEmail(aUsuario.getEmail()) != null){
            retornoBD = usuarioDao.alterar(aUsuario);
        } else {
            retornoBD = usuarioDao.salvar(aUsuario);
        }
        if(retornoBD <= 0) {
            return true;
        }
        return false;
    }

    public static Usuario carregarUsuarioPorEmailBD (Context aContext, String aEmail){
        UsuarioDao usuarioDao = new UsuarioDao(aContext);
        usuarioDao.open();
        Usuario lUsuario = usuarioDao.carregarPorEmail(aEmail);
        return lUsuario;
    }

}
