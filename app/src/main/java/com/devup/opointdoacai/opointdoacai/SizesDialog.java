package com.devup.opointdoacai.opointdoacai;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class SizesDialog extends DialogFragment{

    private String selection;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String[] sizes = getActivity().getResources().getStringArray(R.array.sizes);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        builder.setTitle("  Selecione o Tamanho");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.iconacai);

        builder.setSingleChoiceItems(R.array.sizes, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                selection = sizes[which];

            }
        });

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toasty.success(getActivity(), "Opção Selecionada:" + selection, Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(getActivity(), SizesActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toasty.error(getActivity(), "Cancelado", Toast.LENGTH_SHORT, true).show();

            }
        });

        return builder.create();
    }
}
