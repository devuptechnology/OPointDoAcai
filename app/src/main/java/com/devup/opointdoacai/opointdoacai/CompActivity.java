package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class CompActivity extends AppCompatActivity {

    //Variáveis de Preços
    private double valorCopo;

    //Variáveis RecyclerView With Cardview and CheckBox
    StringBuffer moussesBuffer = null;
    MoussesAdapter moussesAdapter;

    //Variaveis de Botões
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp);

        //Instanciando Adapter
        moussesAdapter = new MoussesAdapter(this, getMousses());

        //Instanciando o FloatingActionButton
        floatingActionButton = findViewById(R.id.fab_cart_comp);

        //Evento OnClick do FloatingActionButton
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moussesBuffer = new StringBuffer();

                for(Mousses mousses : moussesAdapter.checkedMousses){

                    moussesBuffer.append(mousses.getValorMousse());
                    moussesBuffer.append("\n");

                }

                if(moussesAdapter.checkedMousses.size() > 0){

                    Toasty.normal(CompActivity.this, moussesBuffer.toString(), Toast.LENGTH_LONG).show();

                }else{

                    Toasty.normal(CompActivity.this, "Nenhum Complemento Selecionado", Toast.LENGTH_LONG).show();

                }

            }
        });

        //Recebendo valor do Copo
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        valorCopo = bundle.getDouble("valorcopo");

        //RecyclerView Mousses
        RecyclerView recyclerView = findViewById(R.id.recycler_view_mousses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moussesAdapter);

        //Setando Orientação Padrão para a Screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private ArrayList<Mousses> getMousses(){

        ArrayList<Mousses> moussesArrayList = new ArrayList<>();

        Mousses mousses = new Mousses("Abacaxi", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Framboesa", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Graviola", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Jabuticaba", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Limão", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Maracujá", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Morango", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Chantilly", "3,00");
        moussesArrayList.add(mousses);

        mousses = new Mousses("Uva", "3,00");
        moussesArrayList.add(mousses);

        return moussesArrayList;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        valorCopo = 0.0;
        finish();

    }
}
