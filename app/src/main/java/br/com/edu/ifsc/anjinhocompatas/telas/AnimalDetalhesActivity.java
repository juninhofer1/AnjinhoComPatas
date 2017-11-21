package br.com.edu.ifsc.anjinhocompatas.telas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.CoresUtil;

/**
     * Created by keila on 02/11/2017.
     */

    public class AnimalDetalhesActivity extends AppCompatActivity {

        private Animal mAnimal;
        private boolean mFavorito = false;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.grid_animal_detalhes);

            mAnimal = (Animal) getIntent().getExtras().get("animalzinhoSelecionado");
            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

            floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                    R.drawable.ic_add_favorite, R.color.icons));

            ImageView imagemAnimalDetalhes = (ImageView) findViewById(R.id.imagemDetalhesId);
            TextView nomeAnimal = (TextView) findViewById(R.id.nomeAnimalId);
            Button botaoAdotar = (Button) findViewById(R.id.buttonAdotarId);
            TextView racaAnimal = (TextView) findViewById(R.id.racaAnimalId);
            TextView tamanhoAnimal = (TextView) findViewById(R.id.tamanhoAnimalId);
            TextView corAnimal = (TextView) findViewById(R.id.corAnimalId);
            TextView idadeAnimal = (TextView) findViewById(R.id.idadeAnimalId);

            racaAnimal.setText(mAnimal.getRaca());
            imagemAnimalDetalhes.setImageResource(mAnimal.getImagem());
            nomeAnimal.setText(mAnimal.getNome());
            tamanhoAnimal.setText(mAnimal.getTamanho());
            corAnimal.setText(mAnimal.getCor());
            idadeAnimal.setText(String.valueOf(mAnimal.getIdade()));

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mFavorito) {
                        mFavorito = true;
                        CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                                R.drawable.ic_add_favorite, R.color.favorite);
                    } else {
                        mFavorito = false;
                        CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                                R.drawable.ic_add_favorite, R.color.icons);
                    }
                }
            });

        }
    }




