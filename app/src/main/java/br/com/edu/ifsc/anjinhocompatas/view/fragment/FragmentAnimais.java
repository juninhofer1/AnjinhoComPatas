package br.com.edu.ifsc.anjinhocompatas.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.view.AnimalDetalhesActivity;
import br.com.edu.ifsc.anjinhocompatas.view.adapter.GridViewAdapterAnimaisRecycler;
import br.com.edu.ifsc.anjinhocompatas.view.adapter.OnItemClickRecycler;
import br.com.edu.ifsc.anjinhocompatas.vo.Animal;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentAnimais extends Fragment {

    private View mView;
    private List<Animal> listAnimal;
    private GridViewAdapterAnimaisRecycler gridViewAdapter;
    private RecyclerView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_grid_animal, container, false);

        //retorna a telinha
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        boolean isGato = bundle.getBoolean("animalzinho");

        //chama a lista de gatos
        getList(isGato);
        //cast
        gridView = (RecyclerView) mView.findViewById(R.id.gridView);

        //cria novo adapter (context(telinha), modelo telinha,conteúdo da telinha, inteface para click na tela)
        gridViewAdapter = new GridViewAdapterAnimaisRecycler(getContext(), R.layout.grid_item, listAnimal, onItemClickRecycler);

//        Define se o recyclerview será uma lista ou uma grid 2 para definir as colunas
        gridView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext().getApplicationContext(),2);
//        define a configuração acima em nosso recycler
        gridView.setLayoutManager(layoutManager);

        //mostra, seta a telinha do grid
        gridView.setAdapter(gridViewAdapter);

    }

//    Evento de click, o que será feito quando clckar em um item da lista
        OnItemClickRecycler onItemClickRecycler = new OnItemClickRecycler() {
        @Override
        public void onItemClick(View v, int position) {
//            Pega Item na lista
            Animal lAnimal = listAnimal.get(position);
//            Cria intente para abrir a activity
            Intent intent = new Intent(getActivity(), AnimalDetalhesActivity.class);
//            passa como parametro uma animal para a activity que vai abrir
            intent.putExtra("animalzinhoSelecionado", lAnimal);
//            starta a activity
            startActivity(intent);
        }
    };

    //Lista com as fotos dos dogs
    public void getList(boolean isGato) {

        //cria nova array list
        listAnimal = new ArrayList<>();

        //adiciona na lista
        if (isGato) {
            listAnimal.add(new Animal(R.drawable.cat, "Cat 1", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.cat2, "Cat 2","siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.cat3, "Cat 3","siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.cat4, "Cat 4", "siamês", "pequeno", "azul", 2));
        } else {
            listAnimal.add(new Animal(R.drawable.dog, "Pedrinho", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog2, "Joãozinho", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog3, "Zé", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog4, "Mine", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog5, "Pompom", "siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog6, "Billy Gean","siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog7, "Molly","siamês", "pequeno", "azul", 2));
            listAnimal.add(new Animal(R.drawable.dog8, "Pepê","siamês", "pequeno", "azul", 2));
        }
    }
}
