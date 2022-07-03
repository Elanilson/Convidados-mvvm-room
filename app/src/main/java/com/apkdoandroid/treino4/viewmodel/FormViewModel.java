package com.apkdoandroid.treino4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.model.Resposta;
import com.apkdoandroid.treino4.repositorio.Repositorio;

public class FormViewModel extends AndroidViewModel {
    private Repositorio repositorio;

    private MutableLiveData<Convidado> mConvidado = new MutableLiveData<>();
    public LiveData<Convidado> convidado = mConvidado;

    private MutableLiveData<Resposta> mRespota = new MutableLiveData<>();
    public LiveData<Resposta> respota = mRespota;

    public FormViewModel(@NonNull Application application) {
        super(application);
        repositorio = new Repositorio(application.getApplicationContext());
    }

    public void salvarConvidado(Convidado convidado){
        if("".equals(convidado.getNome() )|| " ".equals(convidado.getNome())){
            mRespota.setValue(new Resposta(false,"Nome obrigatorio"));
            return;
        }

        if(convidado.getId() == 0){
            if(repositorio.insert(convidado)){
                mRespota.setValue(new Resposta("Convidado inserido com sucesso"));
            }else {
                mRespota.setValue(new Resposta(false,"Error inesperado"));
            }
        }else{
            if(repositorio.update(convidado)){
                mRespota.setValue(new Resposta("Convidado atualizado com sucesso"));
            }else{
                mRespota.setValue(new Resposta(false,"Erro inesperado"));
            }
        }
    }

    public void carregarConvidado(int id){
        mConvidado.setValue(repositorio.carregarConvidado(id));
    }
}
