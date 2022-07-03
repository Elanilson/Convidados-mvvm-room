package com.apkdoandroid.treino4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "convidados")
public class Convidado {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "presenca")
    private int presenca;


    public Convidado() {
    }

    public Convidado(String nome, int presenca) {
        this.nome = nome;
        this.presenca = presenca;
    }

    public Convidado(int id, String nome, int presenca) {
        this.id = id;
        this.nome = nome;
        this.presenca = presenca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPresenca() {
        return presenca;
    }

    public void setPresenca(int presenca) {
        this.presenca = presenca;
    }
}
