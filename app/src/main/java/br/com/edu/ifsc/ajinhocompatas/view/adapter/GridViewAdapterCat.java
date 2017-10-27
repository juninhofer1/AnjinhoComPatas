package br.com.edu.ifsc.ajinhocompatas.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.R;
import br.com.edu.ifsc.ajinhocompatas.view.fragment.AnimalGato;

/**
 * Created by keila on 25/10/2017.
 */

public class GridViewAdapterCat extends ArrayAdapter<AnimalGato> {
    public GridViewAdapterCat(Context context, int resource, List<AnimalGato> objects) {
        //praticamente um this dp ArrayAdapter<AnimalGato>
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;

        //se não tiver foto
        if (null == item) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.grid_item, null);
        }

        //pega a posição do item e coloca no animal
        AnimalGato animalGato = getItem(position);

        //cast da imagem, titulo e descrição do item
        ImageView img = (ImageView) item.findViewById(R.id.imageView);
        TextView titulo = (TextView) item.findViewById(R.id.tituloId);
        TextView descricao = (TextView) item.findViewById(R.id.descricaoId);


        img.setImageResource(animalGato.getImagemGatoId());
        titulo.setText(animalGato.getTitle());
        descricao.setText(animalGato.getDescription());

        return item;

    }
}