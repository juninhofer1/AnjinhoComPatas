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
import br.com.edu.ifsc.ajinhocompatas.view.fragment.AnimalCao;

/**
 * Created by keila on 26/10/2017.
 */

public class GridViewAdapterDog extends ArrayAdapter<AnimalCao>{
    public GridViewAdapterDog(Context context, int resource, List<AnimalCao> objects) {

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
        AnimalCao animalCao = getItem(position);

        //cast da imagem, titulo e descrição do item do grid_item
        ImageView imgDog = (ImageView) item.findViewById(R.id.imageView);
        TextView tituloDog = (TextView) item.findViewById(R.id.tituloId);
        TextView descricaoDog = (TextView) item.findViewById(R.id.descricaoId);


        imgDog.setImageResource(animalCao.getImagemCaoId());
        tituloDog.setText(animalCao.getTitle());
        descricaoDog.setText(animalCao.getDescription());

        return item;

    }

}
