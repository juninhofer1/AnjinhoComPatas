package br.com.edu.ifsc.ajinhocompatas.view;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    public class AnimalDetalhesActivity extends AppCompatActivity {

        private Animal mAnimal;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.grid_animal_detalhes);

            mAnimal = (Animal) getIntent().getExtras().get("animalzinhoSelecionado");

            ImageView imagemAnimalDetalhes = (ImageView) findViewById(R.id.imagemDetalhesId);
            Button botaoAdotar = (Button) findViewById(R.id.buttonAdotarId);
            TextView racaAnimal = (TextView) findViewById(R.id.racaAnimalId);
            TextView tamanhoAnimal = (TextView) findViewById(R.id.tamanhoAnimalId);
            TextView corAnimal = (TextView) findViewById(R.id.corAnimalId);
            TextView idadeAnimal = (TextView) findViewById(R.id.idadeAnimalId);

            racaAnimal.setText(mAnimal.getDescricao());
        }
    }



