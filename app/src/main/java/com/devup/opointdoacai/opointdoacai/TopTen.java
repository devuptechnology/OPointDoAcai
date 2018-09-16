package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class TopTen extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    private CardView cvTop01, cvTop02, cvTop03, cvTop04, cvTop05, cvTop06, cvTop07, cvTop08, cvTop09, cvTop10;

    private String complementos = "";
    private String quantidade = "";
    private String top = "";
    private float valor300 = 0.00f;
    private float valor500 = 0.00f;
    private float valor700 = 0.00f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_top);
        toolbar.setTitle("Top 10 do Point");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        //Instanciando CardViews
        cvTop01 = findViewById(R.id.cv_top_01);cvTop02 = findViewById(R.id.cv_top_02);cvTop03 = findViewById(R.id.cv_top_03);
        cvTop04 = findViewById(R.id.cv_top_04);cvTop05 = findViewById(R.id.cv_top_05);cvTop06 = findViewById(R.id.cv_top_06);
        cvTop07 = findViewById(R.id.cv_top_07);cvTop08 = findViewById(R.id.cv_top_08);cvTop09 = findViewById(R.id.cv_top_09);
        cvTop10 = findViewById(R.id.cv_top_10);


        cvTop01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 01 -Sabor do Point";
                complementos = "Banana, Morango, Leite Condensado e Leite em Pó";
                top = "01";
                valor300 = 11.00f;
                valor500 = 13.00f;
                valor700 = 16.00f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 02 - Mamão Nutritivo";
                complementos = "Mamão, Castanha, Mel e Mousse de Graviola";
                top = "02";
                valor300 = 13.00f;
                valor500 = 15.00f;
                valor700 = 18.00f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 03 - Pura Sensação";
                complementos = "Morango, Creme de Avelã, Mousse de Morango e Leite em Pó";
                top = "03";
                valor300 = 14.50f;
                valor500 = 16.50f;
                valor700 = 19.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 04 - Gostosura do Pêssego";
                complementos = "Pêssego em Calda, Paçoca, Ouro Branco e Leite em Pó";
                top = "04";
                valor300 = 11.00f;
                valor500 = 13.00f;
                valor700 = 16.00f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 05 - Delícia de Abacaxi";
                complementos = "Abacaxi em Pedaços, Côco Ralado, Leite Condensado e Leite em Pó";
                top = "05";
                valor300 = 10.50f;
                valor500 = 12.50f;
                valor700 = 15.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 06 - Banana Tropical";
                complementos = "Banana, Granola, Leite Condensado, Leite em Pó";
                top = "06";
                valor300 = 09.50f;
                valor500 = 11.50f;
                valor700 = 14.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 07 - Maçã do Deuses";
                complementos = "Maçã em Pedaços, Sucrilhos, Iorgute e Gotas de Chocolate";
                top = "07";
                valor300 = 11.50f;
                valor500 = 13.50f;
                valor700 = 16.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 08 - Maravilha de Kiwi";
                complementos = "Kiwi em pedaços, Neston, Leite Condensado e Ovomaltine";
                top = "08";
                valor300 = 13.00f;
                valor500 = 15.00f;
                valor700 = 18.00f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 09 - Doce Energia";
                complementos = "Kit Kat, Doce de Leite, Granulado e Côco Ralado";
                top = "09";
                valor300 = 11.50f;
                valor500 = 13.50f;
                valor700 = 16.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cvTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantidade = "Top 10 - Vita Power";
                complementos = "Banana, Maçã, Mamão e Aveia";
                top = "10";
                valor300 = 10.50f;
                valor500 = 12.50f;
                valor700 = 15.50f;

                Bundle bundle = new Bundle();
                bundle.putString("quantidade", quantidade);
                bundle.putString("complementos", complementos);
                bundle.putString("top", top);
                bundle.putFloat("valor300", valor300);
                bundle.putFloat("valor500", valor500);
                bundle.putFloat("valor700", valor700);
                Intent intent = new Intent(getApplicationContext(), TopSizes.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //Setando Orientação Padrão para a Screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}
