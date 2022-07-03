package com.apkdoandroid.treino4.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.treino4.R;
import com.apkdoandroid.treino4.constrants.ConvidadoConstrants;
import com.apkdoandroid.treino4.databinding.FragmentTodosBinding;
import com.apkdoandroid.treino4.model.Convidado;
import com.apkdoandroid.treino4.model.Resposta;
import com.apkdoandroid.treino4.view.ConvidadoAdapter;
import com.apkdoandroid.treino4.view.FormActivity;
import com.apkdoandroid.treino4.view.listners.OnListClick;
import com.apkdoandroid.treino4.viewmodel.TodosViewModel;

import java.util.List;


public class TodosFragment extends Fragment {

    private ViewHolder viewHolder = new ViewHolder();
    private ConvidadoAdapter adapter = new ConvidadoAdapter();
    private TodosViewModel viewModel;
    private FragmentTodosBinding binding;
    private int filter = 0 ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(TodosViewModel.class);

        binding = FragmentTodosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewHolder.recyclerView = root.findViewById(R.id.recyclerViewAll);
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        viewHolder.recyclerView.setAdapter(adapter);

        OnListClick onListClick = new OnListClick() {
            @Override
            public void onClick(int id) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",id);

                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onDelete(int id) {
                viewModel.deletar(id);
                viewModel.ListarConvidados(filter);

            }
        };

        adapter.attackOnListClick(onListClick);

        if(getArguments() != null){
            filter = getArguments().getInt(ConvidadoConstrants.FILTER);
        }
        
        observes();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.ListarConvidados(filter);
    }

    private void observes() {
        viewModel.convidados.observe(getViewLifecycleOwner(), new Observer<List<Convidado>>() {
            @Override
            public void onChanged(List<Convidado> convidados) {
                adapter.attackConvidados(convidados);
            }
        });

        viewModel.resposta.observe(getViewLifecycleOwner(), new Observer<Resposta>() {
            @Override
            public void onChanged(Resposta resposta) {
                Toast.makeText(getActivity(), resposta.getMensagem(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class ViewHolder{
        RecyclerView recyclerView;
    }


}