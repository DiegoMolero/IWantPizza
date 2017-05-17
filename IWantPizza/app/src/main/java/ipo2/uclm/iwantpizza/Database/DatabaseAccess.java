package ipo2.uclm.iwantpizza.Database;

/**
 * Created by User on 17/05/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ipo2.uclm.iwantpizza.Comida.Bebida;
import ipo2.uclm.iwantpizza.Comida.Comida;
import ipo2.uclm.iwantpizza.Comida.Pizza;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM users", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public boolean isEmailPass(String email,String pass){
        List<String> list = new ArrayList<>();
        String sqlSentence="SELECT pass FROM users WHERE email='"+email+"' AND pass='"+pass+"'";
        Cursor cursor = database.rawQuery(
        sqlSentence,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return  !list.isEmpty();
    }

    public  ArrayList<Comida> getListPizzas(){
        ArrayList<Comida> arrayPizzas = new ArrayList<>();
        String tipo=null;
        String precio=null;
        String sqlSentence="SELECT * FROM pizzas";
        Cursor cursor = database.rawQuery(
                sqlSentence,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tipo= cursor.getString(0);
            precio= cursor.getString(1);
            Pizza aux = new Pizza(tipo,Float.parseFloat(precio));
            arrayPizzas.add(aux);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayPizzas;
    }
    public ArrayList<Comida> getListBebidas(){
        ArrayList<Comida> arrayPizzas = new ArrayList<>();
        String tipo=null;
        String precio=null;
        String sqlSentence="SELECT * FROM bebidas";
        Cursor cursor = database.rawQuery(
                sqlSentence,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tipo= cursor.getString(0);
            precio= cursor.getString(1);
            Bebida aux = new Bebida(tipo,Float.parseFloat(precio));
            arrayPizzas.add(aux);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayPizzas;
    }
}