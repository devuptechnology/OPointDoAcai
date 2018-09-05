package com.devup.opointdoacai.opointdoacai;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_nome, txt_cart_complementos, txt_cart_preco;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_nome(TextView txt_cart_nome) {
        this.txt_cart_nome = txt_cart_nome;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        this.txt_cart_nome = itemView.findViewById(R.id.cart_list_nome);
        this.txt_cart_complementos = itemView.findViewById(R.id.cart_list_complementos);
        this.txt_cart_preco = itemView.findViewById(R.id.cart_list_preco);
    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Locale locale = new Locale("PT","BR");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
//        float price = Float.parseFloat(listData.get(position).getPreco());

        holder.txt_cart_nome.setText(listData.get(position).getQuantidade());
        holder.txt_cart_complementos.setText(listData.get(position).getComplementos());
        holder.txt_cart_preco.setText(listData.get(position).getPreco());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
