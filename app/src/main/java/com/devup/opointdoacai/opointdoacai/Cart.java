package com.devup.opointdoacai.opointdoacai;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devup.opointdoacai.opointdoacai.Database.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

import es.dmoral.toasty.Toasty;

public class Cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotal;
    Button btnConfirmarPedido;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    Dialog adressDialog;
    Button adressDialogBtn;
    EditText adressRua;
    EditText adressNum;
    EditText adressBairro;
    EditText etZipCode;
    Spinner spinnerCidade;

    String cidadeSelected;

    float total = 0;

    private FirebaseAuth mAuth;

    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Pedidos");
        mAuth = FirebaseAuth.getInstance();

        //Init
        recyclerView = findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotal = findViewById(R.id.total);
        btnConfirmarPedido = findViewById(R.id.btn_confirmar_pedido);

        //loadListPedidos(total);

        final float secTotal = loadListPedidos(total);

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(secTotal == 0){

                    Toasty.error(Cart.this, "Carrinho vazio", Toast.LENGTH_SHORT).show();

                }else{

                    callDialogAdress();

                }

            }
        });



        //Setando Orientação Padrão para a Screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
    }



    private void callDialogAdress() {

        adressDialog = new Dialog(Cart.this);
        adressDialog.setContentView(R.layout.dialog_adress);
        adressDialog.setTitle("");

        adressDialogBtn = adressDialog.findViewById(R.id.btn_continuar_dialog_adress);
        adressRua = adressDialog.findViewById(R.id.edt_rua);
        adressNum = adressDialog.findViewById(R.id.edt_num);
        adressBairro = adressDialog.findViewById(R.id.edt_bairro);
        etZipCode = adressDialog.findViewById(R.id.edt_cep);
        spinnerCidade = adressDialog.findViewById(R.id.spinner_cidade);

        etZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    adressRua.setEnabled(false);
                    adressBairro.setEnabled(false);
                    spinnerCidade.setEnabled(false);

                    if (s.length() == 0){
                        adressRua.setEnabled(true);
                        adressBairro.setEnabled(true);
                        spinnerCidade.setEnabled(true);

                        adressRua.setText("");
                        adressBairro.setText("");
                        spinnerCidade.setSelection(0);

                    }

            }
        });

        etZipCode.addTextChangedListener(new ZipCodeListener(this));

        util = new Util(this,
                R.id.edt_rua,
                R.id.edt_bairro);



        spinnerCidade = adressDialog.findViewById(R.id.spinner_cidade);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cidades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidade.setAdapter(adapter);

        spinnerCidade.setOnItemSelectedListener(this);

        adressDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(adressRua.getText()) || TextUtils.isEmpty(adressNum.getText()) || TextUtils.isEmpty(adressBairro.getText())){

                    Toasty.error(Cart.this, "Por favor, preencha com todos os dados", Toast.LENGTH_SHORT).show();

                }else{

                    new Database(getBaseContext()).cleanCart();

                    Toasty.success(Cart.this, "Pedido efetuado com Sucesso!", Toast.LENGTH_SHORT).show();

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    String cep = etZipCode.getText().toString();
                    String rua = adressRua.getText().toString();
                    String num = adressNum.getText().toString();
                    String bairro = adressBairro.getText().toString();


                    String cidade = String.valueOf(spinnerCidade.getSelectedItem());
                    String endereco = rua + ", " + num + ", " + bairro + ", " + cep + ", " + cidade + " - SP";

                    Toasty.success(Cart.this, endereco, Toast.LENGTH_SHORT).show();
                    //Request request = new Request(
                            //firebaseUser.getPhoneNumber(),
                            //firebaseUser.getDisplayName(),
                           // endereco,
                           // txtTotal.getText().toString(),
                          //  cart
                   // );

                    //Intent intent = new Intent(Cart.this, Cart.class);
                    //startActivity(intent);

                }


            }
        });

        adressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        adressDialog.show();

    }

    private String getZipCode(){
        return etZipCode.getText().toString();
    }

    public String getUriRequest(){
        return "https://viacep.com.br/ws/"+getZipCode()+"/json/";
    }


    public void setAddressFields( Address address){
        setField( R.id.edt_rua, address.getLogradouro() );
        setField( R.id.edt_bairro, address.getBairro() );
        setSpinner( R.id.spinner_cidade, R.array.cidades_array, address.getLocalidade() );
    }

    private void setField( int fieldId, String data ){
        ((EditText) adressDialog.findViewById( fieldId )).setText( data );
    }

    private void setSpinner( int fieldId, int arrayId, String cidade ){
        Spinner spinner = adressDialog.findViewById( fieldId );
        String[] cidades = getResources().getStringArray(arrayId);

        for( int i = 0; i < cidades.length; i++ ){
            if( cidades[i].equals(cidade) ){
                spinner.setSelection( i );
                break;
            }else if( cidades[i].equals(cidade) ){
                spinner.setSelection( i );
                break;
            }
        }
    }

    private float loadListPedidos(float total) {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        //Calculando Preco Total
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

        return total;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Cart.this, SizesActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cidadeSelected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
