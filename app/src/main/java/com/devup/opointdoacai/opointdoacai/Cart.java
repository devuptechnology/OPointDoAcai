package com.devup.opointdoacai.opointdoacai;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devup.opointdoacai.opointdoacai.Database.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.StringValue;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

import es.dmoral.toasty.Toasty;

public class Cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

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

    RelativeLayout rootLayout;

    private android.support.v7.widget.Toolbar toolbar;

    private FloatingActionButton floatingActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_cart);
        toolbar.setTitle("Carrinho");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        floatingActionButtonAdd = findViewById(R.id.fab_cart_add);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Pedidos");
        mAuth = FirebaseAuth.getInstance();

        //Init
        recyclerView = findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Swipe to Delete
        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        txtTotal = findViewById(R.id.total);
        btnConfirmarPedido = findViewById(R.id.btn_confirmar_pedido);

        rootLayout = findViewById(R.id.rootLayout);

        loadListPedidos(total);

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    if (cart.size() > 0) {

                        Calendar now = Calendar.getInstance();
                        int sem = now.get(Calendar.DAY_OF_WEEK);
                        int hour = now.get(Calendar.HOUR_OF_DAY);
                        int minute = now.get(Calendar.MINUTE);

                        if (sem == 2){

                            Toasty.error(Cart.this, "Não abrimos às Segunda-feiras", Toast.LENGTH_SHORT).show();

                        }else{

                            if ((hour < 14) && (hour > 0)){

                                int hora = 13 - hour;
                                int minutos = 60 - minute;

                                if (hora == 0){

                                    Toasty.error(Cart.this, "Abriremos dentro de " + minutos + " minuto(s)", Toast.LENGTH_SHORT).show();


                                }else{

                                    Toasty.error(Cart.this, "Abriremos dentro de " + hora + " hora(s) e " + minutos + " minuto(s)", Toast.LENGTH_SHORT).show();


                                }


                            }else if ((hour >= 21) && (hour < 24)){

                                Toasty.error(Cart.this, "Já encerramos por hoje", Toast.LENGTH_SHORT).show();

                            }else{

                                callDialogAdress();

                            }

                        }
                    } else {
                        Toasty.error(Cart.this, "Carrinho vazio", Toast.LENGTH_SHORT).show();
                    }
                }else{

                Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
                return;

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

        final RadioButton rdiCOD = adressDialog.findViewById(R.id.rdiCOD);
        final RadioButton rdiCard = adressDialog.findViewById(R.id.rdiCard);
        final RadioButton rdiCardCOD = adressDialog.findViewById(R.id.rdiCardCOD);

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

                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                String cep = etZipCode.getText().toString();
                String rua = adressRua.getText().toString();
                String num = adressNum.getText().toString();
                String bairro = adressBairro.getText().toString();


                String cidade = String.valueOf(spinnerCidade.getSelectedItem());
                String endereco = rua + ", " + num + ", " + bairro + ", " + cep + ", " + cidade + " - SP";

                if (Common.isConnectedToInternet(getBaseContext())) {

                    if (TextUtils.isEmpty(adressRua.getText()) || TextUtils.isEmpty(adressNum.getText()) || TextUtils.isEmpty(adressBairro.getText())) {

                        Toasty.error(Cart.this, "Por favor, preencha com todos os dados", Toast.LENGTH_SHORT).show();

                    } else {

                        //Checando método de pagamento

                        if (!rdiCard.isChecked() && !rdiCardCOD.isChecked() && !rdiCOD.isChecked()){

                            Toasty.error(Cart.this, "Selecione uma opção de pagamento", Toast.LENGTH_SHORT).show();

                        }else if (rdiCard.isChecked()){

                            Request request = new Request(
                                    firebaseUser.getPhoneNumber(),
                                    firebaseUser.getDisplayName(),
                                    endereco,
                                    txtTotal.getText().toString(),
                                    "Cartão",
                                    "0",
                                    "0",
                                    cart
                            );

                            //Enviando ao Firebase
                            requests.child(String.valueOf(System.currentTimeMillis()))
                                    .setValue(request);

                            //Esvaziando o carrinho
                            new Database(getBaseContext()).cleanCart();

                            Toasty.success(Cart.this, "Pedido efetuado com Sucesso!", Toast.LENGTH_SHORT).show();

                            //Enviando para a Atividade Principal
                            Intent intent = new Intent(Cart.this, OrderStatus.class);
                            startActivity(intent);
                            finish();

                        }else if (rdiCOD.isChecked()){

                            Request request = new Request(
                                    firebaseUser.getPhoneNumber(),
                                    firebaseUser.getDisplayName(),
                                    endereco,
                                    txtTotal.getText().toString(),
                                    "Dinheiro",
                                    "0",
                                    "0",
                                    cart
                            );

                            //Enviando ao Firebase
                            requests.child(String.valueOf(System.currentTimeMillis()))
                                    .setValue(request);

                            //Esvaziando o carrinho
                            new Database(getBaseContext()).cleanCart();

                            Toasty.success(Cart.this, "Pedido efetuado com Sucesso!", Toast.LENGTH_SHORT).show();

                            //Enviando para a Atividade Principal
                            Intent intent = new Intent(Cart.this, OrderStatus.class);
                            startActivity(intent);
                            finish();

                        }else if (rdiCardCOD.isChecked()){

                            Request request = new Request(
                                    firebaseUser.getPhoneNumber(),
                                    firebaseUser.getDisplayName(),
                                    endereco,
                                    txtTotal.getText().toString(),
                                    "Dinheiro e Cartão",
                                    "0",
                                    "0",
                                    cart
                            );

                            //Enviando ao Firebase
                            requests.child(String.valueOf(System.currentTimeMillis()))
                                    .setValue(request);

                            //Esvaziando o carrinho
                            new Database(getBaseContext()).cleanCart();

                            Toasty.success(Cart.this, "Pedido efetuado com Sucesso!", Toast.LENGTH_SHORT).show();

                            //Enviando para a Atividade Principal
                            Intent intent = new Intent(Cart.this, OrderStatus.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                }else{

                    Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
                    return;

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
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle().equals(Common.DELETE)){
            deleteCart(item.getOrder());
        }

        return true;
    }

    private void deleteCart(int position) {

        cart.remove(position);

        new Database(this).cleanCart();

        for (Order item:cart){
            new Database(this).addToCart(item);
        }

        loadListPedidos(total);

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cidadeSelected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof CartViewHolder) {

            String name = ((CartAdapter) recyclerView.getAdapter()).getItem(viewHolder.getAdapterPosition()).getQuantidade();

            final Order deleteItem = ((CartAdapter) recyclerView.getAdapter()).getItem(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem(deleteIndex);

        }
    }
}
