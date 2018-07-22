package com.devup.opointdoacai.opointdoacai;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;


public class SizesActivity extends AppCompatActivity {

    private CardView cardView1, cardView2, cardView3, cardView4, cardView5;

    private int tamanhoEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizes);

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

                tamanhoEscolhido = 1;

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tamanhoEscolhido = 2;

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tamanhoEscolhido = 3;

            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tamanhoEscolhido = 4;

            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tamanhoEscolhido = 5;

            }
        });

        animationsPlay();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        tamanhoEscolhido = 0;

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
