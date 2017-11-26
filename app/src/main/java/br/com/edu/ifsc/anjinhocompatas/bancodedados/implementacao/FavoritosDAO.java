package br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.bancodedados.BasicDAO;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.TableBuilder;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.modelos.Favorito;
/**
 * Created by Wilson on 24/11/2017.
 */

public class FavoritosDAO extends BasicDAO {

    public FavoritosDAO(Context ctx) {
        super(ctx);
    }

    public static final String TABELA = "FAVORITOS";
    public static final String ID = "_id";
    public static final String ID_USUARIO = "ID_USUARIO";
    public static final String ID_ANIMAL = "ID_ANIMAL";

    public static final String CREATE_TABLE = definirTabela();

    private static String definirTabela() {
        TableBuilder tb = new TableBuilder(TABELA);
        try {
            tb.setPrimaryKey(ID, tb.INTEGER, true);
            tb.addColuna(ID_USUARIO, tb.INTEGER, false);
            tb.addColuna(ID_ANIMAL, tb.INTEGER, false);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tb.toString();
    }

    public static ContentValues classificarPorValores(Favorito aFavoritos) {
        ContentValues values = new ContentValues();
        if(aFavoritos.getId() != null) {
            values.put(ID, aFavoritos.getId());
        }

        values.put(ID_USUARIO, aFavoritos.getUsuario());
        values.put(ID_ANIMAL, aFavoritos.getAnimal().getId());
        return values;
    }

    public static Favorito cursorDaClasse(Cursor c) {
        if (c == null || c.getCount() < 1) {
            return null;
        }
        Favorito aFavoritos = new Favorito();
        aFavoritos.setId(c.getInt(c.getColumnIndex(ID)));
        Integer lIdAnimal = c.getInt(c.getColumnIndex(ID_ANIMAL));
        aFavoritos.setUsuario(c.getInt(c.getColumnIndex(ID_USUARIO)));
        aFavoritos.setAnimal(Animal.carregarPorId(context, lIdAnimal));
        return aFavoritos;
    }

    public long salvar(Favorito aFavoritos) {
        ContentValues valores = classificarPorValores(aFavoritos);
        return inserir(TABELA, valores);
    }

    public long alterar(Favorito aFavoritos) {
        ContentValues values = classificarPorValores(aFavoritos);
        //Alterando usuário quando o id for igual
        return atualizar(TABELA, values, new String[] { ID }, new String[] { String.valueOf(aFavoritos.getId()) });
    }

    public boolean apagar(Favorito aFavoritos) {
        return remover(TABELA, ID, aFavoritos.getId());
    }

    public Favorito carregarPorId(long aId) {
        Cursor c = consultar(TABELA, ID, String.valueOf(aId));
        Favorito lFavoritos = cursorDaClasse(c);
        c.close();
        return lFavoritos;
    }

    public List<Favorito> carregarFavoritosIdUsuario(long aId) {
        Cursor lCursor = consultar(TABELA, ID_USUARIO, String.valueOf(aId));
        List<Favorito> lFavoritos = new ArrayList<>();
        if (lCursor != null) {
            lCursor.moveToFirst();
            while (!lCursor.isAfterLast()) {
                Favorito lFavorito = cursorDaClasse(lCursor);
                lFavoritos.add(lFavorito);
                lCursor.moveToNext();
            }
            lCursor.close();
        }
        return lFavoritos;
    }

//    public List<Animal> carregarFavoritosIdUsuario(int idUsuario) {
//        Animal animal
//    }


}
