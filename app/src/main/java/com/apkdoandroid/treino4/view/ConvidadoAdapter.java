package com.apkdoandroid.treino4.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.treino4.R;
import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.view.listners.OnListClick;
import com.apkdoandroid.treino4.view.viewholder.ConvidadoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ConvidadoAdapter extends RecyclerView.Adapter<ConvidadoViewHolder> {

    private List<Convidado> convidados = new ArrayList<>();
    private OnListClick onListClick;
    @NonNull
    @Override
    public ConvidadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new ConvidadoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ConvidadoViewHolder holder, int position) {

        holder.bind(convidados.get(position),onListClick );

    }

    @Override
    public int getItemCount() {
        return convidados.size();
    }

    public void attackConvidados(List<Convidado> convidados){
        this.convidados = convidados;
        notifyDataSetChanged();
    }

    public void attackOnListClick (OnListClick onListClick){
        this.onListClick = onListClick;
    }
}
