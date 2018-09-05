package com.devup.opointdoacai.opointdoacai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SizesActivity extends AppCompatActivity {

    private CardView cardView1, cardView2, cardView3, cardView4, cardView5;

    private float valorqnt = 0.00f;
    private String quantidade = "";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mOrderDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizes);

        mFirebaseDatabase  = FirebaseDatabase.getInstance();
        mOrderDatabaseReference =  mFirebaseDatabase.getReference().child("pedidos");

        //Setando Orientação de Retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cardView1 = findViewById(R.id.card_qnt_200);
        cardView2 = findViewById(R.id.card_qnt_300);
        cardView3 = findViewById(R.id.card_qnt_400);
        cardView4 = findViewById(R.id.card_qnt_500);
        cardView5 = findViewById(R.id.card_qnt_700);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 3.00f;
                quantidade = "Açaí de 200 ml";

                Bundle bundle = new Bundle();
                bundle.putFloat("value", valorqnt);
                bundle.putString("qnt", quantidade);
                Intent intent = new Intent(getApplicationContext(), CompActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 4.00f;
                quantidade = "Açaí de 300 ml";

                Bundle bundle = new Bundle();
                bundle.putFloat("value", valorqnt);
                bundle.putString("qnt", quantidade);
                Intent intent = new Intent(getApplicationContext(), CompActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 5.00f;
                quantidade = "Açaí de 400 ml";

                Bundle bundle = new Bundle();
                bundle.putFloat("value", valorqnt);
                bundle.putString("qnt", quantidade);
                Intent intent = new Intent(getApplicationContext(), CompActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 6.00f;
                quantidade = "Açaí de 500 ml";

                Bundle bundle = new Bundle();
                bundle.putFloat("value", valorqnt);
                bundle.putString("qnt", quantidade);
                Intent intent = new Intent(getApplicationContext(), CompActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valorqnt = 9.00f;
                quantidade = "Açaí de 700 ml";

                Bundle bundle = new Bundle();
                bundle.putFloat("value", valorqnt);
                bundle.putString("qnt", quantidade);
                Intent intent = new Intent(getApplicationContext(), CompActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        animationsPlay();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();



    }

    private void animationsPlay() {

        YoYo.with(Techniques.FlipInX)
                .duration(500)
                .repeat(0)
                .delay(200)
                .playOn(findViewById(R.id.card_qnt_200));

        YoYo.with(Techniques.FlipInX)
                .duration(500)
                .repeat(0)
                .delay(200)
                .playOn(findViewById(R.id.card_qnt_300));

        YoYo.with(Techniques.FlipInX)
                .duration(500)
                .repeat(0)
                .delay(200)
                .playOn(findViewById(R.id.card_qnt_400));

        YoYo.with(Techniques.FlipInX)
                .duration(500)
                .repeat(0)
                .delay(200)
                .playOn(findViewById(R.id.card_qnt_500));

        YoYo.with(Techniques.FlipInX)
                .duration(500)
                .repeat(0)
                .delay(200)
                .playOn(findViewById(R.id.card_qnt_700));


    }


}
