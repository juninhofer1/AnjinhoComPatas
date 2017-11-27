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

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.telas.adapter.GridViewAdapterAnimaisRecycler;
import br.com.edu.ifsc.anjinhocompatas.telas.adapter.OnItemClickRecycler;

/**
 * Created by Wilson on 27/11/2017.
 */

public class FavoritosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private List<Animal> listAnimal;
    private GridViewAdapterAnimaisRecycler gridViewAdapter;
    private RecyclerView gridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        this.imageView = (ImageView) findViewById(R.id.logo);
        this.imageView.setVisibility(View.GONE);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
//        getSupportActionBar().setLogo(R.drawable.icon_app_s);
        getSupportActionBar().setTitle("Favoritos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getList();
        //cast
        gridView = (RecyclerView) findViewById(R.id.gridView);

        //cria novo adapter (context(telinha), modelo telinha,conteúdo da telinha, inteface para click na tela)
        gridViewAdapter = new GridViewAdapterAnimaisRecycler(FavoritosActivity.this, R.layout.grid_item, listAnimal, onItemClickRecycler);

//        Define se o recyclerview será uma lista ou uma grid 2 para definir as colunas
        gridView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavoritosActivity.this);
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

    public void getList() {
        listAnimal = new ArrayList<>();
        listAnimal = Animal.carregarTodosAnimais(FavoritosActivity.this);
    }
}
