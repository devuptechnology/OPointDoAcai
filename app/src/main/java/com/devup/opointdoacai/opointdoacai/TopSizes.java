package com.devup.opointdoacai.opointdoacai;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devup.opointdoacai.opointdoacai.Database.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class TopSizes extends AppCompatActivity {

    private CardView opc1, opc2, opc3;
    private TextView txt01, txt02, txt03, txtTopOpc, txtTopName, txtTopComp;

    private String complementos = "";
    private String quantidade = "";
    private String top = "";
    private float valor300 = 0.00f;
    private float valor500 = 0.00f;
    private float valor700 = 0.00f;
    private String valor = "";

    private android.support.v7.widget.Toolbar toolbar;

    private Dialog mDialog;
    private Button buttonConfirmar;
    private Button buttonVoltar;
    private TextView txtDialogValor;
    private TextView txtDialogComp;
    private TextView txtDialogQnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sizes);

        //Setando Orientação de Retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_top_sizes);
        toolbar.setTitle("Escolha o Tamanho do Copo");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt01.setText("Carregando...");
                txt02.setText("Carregando...");
                txt03.setText("Carregando...");
                txtTopOpc.setText("00");
                txtTopName.setText("Carregando...");
                txtTopComp.setText("Carregando...");

                Intent intent = new Intent(TopSizes.this, TopTen.class);
                startActivity(intent);
                finish();

            }
        });

        opc1 = findViewById(R.id.qnt_300);
        opc2 = findViewById(R.id.qnt_500);
        opc3 = findViewById(R.id.qnt_700);

        txt01 = findViewById(R.id.preco_300);
        txt02 = findViewById(R.id.preco_500);
        txt03 = findViewById(R.id.preco_700);
        txtTopOpc = findViewById(R.id.txt_selected_top_number);
        txtTopName = findViewById(R.id.txt_top_name_selected);
        txtTopComp = findViewById(R.id.txt_top_complementos_selected);

        //Recebendo dados da TopTen Activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        quantidade = bundle.getString("quantidade");
        complementos = bundle.getString("complementos");
        top = bundle.getString("top");
        valor300 = bundle.getFloat("valor300");
        valor500 = bundle.getFloat("valor500");
        valor700 = bundle.getFloat("valor700");

        Locale locale = new Locale("PT","BR");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txt01.setText(fmt.format(valor300));
        txt02.setText(fmt.format(valor500));
        txt03.setText(fmt.format(valor700));
        txtTopOpc.setText(top);
        txtTopName.setText(quantidade);
        txtTopComp.setText(complementos);

        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valor300);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(TopSizes.this, Cart.class);
                startActivity(intent);

            }
        });

        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valor500);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(TopSizes.this, Cart.class);
                startActivity(intent);

            }
        });

        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                valor = fmt.format(valor700);

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valor
                ));

                Intent intent = new Intent(TopSizes.this, Cart.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
