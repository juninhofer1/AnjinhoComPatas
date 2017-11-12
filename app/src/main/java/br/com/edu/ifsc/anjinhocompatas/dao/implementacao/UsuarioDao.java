package br.com.edu.ifsc.anjinhocompatas.dao.implementacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.dao.BasicDAO;
import br.com.edu.ifsc.anjinhocompatas.dao.TableBuilder;
import br.com.edu.ifsc.anjinhocompatas.vo.Usuario;

/**
 * Created by Mobile on 09/10/2017.
 */

public class UsuarioDao extends BasicDAO {

    public UsuarioDao(Context ctx) {
        super(ctx);
    }

    public static final String TABELA = "USUARIO";
    public static final String ID = "_id";
    public static final String NOME = "NOME";
    public static final String EMAIL = "EMAIL";
    public static final String ENDERECO = "ENDERECO";
    public static final String IDADE = "IDADE";

    public static final String CREATE_TABLE = definirTabela();

    private static String definirTabela() {
        TableBuilder tb = new TableBuilder(TABELA);
        try {
            tb.setPrimaryKey(ID, tb.INTEGER, true);
            tb.addColuna(NOME, tb.TEXT, false);
            tb.addColuna(EMAIL, tb.TEXT, false);
            tb.addColuna(ENDERECO, tb.TEXT, false);
            tb.addColuna(IDADE, tb.INTEGER, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb.toString();
    }

    public long salvar(Usuario aUsuario) {
        ContentValues valores = classificarPorValores(aUsuario);
        return inserir(TABELA, valores);
    }

    public long alterar(Usuario aUsuario) {
        ContentValues values = classificarPorValores(aUsuario);
        //Alterando usuário quando o id for igual
        return atualizar(TABELA, values, new String[] { EMAIL }, new String[] { String.valueOf(aUsuario.getId()) });
    }

    public boolean apagar(Usuario aUsuario) {
        return remover(TABELA, ID, aUsuario.getId());
    }

    public Usuario carregarPorId(long aId) {
        Cursor c = consultar(TABELA, ID, String.valueOf(aId));
        Usuario use = cursorDaClasse(c);
        c.close();
        return use;
    }

    public Usuario carregarPorEmail(String aEmail) {
        Cursor c = consultar(TABELA, EMAIL, aEmail);
        Usuario lUser = cursorDaClasse(c);
        c.close();
        return lUser;
    }

    public List<Usuario> carregarTodosOsUruarios() {
        List<Usuario> usuariosList = new ArrayList<>();

        Cursor mCursor = mDb.query(TABELA, null, null, null, null, null, ID);
        if (mCursor != null) {
            mCursor.moveToFirst();

            while (!mCursor.isAfterLast()) {
                Usuario uses = cursorDaClasse(mCursor);
                usuariosList.add(uses);
                mCursor.moveToNext();
            }
            mCursor.close();
        }

        return usuariosList;
    }

    public static ContentValues classificarPorValores(Usuario aUsuario) {
        ContentValues values = new ContentValues();
        values.put(ID, aUsuario.getId());
        values.put(NOME, aUsuario.getNome());
        values.put(EMAIL, aUsuario.getEmail());
        values.put(ENDERECO, aUsuario.getEndereco());
        values.put(IDADE, aUsuario.getIdade());
        return values;
    }

    public static Usuario cursorDaClasse(Cursor c) {
        if (c == null || c.getCount() < 1) {
            return null;
        }
        Usuario user = new Usuario();
        user.setId(c.getInt(c.getColumnIndex(ID)));
        user.setNome(c.getString(c.getColumnIndex(NOME)));
        user.setEmail(c.getString(c.getColumnIndex(EMAIL)));
        user.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
        user.setIdade(c.getInt(c.getColumnIndex(IDADE)));
        return user;
    }
}
