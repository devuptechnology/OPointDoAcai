package com.devup.opointdoacai.opointdoacai;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {

    public static User currentUser;

    public static String FLAGFIRSTINIT;

    public static String convertCodeToStatus(String status) {

        if (status.equals("0")){
            return "Pedido";
        }else if (status.equals("1")){
            return "A Caminho";
        }else{
            return "Entregue";
        }
    }

    public static final String DELETE = "Excluir";

    public static boolean isConnectedToInternet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null){

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if (info != null){

                for (int i = 0; i < info.length; i++){

                    if (info[i].getState() == NetworkInfo.State.CONNECTED){

                        return true;

                    }

                }

            }

        }
        return false;

    }
}
