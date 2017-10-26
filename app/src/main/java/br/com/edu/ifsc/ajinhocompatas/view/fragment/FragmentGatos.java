package br.com.edu.ifsc.ajinhocompatas.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.R;
import br.com.edu.ifsc.ajinhocompatas.view.adapter.GridViewAdapterCat;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentGatos extends Fragment {

    private View mViewGatos;
    private List<Animal> catList;
    private GridViewAdapterCat gridViewAdapterCat;
    private GridView gridViewCat;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mViewGatos = inflater.inflate(R.layout.fragment_gatos, container, false);
        //chama a lista de gatos
        getGatosList();

        //cast
        gridViewCat = (GridView) this.mViewGatos.findViewById(R.id.gridViewGatos);

        //cria novo adapter (context(telinha), modelo da telinha, conteúdo da telinha)
        gridViewAdapterCat = new GridViewAdapterCat(getContext(), R.layout.grid_item, catList);

        //mostra, seta a telinha do grid
        gridViewCat.setAdapter( gridViewAdapterCat );

        return this.mViewGatos;
    }

    //Lista com as fotos
    public void getGatosList() {
        //cria nova array list
        catList = new ArrayList<>();
        //adiciona na lista
        catList.add(new Animal(R.drawable.cat, "Cat 1", "Persa"));
        catList.add(new Animal(R.drawable.cat2, "Cat 2", "Siamês"));
        catList.add(new Animal(R.drawable.cat3, "Cat 3", "Azul Russo"));
        catList.add(new Animal(R.drawable.cat4, "Cat 4", "Exótico"));
    };
    }

