package com.devup.opointdoacai.opointdoacai;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MoussesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nomeMousse;
    TextView valorMousse;
    CheckBox checkBoxMousse;

    ItemClickListenerMousses itemClickListenerMousses;

    public MoussesHolder(View itemView){
        super(itemView);

        nomeMousse = itemView.findViewById(R.id.txt_mousse_name);
        valorMousse = itemView.findViewById(R.id.txt_mousse_price);
        checkBoxMousse = itemView.findViewById(R.id.checkbox_mousses);
        
        checkBoxMousse.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListenerMousses itemClickListener){
        
        this.itemClickListenerMousses = itemClickListener;
        
    }

    @Override
    public void onClick(View v) {
        
        this.itemClickListenerMousses.onItemClick(v, getLayoutPosition());
        
    }
}
