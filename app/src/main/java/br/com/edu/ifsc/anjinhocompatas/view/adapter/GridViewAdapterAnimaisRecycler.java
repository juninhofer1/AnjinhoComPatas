package br.com.edu.ifsc.anjinhocompatas.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.vo.Animal;

/**
 * Created by keila on 26/10/2017.
 */

public class GridViewAdapterAnimaisRecycler extends RecyclerView.Adapter<GridViewAdapterAnimaisRecycler.ViewHolder> {
    private List<Animal> mAnimais;
    private int resource;
    private Context mContext;
    private OnItemClickRecycler onItemListerner;

    public GridViewAdapterAnimaisRecycler(Context aContext,int resouces, List<Animal> aAnimais, OnItemClickRecycler listener) {
        this.mAnimais = aAnimais;
        this.mContext = aContext;
        this.resource = resouces;
        this.onItemListerner = listener;
    }

    @Override
    public GridViewAdapterAnimaisRecycler.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int ite = i;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListerner.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(GridViewAdapterAnimaisRecycler.ViewHolder viewHolder, int i) {
        viewHolder.nome.setText(mAnimais.get(i).getNome());
        viewHolder.descricao.setText(mAnimais.get(i).getDescricao());
        viewHolder.img.setImageResource(mAnimais.get(i).getImagem());
    }

    @Override
    public int getItemCount() {
        return mAnimais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView nome;
        private TextView descricao;


        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.imageView);
            nome = (TextView) view.findViewById(R.id.tituloId);
            descricao = (TextView) view.findViewById(R.id.descricaoId);
        }
    }

}
