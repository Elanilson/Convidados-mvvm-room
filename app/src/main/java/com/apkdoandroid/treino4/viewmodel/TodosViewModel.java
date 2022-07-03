package com.apkdoandroid.treino4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apkdoandroid.treino4.constrants.ConvidadoConstrants;
import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.model.Resposta;
import com.apkdoandroid.treino4.repositorio.Repositorio;

import java.util.List;

public class TodosViewModel extends AndroidViewModel {
    private Repositorio repositorio;

    public TodosViewModel(@NonNull Application application) {
        super(application);
        repositorio = new Repositorio(application.getApplicationContext());
    }

    private MutableLiveData<List<Convidado>> mConvidados = new MutableLiveData<>();
    public LiveData<List<Convidado>> convidados = mConvidados;

    private MutableLiveData<Resposta> mRespota = new MutableLiveData<>();
    public LiveData<Resposta> resposta = mRespota;


    public void ListarConvidados(int filter){
        if(filter == ConvidadoConstrants.CONFIRMACAO.NAO_CONFIRMADO){
            mConvidados.setValue(repositorio.getAll());
        }else if(filter == ConvidadoConstrants.CONFIRMACAO.PRESENTE){
            mConvidados.setValue(repositorio.getPresentes());
        }else if(filter == ConvidadoConstrants.CONFIRMACAO.AUSENTE){
            mConvidados.setValue(repositorio.getAusentes());
        }
    }

    public void deletar(int id){
        if(repositorio.deletar(id)){
            mRespota.setValue(new Resposta("Removido com sucesso"));
        }else{
            mRespota.setValue(new Resposta(false, "Error inesperado"));
        }
    }



}