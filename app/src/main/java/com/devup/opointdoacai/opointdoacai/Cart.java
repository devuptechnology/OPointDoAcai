package com.devup.opointdoacai.opointdoacai;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devup.opointdoacai.opointdoacai.Database.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotal;
    Button btnConfirmarPedido;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        //Firebase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Pedidos");

        //Init
        recyclerView = findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotal = findViewById(R.id.total);
        btnConfirmarPedido = findViewById(R.id.btn_confirmar_pedido);

        loadListPedidos();

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callDialogAdress();

            }
        });

        //Setando Orientação Padrão para a Screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
    }

    private void callDialogAdress() {



    }

    private void loadListPedidos() {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        //Calculando Preco Total
        float total = 0;
        String firstString = "";
        String secondString = "";
        String thirdString = "";
        String fourtyString = "";
        float aux = 0;
        for (Order order:cart){
            firstString = order.getPreco();
            secondString = firstString.replace("R","");
            thirdString = secondString.replace("$","");
            fourtyString = thirdString.replace(",", ".");
            aux = Float.parseFloat(fourtyString);
            total+=aux;
        }


        Locale locale = new Locale("PT","BR");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotal.setText(fmt.format(total));
    }
}
