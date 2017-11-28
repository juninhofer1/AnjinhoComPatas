package br.com.edu.ifsc.anjinhocompatas.telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.modelos.Favorito;
import br.com.edu.ifsc.anjinhocompatas.modelos.Usuario;
import br.com.edu.ifsc.anjinhocompatas.telas.adapter.GridViewAdapterAnimaisRecycler;
import br.com.edu.ifsc.anjinhocompatas.telas.adapter.OnItemClickRecycler;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;

/**
 * Created by Wilson on 27/11/2017.
 */

public class FavoritosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private List<Animal> listAnimal;
    private GridViewAdapterAnimaisRecycler gridViewAdapter;
    private RecyclerView gridView;
    private Usuario mUsuario;
    private LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        mUsuario = Usuario.carregarPorEmail
                (FavoritosActivity.this, SharedPreferencesUtil
                        .lerPreferenciaString(FavoritosActivity.this, R.string.key_usuriao_logado));

        this.linearLayout = (LinearLayout) findViewById(R.id.layout_nenhum_favorito);
        this.imageView = (ImageView) findViewById(R.id.logo);
        this.imageView.setVisibility(View.GONE);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
//        getSupportActionBar().setLogo(R.drawable.icon_app_s);
        getSupportActionBar().setTitle("Favoritos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //cast
        gridView = (RecyclerView) findViewById(R.id.gridView);
    }

    //    Evento de click, o que será feito quando clckar em um item da lista
    OnItemClickRecycler onItemClickRecycler = new OnItemClickRecycler() {
        @Override
        public void onItemClick(View v, int position) {
//            Pega Item na lista
            Animal lAnimal = listAnimal.get(position);
//            Cria intente para abrir a activity
            Intent intent = new Intent(FavoritosActivity.this, AnimalDetalhesActivity.class);
//            passa como parametro uma animal para a activity que vai abrir
            lAnimal.setFoto("");
            intent.putExtra("animalzinhoSelecionado", lAnimal);
//            starta a activity
            startActivity(intent);
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    //Lista com as fotos dos dogs
    public void getList() {
//      cria nova array list
        listAnimal = new ArrayList<>();

        //adiciona na lista
        listAnimal = Favorito.carregarFavoritos(FavoritosActivity.this, mUsuario.getId());

        if(!listAnimal.isEmpty()) {
            linearLayout.setVisibility(View.GONE);
        } else{
            linearLayout.setVisibility(View.VISIBLE);
        }

        if(gridView != null) {
//        cria novo adapter (context(telinha), modelo telinha,conteúdo da telinha, inteface para click na tela)
            gridViewAdapter = new GridViewAdapterAnimaisRecycler(FavoritosActivity.this, R.layout.grid_item, listAnimal, onItemClickRecycler);

//        Define se o recyclerview será uma lista ou uma grid 2 para definir as colunas
            gridView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FavoritosActivity.this,2);
//        define a configuração acima em nosso recycler
            gridView.setLayoutManager(layoutManager);

            //mostra, seta a telinha do grid
            gridView.setAdapter(gridViewAdapter);
            gridView.invalidate();
        }
    }
}
