package com.devup.opointdoacai.opointdoacai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class PhoneAuth extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button click;

    @Override
    public void onStart() {
        super.onStart();

        if (Common.isConnectedToInternet(getBaseContext())) {

            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                startActivity(new Intent(PhoneAuth.this, MainActivity.class));
                finish();
            }

        }else{
            Toasty.error(getApplicationContext(), "Sem conexão com a Internet", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_phone_auth);
        mAuth = FirebaseAuth.getInstance();

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PhoneAuth.this, Authentication.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
