package br.com.edu.ifsc.ajinhocompatas.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.R;

import br.com.edu.ifsc.ajinhocompatas.view.Animal_detalhes;
import br.com.edu.ifsc.ajinhocompatas.vo.Animal;


/**
 * Created by keila on 26/10/2017.
 */

public class GridViewAdapterAnimais extends ArrayAdapter<Animal>{
    public GridViewAdapterAnimais(Context context, int resource, List<Animal> objects) {
        //praticamente um this do ArrayAdapter<Animal>
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;

        //se não tiver foto
        if (null == item) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.grid_item, null);
        }

        //pega a posição do item na lista Animal e coloca no animal
        final Animal animal = getItem(position);

        //cast da imagem, titulo e descrição do item do grid_item
        ImageView img = (ImageView) item.findViewById(R.id.imageView);
        TextView titulo = (TextView) item.findViewById(R.id.tituloId);
        TextView descricao = (TextView) item.findViewById(R.id.descricaoId);

        img.setImageResource(animal.getImagem());
        titulo.setText(animal.getNome());
        descricao.setText(animal.getDescricao());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passar dados para outra activity

            }

        });

        return item;
    }



}
