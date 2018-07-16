package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //Authentication
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //Firestore Backend
    private FirebaseFirestore mFirestore;

    //Componetes
    private Button btnAcai;
    private Button btnAcaiTop;
    private Button btnSucos;
    private Button btnVitaminas;
    private Button btnSaladaDeFrutas;

    //Variáveis
    private String name;
    private  String phone;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null){

                }else{

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.FacebookBuilder().build(),
                                            new AuthUI.IdpConfig.PhoneBuilder().setDefaultNumber("br","").build()
                                    ))
                                    .setTheme(R.style.LoginTheme)
                                    .setLogo(R.drawable.logo)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnAcai = findViewById(R.id.btn_acai);
        btnAcaiTop = findViewById(R.id.btn_acai_top);
        btnSucos = findViewById(R.id.btn_sucos);
        btnVitaminas = findViewById(R.id.btn_vitaminas);
        btnSaladaDeFrutas = findViewById(R.id.btn_saladadefrutas);




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
}
