package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ms_square.etsyblur.BlurSupport;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {

    //Variavel de Views
    private android.support.v7.widget.Toolbar toolbar;


    //Authentication
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //Firestore Backend
    private FirebaseFirestore mFirestore;

    //Componetes Buttons Cards
    private Button btnAcai;
    private Button btnAcaiTop;
    private Button btnSucos;
    private Button btnVitaminas;
    private Button btnSaladaDeFrutas;

    //Componentes Buttons Drawer
    private RelativeLayout rlHome;
    private RelativeLayout rlPedidos;
    private RelativeLayout rlCarrinho;
    private RelativeLayout rlSair;
    private RelativeLayout rlFidelidade;


    //Variáveis
    private String name;
    private  String phone;
    private FlowingDrawer mDrawer;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        if (Common.isConnectedToInternet(getBaseContext())) {

            mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                    if (currentUser != null) {

                        final String user_id = mAuth.getCurrentUser().getUid();
                        String token_id = FirebaseInstanceId.getInstance().getToken();

                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("token_id", token_id);

                        mFirestore.collection("Users").document(user_id).update(userMap);


                    } else {
                        Intent intent = new Intent(MainActivity.this, PhoneAuth.class);
                        startActivity(intent);
                        finish();
                    }

                }
            };

        }else{
            Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciando Componentes da Nav Drawer
        rlHome = findViewById(R.id.home_acess);
        rlSair = findViewById(R.id.out_acess);
        rlPedidos = findViewById(R.id.order_acess);
        rlCarrinho = findViewById(R.id.cart_acess);
        rlFidelidade = findViewById(R.id.fide_acess);

        rlFidelidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toasty.success(MainActivity.this, "Ainda em construção", Toast.LENGTH_SHORT).show();

            }
        });

        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawer.closeMenu();

            }
        });

        rlCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawer.closeMenu();
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);

            }
        });

        rlPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawer.closeMenu();
                Intent intent = new Intent(MainActivity.this, OrderStatus.class);
                startActivity(intent);

            }
        });


        rlSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                mDrawer.closeMenu();
                finish();

            }
        });


        //Toolbar - Instanciando
        toolbar = findViewById(R.id.toolbar_id_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawer.toggleMenu();

            }
        });

        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {          }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
            }
        });


        mAuth = FirebaseAuth.getInstance();


        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.main_layout_target), "Seja Bem Vindo ao Aplicativo do Point do Açaí!\n\n\n\nMonte seu Açaí de forma rápida!", "Selecione esta opção para montar o seu Açai\nOu selecione outra das opções abaixo")
                        .outerCircleColor(R.color.colorPrimary)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.colorTextWithe)
                        .titleTextSize(20)
                        .titleTextColor(R.color.colorTextWithe)
                        .descriptionTextSize(16)
                        .descriptionTextColor(R.color.colorAccent)
                        .textColor(R.color.colorTextWithe)
                        .textTypeface(Typeface.SANS_SERIF)
                        .dimColor(R.color.colorPrimaryDark)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(false)
                        .transparentTarget(false)
                        .targetRadius(110),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                    }
                });





        btnAcai = findViewById(R.id.btn_acai);
        btnAcaiTop = findViewById(R.id.btn_acai_top);
        btnSucos = findViewById(R.id.btn_sucos);
        btnVitaminas = findViewById(R.id.btn_vitaminas);
        btnSaladaDeFrutas = findViewById(R.id.btn_saladadefrutas);

        btnAcai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SizesActivity.class);
                startActivity(intent);

            }
        });

        btnAcaiTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TopTen.class);
                startActivity(intent);

            }
        });

        btnVitaminas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Vitaminas.class);
                startActivity(intent);

            }
        });

        btnSucos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Sucos.class);
                startActivity(intent);

            }
        });

        btnSaladaDeFrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, SaladaFrutas.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {

                Toasty.success(MainActivity.this, "Bem vindo!", Toast.LENGTH_SHORT, true).show();

                FirebaseUser currentUser = mAuth.getCurrentUser();

                    if(currentUser.getDisplayName() != null){

                        name = currentUser.getDisplayName();

                    }else{

                        name = "Anonimo";

                    }

                    if (currentUser.getPhoneNumber() != null){

                        phone = currentUser.getPhoneNumber();

                        User user = new User();
                        user.setPhone(phone);

                    }else{

                        phone = "";

                    }

                    String user_id = mAuth.getCurrentUser().getUid();

                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("phone", phone);

                    mFirestore.collection("Users").document(user_id).set(userMap);

            } else{

                Toasty.error(MainActivity.this, "Erro ao fazer Login", Toast.LENGTH_SHORT, true).show();

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener != null){

            mAuth.removeAuthStateListener(mAuthStateListener);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAuth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    public void onBackPressed() {
    }

}
