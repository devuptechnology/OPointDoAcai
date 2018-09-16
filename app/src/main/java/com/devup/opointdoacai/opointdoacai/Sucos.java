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

public class Sucos extends AppCompatActivity {

    private CardView cv1, cv2, cv3, cv4, cv5, cv6, cv7, cv8, cv9, cv10, cv11, cv12, cv13, cv14, cv15, cv16, cv17, cv18;

    private android.support.v7.widget.Toolbar toolbar;

    private float valorIndividual = 0.00f;
    private String valor = "";
    private String quantidade = "";
    private String complementos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucos);

        //Setando Orientação de Retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_sucos);
        toolbar.setTitle("Sucos");
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

        cv1 = findViewById(R.id.cv_sucos_leite_01);cv2 = findViewById(R.id.cv_sucos_leite_02);cv3 = findViewById(R.id.cv_sucos_leite_03);
        cv4 = findViewById(R.id.cv_sucos_leite_04);cv5 = findViewById(R.id.cv_sucos_leite_05);cv6 = findViewById(R.id.cv_sucos_leite_06);
        cv7 = findViewById(R.id.cv_sucos_leite_07);cv8 = findViewById(R.id.cv_sucos_leite_08);cv9 = findViewById(R.id.cv_sucos_leite_09);
        cv10 = findViewById(R.id.cv_sucos_agua_10);cv11 = findViewById(R.id.cv_sucos_agua_11);cv12 = findViewById(R.id.cv_sucos_agua_12);
        cv13 = findViewById(R.id.cv_sucos_agua_13);cv14 = findViewById(R.id.cv_sucos_agua_14);cv15 = findViewById(R.id.cv_sucos_agua_15);
        cv16 = findViewById(R.id.cv_sucos_agua_16);cv17 = findViewById(R.id.cv_sucos_agua_17);cv18 = findViewById(R.id.cv_sucos_agua_18);


        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Abacaxi";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Abacaxi com Hortelã";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Acerola";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Caju";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Côco";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Laranja";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Limão";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Maracujá";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 07.00f;
                quantidade = "Suco de Morango";
                complementos = "Com Leite";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Abacaxi";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Abacaxi com Hortelã";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Acerola";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Caju";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Côco";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Laranja";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Limão";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Maracujá";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });

        cv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorIndividual = 06.00f;
                quantidade = "Suco de Morango";
                complementos = "Com Água";

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valorIndividual);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(Sucos.this, Cart.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
