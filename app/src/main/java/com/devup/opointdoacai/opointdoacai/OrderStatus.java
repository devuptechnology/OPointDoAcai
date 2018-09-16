package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import es.dmoral.toasty.Toasty;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;
    FirebaseAuth mAuth;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_orders);
        toolbar.setTitle("Pedidos");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OrderStatus.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Pedidos");
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String phone = mAuth.getCurrentUser().getPhoneNumber();

        if (Common.isConnectedToInternet(getBaseContext())) {
            loadOrders(phone);
        }else{
            Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void loadOrders(String phone) {

        Query getOrderByUser = requests.orderByChild("telefone")
                .equalTo(phone);

        FirebaseRecyclerOptions<Request> orderOptions = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(getOrderByUser, Request.class)
                .build();

       adapter  = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(orderOptions) {
           @Override
           protected void onBindViewHolder(@NonNull OrderViewHolder viewHolder, int position, @NonNull Request model) {
               viewHolder.txtOrderId.setText("Numero do Pedido: " + adapter.getRef(position).getKey());
               viewHolder.txtOrderStatus.setText("Status: " + Common.convertCodeToStatus(model.getStatus()));
               viewHolder.txtOrderAddress.setText("Endereço: " + model.getEndereco());
               viewHolder.txtOrderPhone.setText("Telefone: " + model.getTelefone());
           }

           @NonNull
           @Override
           public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.order_layout, parent, false);
               return new OrderViewHolder(itemView);
           }
       };

       adapter.startListening();
       recyclerView.setAdapter(adapter);

    }

}
