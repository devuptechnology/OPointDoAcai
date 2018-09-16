package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.devup.opointdoacai.opointdoacai.Database.Database;

import java.text.NumberFormat;
import java.util.Locale;

public class SaladaFrutas extends AppCompatActivity {

    private CardView cvs1, cvs2, cvs3, cvs4;

    private android.support.v7.widget.Toolbar toolbar;

    private float valorIndividual = 0.00f;
    private String valor = "";
    private String quantidade = "";
    private String complementos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salada_frutas);

        //Setando Orientação de Retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_salada_frutas);
        toolbar.setTitle("Salada de Frutas");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 00.00f;
                quantidade = "";
                complementos = "";
                finish();

            }
        });

        cvs1 = findViewById(R.id.cobertura_01);
        cvs2 = findViewById(R.id.cobertura_02);
        cvs3 = findViewById(R.id.cobertura_03);
        cvs4 = findViewById(R.id.cobertura_04);

        cvs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 10.00f;
                quantidade = "Salada de Frutas";
                complementos = "Cobertura de Suco de Laranja";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(SaladaFrutas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 10.00f;
                quantidade = "Salada de Frutas";
                complementos = "Cobertura de Leite Condensado";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(SaladaFrutas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 10.00f;
                quantidade = "Salada de Frutas";
                complementos = "Cobertura de Iorgute";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(SaladaFrutas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 10.00f;
                quantidade = "Salada de Frutas";
                complementos = "Cobertura de Mel";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(SaladaFrutas.this, Cart.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
