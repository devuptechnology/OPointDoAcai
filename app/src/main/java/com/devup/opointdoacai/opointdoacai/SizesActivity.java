package com.devup.opointdoacai.opointdoacai;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;


public class SizesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizes);

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.sizes_layout_target), "Você pode escolher o tamanho do seu Copo\n\n\n\n\n\nEste é o mais pedido:", "")
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
                        .drawShadow(false)
                        .cancelable(false)
                        .tintTarget(false)
                        .transparentTarget(false)
                        .targetRadius(110),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                    }
                });

    }

}
