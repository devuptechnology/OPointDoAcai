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

public class Vitaminas extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    private CardView cvVita01, cvVita02, cvVita03, cvVita04, cvVita05;

    private float valorIndividual = 0.00f;
    private String valor = "";
    private String quantidade = "";
    private String complementos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitaminas);

        //Setando Orientação de Retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_vitaminas);
        toolbar.setTitle("Vitaminas");
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

        //Instanciando CardViews
        cvVita01 = findViewById(R.id.cv_vitaminas_01);cvVita02 = findViewById(R.id.cv_vitaminas_02);
        cvVita03 = findViewById(R.id.cv_vitaminas_03);cvVita04 = findViewById(R.id.cv_vitaminas_04);
        cvVita05 = findViewById(R.id.cv_vitaminas_05);

        cvVita01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.50f;
                quantidade = "Vitamina";
                complementos = "Côco com Sonho de Valsa";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Vitaminas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvVita02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.50f;
                quantidade = "Vitamina";
                complementos = "Limonada Suíça";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Vitaminas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvVita03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.50f;
                quantidade = "Vitamina";
                complementos = "Morango com Ninho";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Vitaminas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvVita04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.50f;
                quantidade = "Vitamina";
                complementos = "Vitamina de Açaí do Point";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Vitaminas.this, Cart.class);
                startActivity(intent);

            }
        });

        cvVita05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 08.00f;
                quantidade = "Vitamina";
                complementos = "Sensação";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Vitaminas.this, Cart.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
