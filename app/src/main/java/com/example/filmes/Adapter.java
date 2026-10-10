package com.example.filmes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmes.Filme;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Filme> filmes;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public Adapter(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tTitulo.setText(filmes.get(position).getTitulo());
        holder.tGenero.setText(filmes.get(position).getGenero());
        holder.tAno.setText(filmes.get(position).getAno());
        holder.tDescricao.setText(filmes.get(position).getDescricao());
        Glide.with (holder.itemView.getContext()).load(filmes.get(position).getImagem()).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        if (this.filmes != null) {
            return this.filmes.size();
        }
        return 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tTitulo;
        TextView tGenero;
        TextView tAno;
        TextView tDescricao;
        ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tTitulo = itemView.findViewById(R.id.textTitulo);
            tGenero = itemView.findViewById(R.id.textGenero);
            tAno = itemView.findViewById(R.id.textAno);
            tDescricao = itemView.findViewById(R.id.textDescricao);
            imagem = itemView.findViewById(R.id.imagem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }
    }
}