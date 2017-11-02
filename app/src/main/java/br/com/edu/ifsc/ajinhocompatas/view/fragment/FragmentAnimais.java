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
import br.com.edu.ifsc.ajinhocompatas.view.adapter.GridViewAdapterAnimais;
import br.com.edu.ifsc.ajinhocompatas.vo.Animal;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentAnimais extends Fragment {

    private View mViewCaes;
    private List<Animal> listAnimal;
    private GridViewAdapterAnimais gridViewAdapterDog;
    private GridView gridViewDog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mViewCaes = inflater.inflate(R.layout.fragment_grid_animal, container, false);

        Bundle bundle = getArguments();
        boolean isGato = bundle.getBoolean("animalzinho");

        //chama a lista de gatos
        getList(isGato);

        //cast
        gridViewDog = (GridView) this.mViewCaes.findViewById(R.id.gridViewCaes);

        //cria novo adapter (context(telinha), modelo da telinha, conteúdo da telinha)
        gridViewAdapterDog = new GridViewAdapterAnimais(getContext(), R.layout.grid_item, listAnimal);

        //mostra, seta a telinha do grid
        gridViewDog.setAdapter( gridViewAdapterDog );

        //retorna a telinha
        return this.mViewCaes;
    }

    //Lista com as fotos dos dogs
    public void getList(boolean isGato){

        //cria nova array list
        listAnimal = new ArrayList<>();

        //adiciona na lista
        if (isGato) {
            listAnimal.add(new Animal(R.drawable.cat, "Cat 1", "Persa"));
            listAnimal.add(new Animal(R.drawable.cat2, "Cat 2", "Siamês"));
            listAnimal.add(new Animal(R.drawable.cat3, "Cat 3", "Azul Russo"));
            listAnimal.add(new Animal(R.drawable.cat4, "Cat 4", "Exótico"));
        } else {
            listAnimal.add(new Animal(R.drawable.dog, "Pedrinho", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog2, "Joãozinho", "Yorkshire"));
            listAnimal.add(new Animal(R.drawable.dog3, "Zé", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog4, "Mine", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog5, "Pompom", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog6, "Billy Gean", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog7, "Molly", "Labrador"));
            listAnimal.add(new Animal(R.drawable.dog8, "Pepê", "Labrador"));
        }






    }
}
