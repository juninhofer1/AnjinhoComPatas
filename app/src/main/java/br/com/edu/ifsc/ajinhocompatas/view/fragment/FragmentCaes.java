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
import br.com.edu.ifsc.ajinhocompatas.view.adapter.GridViewAdapterDog;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentCaes extends Fragment {

    private View mViewCaes;
    private List<AnimalCao> dogList;
    private GridViewAdapterDog gridViewAdapterDog;
    private GridView gridViewDog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mViewCaes = inflater.inflate(R.layout.fragment_caes, container, false);

        //chama a lista de gatos
        getCaesList();

        //cast
        gridViewDog = (GridView) this.mViewCaes.findViewById(R.id.gridViewCaes);

        //cria novo adapter (context(telinha), modelo da telinha, conteúdo da telinha)
        gridViewAdapterDog = new GridViewAdapterDog(getContext(), R.layout.grid_item, dogList);

        //mostra, seta a telinha do grid
        gridViewDog.setAdapter( gridViewAdapterDog );

        //retorna a telinha
        return this.mViewCaes;
    }

    //Lista com as fotos dos dogs
    public void getCaesList(){

        //cria nova array list
        dogList = new ArrayList<>();

        //adiciona na lista
        dogList.add(new AnimalCao(R.drawable.dog, "Pedrinho", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog2, "Joãozinho", "Yorkshire"));
        dogList.add(new AnimalCao(R.drawable.dog3, "Zé", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog4, "Mine", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog5, "Pompom", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog6, "Billy Gean", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog7, "Molly", "Labrador"));
        dogList.add(new AnimalCao(R.drawable.dog8, "Pepê", "Labrador"));



    }
}
