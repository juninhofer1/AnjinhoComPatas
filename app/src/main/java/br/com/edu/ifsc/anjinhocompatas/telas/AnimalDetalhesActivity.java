package br.com.edu.ifsc.anjinhocompatas.telas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import br.com.edu.ifsc.anjinhocompatas.MainActivity;
import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.modelos.Favorito;
import br.com.edu.ifsc.anjinhocompatas.modelos.Usuario;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.CoresUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.ImagemUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;

/**
     * Created by keila on 02/11/2017.
     */

public class AnimalDetalhesActivity extends AppCompatActivity {

    private Animal mAnimal;
    private Usuario mUsuario;
    private boolean mFavorito = false;
    private FloatingActionButton floatingActionButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_animal_detalhes);

        mUsuario = Usuario.carregarPorEmail
                (AnimalDetalhesActivity.this,SharedPreferencesUtil
                        .lerPreferenciaString(AnimalDetalhesActivity.this, R.string.key_usuriao_logado));

        mAnimal = (Animal) getIntent().getExtras().get("animalzinhoSelecionado");
        Animal lAnimal = Animal.carregarPorId(AnimalDetalhesActivity.this, mAnimal.getId());
        if(lAnimal != null) {
            mAnimal = lAnimal;
        }
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                R.drawable.ic_add_favorite, R.color.icons));
        isFavorito();
        ImageView imagemAnimalDetalhes = (ImageView) findViewById(R.id.imagemDetalhesId);
        TextView nomeAnimal = (TextView) findViewById(R.id.nomeAnimalId);
        Button botaoAdotar = (Button) findViewById(R.id.buttonAdotarId);
        TextView racaAnimal = (TextView) findViewById(R.id.racaAnimalId);
        TextView tamanhoAnimal = (TextView) findViewById(R.id.tamanhoAnimalId);
        TextView corAnimal = (TextView) findViewById(R.id.corAnimalId);
        TextView idadeAnimal = (TextView) findViewById(R.id.idadeAnimalId);

        racaAnimal.setText(mAnimal.getRaca());
        if(mAnimal.getFoto() != null) {
            imagemAnimalDetalhes.setImageBitmap(ImagemUtil.converter(mAnimal.getFoto()));
        } else {
            imagemAnimalDetalhes.setImageResource(mAnimal.getImagem());
        }
        nomeAnimal.setText(mAnimal.getNome());
        tamanhoAnimal.setText(mAnimal.getTamanho());
        corAnimal.setText(mAnimal.getCor());
        idadeAnimal.setText(String.valueOf(mAnimal.getIdade()));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesUtil.lerPreferenciaString(AnimalDetalhesActivity.this, R.string.key_usuriao_logado) != null) {
                    Favorito lFavorito = new Favorito();
                    if (!mFavorito) {
                        mFavorito = true;
                        floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                                R.drawable.ic_add_favorite, R.color.favorite));
                        lFavorito.setAnimal(mAnimal);
                        lFavorito.setUsuario(mUsuario.getId());
                        Favorito.savarFavoritoBaseDados(AnimalDetalhesActivity.this, lFavorito);
                    } else {
                        mFavorito = false;
                        floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                                R.drawable.ic_add_favorite, R.color.icons));
                        revomeFavorito();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Efetue o login para ter acesso a essa funcionalidade", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean revomeFavorito() {
        boolean lRetorno = false;
        Favorito lFavorito = Favorito.carregarPorIdUsuarioIdAnial(AnimalDetalhesActivity.this, mUsuario.getId(), mAnimal.getId());
        if(lFavorito != null) {
            lRetorno = Favorito.remover(AnimalDetalhesActivity.this, lFavorito);
        }
        return lRetorno;
    }

    private void isFavorito() {
        if(SharedPreferencesUtil.lerPreferenciaString(AnimalDetalhesActivity.this, R.string.key_usuriao_logado) != null) {
            Favorito lFavorito = Favorito.carregarPorIdUsuarioIdAnial(AnimalDetalhesActivity.this, mUsuario.getId(), mAnimal.getId());
            if (lFavorito != null) {
                floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                        R.drawable.ic_add_favorite, R.color.favorite));
                mFavorito = true;
            } else {
                floatingActionButton.setImageDrawable(CoresUtil.alterarCorDrawableMenuItem(getApplication(),
                        R.drawable.ic_add_favorite, R.color.icons));
                mFavorito = false;
            }
        }
    }


}




