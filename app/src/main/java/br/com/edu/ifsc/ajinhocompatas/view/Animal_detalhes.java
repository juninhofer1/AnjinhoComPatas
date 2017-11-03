package br.com.edu.ifsc.ajinhocompatas.view;


import android.content.Context;
import android.content.Intent;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.R;
import br.com.edu.ifsc.ajinhocompatas.vo.Animal;

/**
 * Created by keila on 02/11/2017.
 */

    public class Animal_detalhes extends ArrayAdapter<Animal> {

    public Animal_detalhes(@NonNull Context context, @LayoutRes int resource, @NonNull List<Animal> objects) {
        super(context, resource, objects);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
                View viewDetalhes = convertView;


                //cast
                ImageView imagemAnimalDetalhes = (ImageView) viewDetalhes.findViewById(R.id.imagemDetalhesId);
                Button botaoAdotar = (Button) viewDetalhes.findViewById(R.id.buttonAdotarId);
                TextView racaAnimal = (TextView) viewDetalhes.findViewById(R.id.racaAnimalId);
                TextView tamanhoAnimal = (TextView)viewDetalhes.findViewById(R.id.tamanhoAnimalId);
                TextView corAnimal = (TextView) viewDetalhes.findViewById(R.id.corAnimalId);
                TextView idadeAnimal = (TextView) viewDetalhes.findViewById(R.id.idadeAnimalId);


                return viewDetalhes;
            }


    }


