package com.apkdoandroid.treino4.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.apkdoandroid.treino4.constrants.ConvidadoConstrants;
import com.apkdoandroid.treino4.constrants.DatabaseConstraints;
import com.apkdoandroid.treino4.model.Convidado;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private ConvidadoDao convidadoDao;

    public Repositorio(Context context) {
        BancoRoom  db = BancoRoom.getInstance(context);
        convidadoDao = db.convidadoDao();
    }

    public List<Convidado> getAll(){
        return this.convidadoDao.getLista();
    }
    public List<Convidado> getPresentes(){
        return this.convidadoDao.getPresentes(ConvidadoConstrants.CONFIRMACAO.PRESENTE);
    }
    public List<Convidado> getAusentes(){
        return this.convidadoDao.getPresentes(ConvidadoConstrants.CONFIRMACAO.AUSENTE);
    }

    public Convidado carregarConvidado(int id) {
        return this.convidadoDao.carregarConvidado(id);
    }

    public Boolean insert(Convidado convidado){
        return this.convidadoDao.insert(convidado) > 0;
    }

    public Boolean update(Convidado convidado){
        return this.convidadoDao.update(convidado) > 0;
    }

    public Boolean deletar (int id){
        Convidado convidado = carregarConvidado(id);
        return this.convidadoDao.deletar(convidado) > 0 ;
    }
}
