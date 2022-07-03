package com.apkdoandroid.treino4.repositorio;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.apkdoandroid.treino4.model.Convidado;

import java.util.List;

@Dao
public interface ConvidadoDao {
    @Insert
    Long  insert(Convidado convidado);
    @Update
    int update(Convidado convidado);
    @Delete
    int  deletar (Convidado convidado);

    @Query("SELECT * FROM convidados where id = :id")
    Convidado  carregarConvidado(int id);

    @Query("SELECT * FROM convidados")
    List<Convidado> getLista();

    @Query("SELECT * FROM convidados where presenca = :precensa")
    List<Convidado> getPresentes(int precensa);
}
