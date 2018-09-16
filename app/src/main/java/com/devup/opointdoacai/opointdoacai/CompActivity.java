package com.devup.opointdoacai.opointdoacai;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.devup.opointdoacai.opointdoacai.Database.Database;
import com.google.protobuf.StringValue;

import android.app.Dialog;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class CompActivity extends AppCompatActivity {

    //Variaveis de Botões
    private FloatingActionButton floatingActionButton;

    //CheckBoxes
    private CheckBox cb_01, cb_02, cb_03, cb_04, cb_05, cb_06, cb_07, cb_08, cb_09, cb_10,  cb_11,  cb_12,  cb_13, cb_14, cb_15, cb_16,
            cb_17, cb_18, cb_19, cb_20, cb_21, cb_22, cb_23, cb_24, cb_25, cb_26, cb_27, cb_28, cb_29, cb_30, cb_31, cb_32, cb_33, cb_34,
            cb_35, cb_36, cb_37, cb_38, cb_39, cb_40, cb_41, cb_42, cb_43, cb_44;

    private float total = 0.00f;
    private float valorqnt = 0.00f;
    private String complementos = "";
    private String quantidade = "";
    private String valorTotal = "";

    Dialog mDialog;
    Button buttonConfirmar;
    Button buttonVoltar;
    TextView txtDialogValor;
    TextView txtDialogComp;
    TextView txtDialogQnt;

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_comp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 0.00f;
                quantidade = "";
                finish();

            }
        });

        //Instâncias
        cb_01 = findViewById(R.id.cb_01);cb_02 = findViewById(R.id.cb_02);cb_03 = findViewById(R.id.cb_03);
        cb_04 = findViewById(R.id.cb_04);cb_05 = findViewById(R.id.cb_05);cb_06 = findViewById(R.id.cb_06);
        cb_07 = findViewById(R.id.cb_07);cb_08 = findViewById(R.id.cb_08);cb_09 = findViewById(R.id.cb_09);
        cb_08 = findViewById(R.id.cb_08);cb_09 = findViewById(R.id.cb_09);cb_10 = findViewById(R.id.cb_10);
        cb_11 = findViewById(R.id.cb_11);cb_12 = findViewById(R.id.cb_12);cb_13 = findViewById(R.id.cb_13);
        cb_14 = findViewById(R.id.cb_14);cb_15 = findViewById(R.id.cb_15);cb_16 = findViewById(R.id.cb_16);
        cb_17 = findViewById(R.id.cb_17);cb_18 = findViewById(R.id.cb_18);cb_19 = findViewById(R.id.cb_19);
        cb_20 = findViewById(R.id.cb_20);cb_21 = findViewById(R.id.cb_21);cb_22 = findViewById(R.id.cb_22);
        cb_23 = findViewById(R.id.cb_23);cb_24 = findViewById(R.id.cb_24);cb_25 = findViewById(R.id.cb_25);
        cb_26 = findViewById(R.id.cb_26);cb_27 = findViewById(R.id.cb_27);cb_28 = findViewById(R.id.cb_28);
        cb_29 = findViewById(R.id.cb_29);cb_30 = findViewById(R.id.cb_30);cb_31 = findViewById(R.id.cb_31);
        cb_32 = findViewById(R.id.cb_32);cb_33 = findViewById(R.id.cb_33);cb_34 = findViewById(R.id.cb_34);
        cb_35 = findViewById(R.id.cb_35);cb_36 = findViewById(R.id.cb_36);cb_37 = findViewById(R.id.cb_37);
        cb_38 = findViewById(R.id.cb_38);cb_39 = findViewById(R.id.cb_39);cb_40 = findViewById(R.id.cb_40);
        cb_41 = findViewById(R.id.cb_41);cb_42 = findViewById(R.id.cb_42);cb_43 = findViewById(R.id.cb_43);
        cb_44 = findViewById(R.id.cb_44);

        //Instanciando o FloatingActionButton
        floatingActionButton = findViewById(R.id.fab_cart_comp);

        //Evento OnClick do FloatingActionButton
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mousses

                if (cb_01.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Abacaxi";

                }

                if (cb_02.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Framboesa";

                }

                if (cb_03.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Graviola";

                }

                if (cb_04.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Jabuticaba";

                }

                if (cb_05.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Limão";

                }

                if (cb_06.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Maracujá";

                }

                if (cb_07.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Chantilly";

                }

                if (cb_08.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Mousse de Uva";

                }

                //Frutas

                if (cb_09.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Abacaxi";

                }

                if (cb_10.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Banana";

                }

                if (cb_11.isChecked()){

                    total = total + 3.50f;
                    complementos = complementos + ", Kiwi";

                }

                if (cb_12.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Maçã";

                }

                if (cb_13.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Mamão";

                }

                if (cb_14.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Morango";

                }

                if (cb_15.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Pêssegos em Calda";

                }

                //Complementos

                if (cb_16.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Beijinho";

                }

                if (cb_17.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Brigadeiro";

                }

                if (cb_18.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Bis";

                }

                if (cb_19.isChecked()){

                    total = total + 3.00f;
                    complementos = complementos + ", Castanha";

                }

                if (cb_20.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Côco Ralado";

                }

                if (cb_21.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Confeti";

                }

                if (cb_22.isChecked()){

                    total = total + 3.50f;
                    complementos = complementos + ", Creme de Avelã";

                }

                if (cb_23.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Doce de Leite";

                }

                if (cb_24.isChecked()){

                    total = total + 1.50f;
                    complementos = complementos + ", Dadinho";

                }

                if (cb_25.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Gotas de Chocolate";

                }

                if (cb_26.isChecked()){

                    total = total + 1.50f;
                    complementos = complementos + ", Granola";

                }

                if (cb_27.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Granulado";

                }

                if (cb_28.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Iorgute";

                }

                if (cb_29.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Leite em Pó";

                }

                if (cb_30.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Leite Condensado";

                }

                if (cb_31.isChecked()){

                    total = total + 1.50f;
                    complementos = complementos + ", Mel";

                }

                if (cb_32.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Nescal Ball";

                }

                if (cb_33.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Neston";

                }

                if (cb_34.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Aveia";

                }

                if (cb_35.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Farinha Láctea";

                }

                if (cb_36.isChecked()){

                    total = total + 1.50f;
                    complementos = complementos + ", Ouro Branco";

                }

                if (cb_37.isChecked()){

                    total = total + 1.50f;
                    complementos = complementos + ", Sonho de Valsa";

                }

                if (cb_38.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Ovomaltine";

                }

                if (cb_39.isChecked()){

                    total = total + 2.00f;
                    complementos = complementos + ", Paçoca";

                }

                if (cb_40.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Space Ball";

                }

                if (cb_41.isChecked()){

                    total = total + 2.50f;
                    complementos = complementos + ", Sucrilhos";

                }

                //Coberturas

                if (cb_42.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Cobertura de Chocolate";

                }

                if (cb_43.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Cobertura de Caramelo";

                }

                if (cb_44.isChecked()){

                    total = total + 1.00f;
                    complementos = complementos + ", Cobertura de Morango";

                }

                //Recebendo dados da Sizes Activity
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                valorqnt = bundle.getFloat("value");
                quantidade = bundle.getString("qnt");

                total = valorqnt + total;

                String stringNew = "";

                if (complementos == ""){
                    stringNew = "Nenhum complemento selecionado.";
                }else{
                    stringNew = complementos.substring(2, complementos.length());
                }

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

                String vTotal = fmt.format(total);
                valorTotal = vTotal;

                ConfirmOrCancelDialog(valorTotal, stringNew, quantidade);

                total = 0.00f;
                complementos = "";
                quantidade = "";
            }
        });

        //Setando Orientação Padrão para a Screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void ConfirmOrCancelDialog(final String valorTotal, final String complementos, final String quantidade){

        mDialog = new Dialog(CompActivity.this);
        mDialog.setContentView(R.layout.dialog_confirm_cancel);
        mDialog.setTitle("Confirmar Pedido?");

        txtDialogQnt = mDialog.findViewById(R.id.txt_tipo_dialog);
        txtDialogQnt.setText(quantidade);

        Locale locale = new Locale("PT","BR");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtDialogValor = mDialog.findViewById(R.id.txt_valor_dialog);
        txtDialogValor.setText(fmt.format(total));

        txtDialogComp = mDialog.findViewById(R.id.txt_comp_dialog);
        txtDialogComp.setText(complementos);

        buttonConfirmar = mDialog.findViewById(R.id.card_dialog_btn_confirm);
        buttonVoltar = mDialog.findViewById(R.id.card_dialog_btn_cancel);

        buttonConfirmar.setEnabled(true);
        buttonVoltar.setEnabled(true);

        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("PT","BR");
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

               // new Database(getBaseContext()).cleanCart();

                new Database(getBaseContext()).addToCart(new Order(
                        quantidade,
                        complementos,
                        valorTotal
                ));

                Intent intent = new Intent(CompActivity.this, Cart.class);
                startActivity(intent);

            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.cancel();

            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }


    @Override
    public void onBackPressed() {
    }
}
