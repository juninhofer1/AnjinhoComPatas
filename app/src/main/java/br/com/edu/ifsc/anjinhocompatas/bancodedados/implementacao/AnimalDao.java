package br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.bancodedados.BasicDAO;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.TableBuilder;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;

/**
 * Created by Wilson on 21/11/2017.
 */

public class AnimalDao extends BasicDAO {

    public AnimalDao(Context ctx) {
        super(ctx);
    }

    public static final String TABELA = "ANIMAL";
    public static final String ID = "_id";
    public static final String ID_DOADOR = "ID_DOADOR";
    public static final String NOME = "NOME";
    public static final String RACA = "RACA";
    public static final String TAMANHO = "TAMANHO";
    public static final String COR = "COR";
    public static final String IDADE = "IDADE";
    public static final String FOTO = "FOTO";
    public static final String TIPO = "TIPO";

    public static final String CREATE_TABLE = definirTabela();

    private static String definirTabela() {
        TableBuilder tb = new TableBuilder(TABELA);
        try {
            tb.setPrimaryKey(ID, tb.INTEGER, true);
            tb.addColuna(ID_DOADOR, tb.INTEGER, true);
            tb.addColuna(NOME, tb.TEXT, false);
            tb.addColuna(RACA, tb.TEXT, false);
            tb.addColuna(TAMANHO, tb.TEXT, false);
            tb.addColuna(COR, tb.TEXT, false);
            tb.addColuna(IDADE, tb.INTEGER, false);
            tb.addColuna(FOTO, tb.TEXT, false);
            tb.addColuna(TIPO, tb.TEXT, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb.toString();
    }

    public long salvar(Animal aAnimal) {
        ContentValues valores = classificarPorValores(aAnimal);
        return inserir(TABELA, valores);
    }

    public long alterar(Animal aAnimal) {
        ContentValues values = classificarPorValores(aAnimal);
        //Alterando usuário quando o id for igual
        return atualizar(TABELA, values, new String[] { ID }, new String[] { String.valueOf(aAnimal.getId()) });
    }

    public boolean apagar(Animal aAnimal) {
        return remover(TABELA, ID, aAnimal.getId());
    }

    public Animal carregarPorId(long aId) {
        Cursor c = consultar(TABELA, ID, String.valueOf(aId));
        Animal lAnimal = cursorDaClasse(c);
        c.close();
        return lAnimal;
    }


    public List<Animal> carregarPorIdDoador(long aIdDoador) {
        Cursor lCursor = consultar(TABELA, ID_DOADOR, String.valueOf(aIdDoador));
        List<Animal> animalList = new ArrayList<>();
        if (lCursor != null) {
            lCursor.moveToFirst();
            while (!lCursor.isAfterLast()) {
                Animal aAnimal = cursorDaClasse(lCursor);
                animalList.add(aAnimal);
                lCursor.moveToNext();
            }
            lCursor.close();
        }
        return animalList;
    }

    public List<Animal> carregarPorTipo(String aTipo) {
        Cursor lCursor = consultar(TABELA, TIPO, String.valueOf(aTipo));
        List<Animal> animalList = new ArrayList<>();
        if (lCursor != null) {
            lCursor.moveToFirst();
            while (!lCursor.isAfterLast()) {
                Animal aAnimal = cursorDaClasse(lCursor);
                animalList.add(aAnimal);
                lCursor.moveToNext();
            }
            lCursor.close();
        }
        return animalList;
    }

    public List<Animal> carregarTodos() {
        List<Animal> animalList = new ArrayList<>();
        Cursor mCursor = mDb.query(TABELA, null, null, null, null, null, ID);
        if (mCursor != null) {
            mCursor.moveToFirst();

            while (!mCursor.isAfterLast()) {
                Animal aAnimal = cursorDaClasse(mCursor);
                animalList.add(aAnimal);
                mCursor.moveToNext();
            }
            mCursor.close();
        }
        return animalList;
    }

    public static ContentValues classificarPorValores(Animal aAnimal) {
        ContentValues values = new ContentValues();
        values.put(ID, aAnimal.getId());
        values.put(ID_DOADOR, aAnimal.getIdDoador());
        values.put(NOME, aAnimal.getNome());
        values.put(RACA, aAnimal.getRaca());
        values.put(TAMANHO, aAnimal.getTamanho());
        values.put(COR, aAnimal.getCor());
        values.put(IDADE, aAnimal.getIdade());
        values.put(FOTO, aAnimal.getFoto());
        values.put(TIPO, aAnimal.getTipoAnimal());
        return values;
    }

    public static Animal cursorDaClasse(Cursor c) {
        if (c == null || c.getCount() < 1) {
            return null;
        }
        Animal lAnimal = new Animal();
        lAnimal.setId(c.getInt(c.getColumnIndex(ID)));
        lAnimal.setIdDoador(c.getInt(c.getColumnIndex(ID_DOADOR)));
        lAnimal.setNome(c.getString(c.getColumnIndex(NOME)));
        lAnimal.setRaca(c.getString(c.getColumnIndex(RACA)));
        lAnimal.setTamanho(c.getString(c.getColumnIndex(TAMANHO)));
        lAnimal.setCor(c.getString(c.getColumnIndex(COR)));
        lAnimal.setIdade(c.getInt(c.getColumnIndex(IDADE)));
        lAnimal.setFoto(c.getString(c.getColumnIndex(FOTO)));
        lAnimal.setFoto(c.getString(c.getColumnIndex(TIPO)));
        return lAnimal;
    }
}
