package com.example.jdemu.pruebabottom;

import android.database.Cursor;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by jdemu on 20/11/2017.
 */

public class ObtenerDatosTransporte {
    static ArrayList<Integer> bus = new ArrayList<>(), metro = new ArrayList<>(), cercanias = new ArrayList<>(), metro_ligero = new ArrayList<>();
    static ArrayList<String> columnas = new ArrayList<>();
    static Bundle b;
    public static ArrayList<String> insert() {
        ArrayList<String> lista_transporte = new ArrayList<>();
        lista_transporte.add("INSERT INTO `Transporte` (`M_transporte`, `A`,`B1`,`B2`,`B3`,`C1`,`C2`) values ('BUS',0,0,20,0,0,0);");
        lista_transporte.add("INSERT INTO `Transporte` (`M_transporte`, `A`,`B1`,`B2`,`B3`) values ('METRO',0,0,0,10);");
        lista_transporte.add("INSERT INTO `Transporte` (`M_transporte`, `A`,`B1`,`B2`,`B3`,`C1`,`C2`) values ('CERCANIAS',1,0,0,0,0,0);");
        lista_transporte.add("INSERT INTO `Transporte` (`M_transporte`, `ML1`,`ML2`,`ML3`) values ('METRO_LIGERO',1,2,3);");
        return lista_transporte;
    }
    public static void borrartodo(){
        bus.clear();
        metro.clear();
        cercanias.clear();
        metro_ligero.clear();
        columnas.clear();
    }

    public static void llenarArrays(Cursor cursor) {
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals("BUS")) {
                bus.add(cursor.getInt(1));
                bus.add(cursor.getInt(2));
                bus.add(cursor.getInt(3));
                bus.add(cursor.getInt(4));
                bus.add(cursor.getInt(5));
                bus.add(cursor.getInt(6));
            } else if (cursor.getString(0).equals("METRO")) {
                metro.add(cursor.getInt(1));
                metro.add(cursor.getInt(2));
                metro.add(cursor.getInt(3));
                metro.add(cursor.getInt(4));
            } else if (cursor.getString(0).equals("CERCANIAS")) {
                cercanias.add(cursor.getInt(1));
                cercanias.add(cursor.getInt(2));
                cercanias.add(cursor.getInt(3));
                cercanias.add(cursor.getInt(4));
                cercanias.add(cursor.getInt(5));
                cercanias.add(cursor.getInt(6));
            } else if (cursor.getString(0).equals("METRO_LIGERO")) {
                metro_ligero.add(cursor.getInt(7));
                metro_ligero.add(cursor.getInt(8));
                metro_ligero.add(cursor.getInt(9));
            }

        }
        columnas.add("A");
        columnas.add("B1");
        columnas.add("B2");
        columnas.add("B3");
        columnas.add("C1");
        columnas.add("C2");
        columnas.add("ML1");
        columnas.add("ML2");
        columnas.add("ML3");
    }

    public static ArrayList<Integer> getBus() {
        return bus;
    }

    public static ArrayList<Integer> getMetro() {
        return metro;
    }

    public static ArrayList<Integer> getCercanias() {
        return cercanias;
    }

    public static ArrayList<Integer> getMetro_ligero() {
        return metro_ligero;
    }

    public static ArrayList<String> getColumnas() {
        return columnas;
    }


}
