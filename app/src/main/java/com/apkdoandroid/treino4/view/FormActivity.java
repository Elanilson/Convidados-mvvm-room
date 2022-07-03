package com.apkdoandroid.treino4.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.apkdoandroid.treino4.R;
import com.apkdoandroid.treino4.constrants.ConvidadoConstrants;
import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.model.Resposta;
import com.apkdoandroid.treino4.viewmodel.FormViewModel;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private FormViewModel viewModel;
    private ViewHolder mViewHolder = new ViewHolder();
    private  int convidadoId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        viewModel = new ViewModelProvider(this).get(FormViewModel.class);

        mViewHolder.editNome = findViewById(R.id.editTexNome);
        mViewHolder.radioNConfirmado = findViewById(R.id.radioButtonNaoConfirmado);
        mViewHolder.radioPresente = findViewById(R.id.radioButtonPresente);
        mViewHolder.radioAusente = findViewById(R.id.radioButtonAusente);
        mViewHolder.btnSalvar = findViewById(R.id.buttonSalvar);

        setListner();
        setObserves();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            convidadoId = bundle.getInt("id");
            viewModel.carregarConvidado(convidadoId);

        }
    }

    private void setObserves() {
        viewModel.convidado.observe(this, new Observer<Convidado>() {
            @Override
            public void onChanged(Convidado convidado) {

                    mViewHolder.editNome.setText(convidado.getNome());
                    mViewHolder.radioNConfirmado.setChecked(convidado.getPresenca() == ConvidadoConstrants.CONFIRMACAO.NAO_CONFIRMADO);
                    mViewHolder.radioPresente.setChecked(convidado.getPresenca() == ConvidadoConstrants.CONFIRMACAO.PRESENTE);
                    mViewHolder.radioAusente.setChecked(convidado.getPresenca() == ConvidadoConstrants.CONFIRMACAO.AUSENTE);


            }
        });

        viewModel.respota.observe(this, new Observer<Resposta>() {
            @Override
            public void onChanged(Resposta resposta) {
                Toast.makeText(FormActivity.this, resposta.getMensagem(), Toast.LENGTH_SHORT).show();
                if(resposta.getStatus()){
                    finish();
                }
            }
        });
    }

    private void setListner() {
        mViewHolder.btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSalvar){

            salvar();

        }
    }

    private void salvar() {
        String nome = mViewHolder.editNome.getText().toString();
        int confirmacao = 0;
        if(mViewHolder.radioPresente.isChecked()){
            confirmacao = ConvidadoConstrants.CONFIRMACAO.PRESENTE;

        }else if(mViewHolder.radioAusente.isChecked()){
            confirmacao = ConvidadoConstrants.CONFIRMACAO.AUSENTE;

        }
        Convidado convidado = new Convidado(convidadoId,nome,confirmacao);

        viewModel.salvarConvidado(convidado);
    }

    private static final class ViewHolder{
        EditText editNome;
        RadioButton radioNConfirmado,radioPresente,radioAusente;
        Button btnSalvar;

    }
}