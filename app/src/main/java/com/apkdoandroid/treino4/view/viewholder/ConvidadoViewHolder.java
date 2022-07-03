package com.apkdoandroid.treino4.view.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.treino4.R;
import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.view.listners.OnListClick;

public class ConvidadoViewHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private Context context;

    public ConvidadoViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.textView4Nome);
        context = itemView.getContext();
    }

    public void bind(Convidado convidado, OnListClick onListClick){
        nome.setText(convidado.getNome());
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListClick.onClick(convidado.getId());
            }
        });

        nome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Remoção de convidado")
                        .setMessage("Deseja realmente remover ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onListClick.onDelete(convidado.getId());
                            }
                        })
                        .setNeutralButton("Não",null)
                        .show();
                return false;
            }
        });
    }
}
