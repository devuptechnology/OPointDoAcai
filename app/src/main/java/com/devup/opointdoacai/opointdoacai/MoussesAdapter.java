package com.devup.opointdoacai.opointdoacai;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;


public class MoussesAdapter extends RecyclerView.Adapter<MoussesHolder> {

    Context context;
    ArrayList<Mousses> moussesArrayList;
    ArrayList<Mousses> checkedMousses = new ArrayList<>();

    public MoussesAdapter(Context context, ArrayList<Mousses> moussesArrayList) {
        this.context = context;
        this.moussesArrayList = moussesArrayList;
    }

    @Override
    public MoussesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_mousses, null);
        MoussesHolder moussesHolder = new MoussesHolder(view);
        return moussesHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MoussesHolder holder, int position) {

        holder.nomeMousse.setText(moussesArrayList.get(position).getNomeMousse());
        holder.valorMousse.setText(moussesArrayList.get(position).getValorMousse());

        holder.setItemClickListener(new ItemClickListenerMousses() {
            @Override
            public void onItemClick(View view, int position) {
                CheckBox checkBox = (CheckBox) view;

                //Eventos
                if(checkBox.isChecked()){

                    checkedMousses.add(moussesArrayList.get(position));

                }else if(!checkBox.isChecked()){

                    checkedMousses.remove(moussesArrayList.get(position));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return moussesArrayList.size();
    }
}
