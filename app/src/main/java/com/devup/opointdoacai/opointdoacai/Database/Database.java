package com.devup.opointdoacai.opointdoacai.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.devup.opointdoacai.opointdoacai.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME= "OpointdoacaiDataBase.db";
    private static final int DB_VER = 1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Complementos", "IdProdutos", "Quantidade", "Preco"};
        String sqlTable = "OrderDetail";

        queryBuilder.setTables(sqlTable);
        Cursor cursor = queryBuilder.query(sqLiteDatabase, sqlSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst()){

            do {

                result.add(new Order(cursor.getString(cursor.getColumnIndex("IdProdutos")),
                        cursor.getString(cursor.getColumnIndex("Complementos")),
                        cursor.getString(cursor.getColumnIndex("Quantidade")),
                        cursor.getString(cursor.getColumnIndex("Preco"))
                        ));

            }while (cursor.moveToNext());

        }

        return result;

    }

    public void addToCart(Order order){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(IdProdutos,Complementos,Quantidade,Preco) VALUES('%s','%s','%s','%s');",
                order.getIdProdutos(),
                order.getComplementos(),
                order.getQuantidade(),
                order.getPreco());
        sqLiteDatabase.execSQL(query);
    }

    public void cleanCart(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        sqLiteDatabase.execSQL(query);
    }
}
