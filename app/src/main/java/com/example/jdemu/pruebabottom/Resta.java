package com.example.jdemu.pruebabottom;

import android.app.Fragment;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.text.method.Touch;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Resta extends Fragment implements View.OnClickListener {
    TextView busa;
    TextView busb1;
    TextView busb2;
    TextView busb3;
    TextView busc1;
    TextView busc2;
    TextView cercaniasA;
    TextView cercaniasb1;
    TextView cercaniasb2;
    TextView cercaniasb3;
    TextView cercaniasc1;
    TextView cercaniasc2;
    TextView ml1;
    TextView ml2;
    TextView ml3;
    TextView metroa;
    TextView metrob1;
    TextView metrob2;
    TextView metrob3;
    Button Ba;
    Button Bb1;
    Button Bb2;
    Button Bb3;
    Button Bc1;
    Button Bc2;
    Button Ca;
    Button Cb1;
    Button Cb2;
    Button Cb3;
    Button Cc1;
    Button Cc2;
    Button ML1;
    Button ML2;
    Button ML3;
    Button Ma;
    Button Mb1;
    Button Mb2;
    Button Mb3;
    int numero;
    Bundle b;
    ArrayList<Integer> bus, metro, cercanias, metro_ligero;
    View vista;
    String columns;
    String row;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (vista != null) {
            ViewGroup parent = (ViewGroup) vista.getParent();
            parent.removeView(vista);
        } else {
            vista = inflater.inflate(R.layout.resta, container, false);
            busa = (TextView) vista.findViewById(R.id.Abus);
            busb1 = (TextView) vista.findViewById(R.id.B1bus);
            busb2 = (TextView) vista.findViewById(R.id.B2bus);
            busb3 = (TextView) vista.findViewById(R.id.B3bus);
            busc1 = (TextView) vista.findViewById(R.id.C1bus);
            busc2 = (TextView) vista.findViewById(R.id.C2bus);
            cercaniasA = (TextView) vista.findViewById(R.id.Acercanias);
            cercaniasb1 = (TextView) vista.findViewById(R.id.B1cercanias);
            cercaniasb2 = (TextView) vista.findViewById(R.id.B2cercanias);
            cercaniasb3 = (TextView) vista.findViewById(R.id.B3cercanias);
            cercaniasc1 = (TextView) vista.findViewById(R.id.C1cercanias);
            cercaniasc2 = (TextView) vista.findViewById(R.id.C2cercanias);
            ml1 = (TextView) vista.findViewById(R.id.ML1);
            ml2 = (TextView) vista.findViewById(R.id.Ml2);
            ml3 = (TextView) vista.findViewById(R.id.ML3);
            metroa = (TextView) vista.findViewById(R.id.Ametro);
            metrob1 = (TextView) vista.findViewById(R.id.B1metro);
            metrob2 = (TextView) vista.findViewById(R.id.B2metro);
            metrob3 = (TextView) vista.findViewById(R.id.B3metro);
            Ba = (Button) vista.findViewById(R.id.Ba);
            Bb1 = (Button) vista.findViewById(R.id.Bb1);
            Bb2 = (Button) vista.findViewById(R.id.Bb2);
            Bb3 = (Button) vista.findViewById(R.id.Bb3);
            Bc1 = (Button) vista.findViewById(R.id.Bc1);
            Bc2 = (Button) vista.findViewById(R.id.Bc2);
            Ca = (Button) vista.findViewById(R.id.Ca);
            Cb1 = (Button) vista.findViewById(R.id.Cb1);
            Cb2 = (Button) vista.findViewById(R.id.Cb2);
            Cb3 = (Button) vista.findViewById(R.id.Cb3);
            Cc1 = (Button) vista.findViewById(R.id.Cc1);
            Cc2 = (Button) vista.findViewById(R.id.Cc2);
            Ma = (Button) vista.findViewById(R.id.Ma);
            Mb1 = (Button) vista.findViewById(R.id.Mb1);
            Mb2 = (Button) vista.findViewById(R.id.Mb2);
            Mb3 = (Button) vista.findViewById(R.id.Mb3);
            ML1 = (Button) vista.findViewById(R.id.BtnML1);
            ML2 = (Button) vista.findViewById(R.id.btnML2);
            ML3 = (Button) vista.findViewById(R.id.BtnML3);

            b = getArguments();
            bus = (ArrayList<Integer>) b.get("buses");
            metro = (ArrayList<Integer>) b.get("metro");
            cercanias = (ArrayList<Integer>) b.get("cercanias");
            metro_ligero = (ArrayList<Integer>) b.get("ligero");

            int busa1 = bus.get(0);
            int busb11 = bus.get(1);
            int busb21 = bus.get(2);
            int busb31 = bus.get(3);
            int busc11 = bus.get(4);
            int busc21 = bus.get(5);
            int cercaniasA1 = cercanias.get(0);
            int cercaniasb11 = cercanias.get(1);
            int cercaniasb21 = cercanias.get(2);
            int cercaniasb31 = cercanias.get(3);
            int cercaniasc11 = cercanias.get(4);
            int cercaniasc21 = cercanias.get(5);
            int ml11 = metro_ligero.get(0);
            int ml21 = metro_ligero.get(1);
            int ml31 = metro_ligero.get(2);
            int metroa1 = metro.get(0);
            int metrob11 = metro.get(1);
            int metrob21 = metro.get(2);
            int metrob31 = metro.get(3);


            busa.setText(String.valueOf(busa1) + " viajes");
            busb1.setText(String.valueOf(busb11) + " viajes");
            busb2.setText(String.valueOf(busb21) + " viajes");
            busb3.setText(String.valueOf(busb31) + " viajes");
            busc1.setText(String.valueOf(busc11) + " viajes");
            busc2.setText(String.valueOf(busc21) + " viajes");
            cercaniasA.setText(String.valueOf(cercaniasA1) + " viajes");
            cercaniasb1.setText(String.valueOf(cercaniasb11) + " viajes");
            cercaniasb2.setText(String.valueOf(cercaniasb21) + " viajes");
            cercaniasb3.setText(String.valueOf(cercaniasb31) + " viajes");
            cercaniasc1.setText(String.valueOf(cercaniasc11) + " viajes");
            cercaniasc2.setText(String.valueOf(cercaniasc21) + " viajes");
            ml1.setText(String.valueOf(ml11) + " viajes");
            ml2.setText(String.valueOf(ml21) + " viajes");
            ml3.setText(String.valueOf(ml31) + " viajes");
            metroa.setText(String.valueOf(metroa1) + " viajes");
            metrob1.setText(String.valueOf(metrob11) + " viajes");
            metrob2.setText(String.valueOf(metrob21) + " viajes");
            metrob3.setText(String.valueOf(metrob31) + " viajes");
        }
        Ba.setOnClickListener(this);
        Bb1.setOnClickListener(this);
        Bb2.setOnClickListener(this);
        Bb3.setOnClickListener(this);
        Bc1.setOnClickListener(this);
        Bc2.setOnClickListener(this);
        Ca.setOnClickListener(this);
        Cb1.setOnClickListener(this);
        Cb2.setOnClickListener(this);
        Cb3.setOnClickListener(this);
        Cc1.setOnClickListener(this);
        Cc2.setOnClickListener(this);
        ML1.setOnClickListener(this);
        ML2.setOnClickListener(this);
        ML3.setOnClickListener(this);
        Ma.setOnClickListener(this);
        Mb1.setOnClickListener(this);
        Mb2.setOnClickListener(this);
        Mb3.setOnClickListener(this);
        return vista;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Ba:
                if (bus.get(0) > 0) {
                    numero = bus.get(0) - 1;
                } else {
                    numero = 0;
                }
                columns = "A";
                row = "BUS";
                break;
            case R.id.Bb1:
                if (bus.get(1) > 0) {
                    numero = bus.get(1) - 1;
                } else {
                    numero = 0;
                }
                columns = "B1";
                row = "BUS";
                break;
            case R.id.Bb2:
                if (bus.get(2) > 0) {
                    numero = bus.get(2) - 1;
                } else {
                    numero = 0;
                }
                columns = "B2";
                row = "BUS";
                break;
            case R.id.Bb3:
                if (bus.get(3) > 0) {
                    numero = bus.get(3) - 1;
                } else {
                    numero = 0;
                }
                columns = "B3";
                row = "BUS";
                break;
            case R.id.Bc1:
                if (bus.get(4) > 0) {
                    numero = bus.get(4) - 1;
                } else {
                    numero = 0;
                }
                columns = "C1";
                row = "BUS";
                break;
            case R.id.Bc2:
                if (bus.get(5) > 0) {
                    numero = bus.get(5) - 1;
                } else {
                    numero = 0;
                }
                columns = "C2";
                row = "BUS";
                break;
            case R.id.Ca:
                if (cercanias.get(0) > 0) {
                    numero = cercanias.get(0) - 1;
                } else {
                    numero = 0;
                }
                columns = "A";
                row = "CERCANIAS";
                break;
            case R.id.Cb1:
                if (cercanias.get(1) > 0) {
                    numero = cercanias.get(1) - 1;
                } else {
                    numero = 0;
                }
                columns = "B1";
                row = "CERCANIAS";
                break;
            case R.id.Cb2:
                if (cercanias.get(2) > 0) {
                    numero = cercanias.get(2) - 1;
                } else {
                    numero = 0;
                }
                columns = "B2";
                row = "CERCANIAS";
                break;
            case R.id.Cb3:
                if (cercanias.get(3) > 0) {
                numero = cercanias.get(3) - 1;
            } else {
                numero = 0;
            }
                columns = "B3";
                row = "CERCANIAS";
                break;
            case R.id.Cc1:
                if (cercanias.get(4) > 0) {
                    numero = cercanias.get(4) - 1;
                } else {
                    numero = 0;
                }
                columns = "C1";
                row = "CERCANIAS";
                break;
            case R.id.Cc2:
                if (cercanias.get(5) > 0) {
                    numero = cercanias.get(5) - 1;
                } else {
                    numero = 0;
                }
                columns = "C2";
                row = "CERCANIAS";
                break;
            case R.id.BtnML1:
                if (metro_ligero.get(0) > 0) {
                    numero = metro_ligero.get(0) - 1;
                } else {
                    numero = 0;
                }
                columns = "ML1";
                row = "METRO_LIGERO";
                break;
            case R.id.btnML2:
                if (metro_ligero.get(1) > 0) {
                    numero = metro_ligero.get(1) - 1;
                } else {
                    numero = 0;
                }
                columns = "ML2";
                row = "METRO_LIGERO";
                break;
            case R.id.BtnML3:
                if (metro_ligero.get(2) > 0) {
                    numero = metro_ligero.get(2) - 1;
                } else {
                    numero = 0;
                }
                columns = "ML3";
                row = "METRO_LIGERO";
                break;
            case R.id.Ma:
                if ( metro.get(0) > 0) {
                    numero =  metro.get(0) - 1;
                } else {
                    numero = 0;
                }
                columns = "A";
                row = "METRO";
                break;
            case R.id.Mb1:
                if ( metro.get(1) > 0) {
                    numero =  metro.get(1) - 1;
                } else {
                    numero = 0;
                }
                columns = "B1";
                row = "METRO";
                break;
            case R.id.Mb2:
                if ( metro.get(2) > 0) {
                    numero =  metro.get(2) - 1;
                } else {
                    numero = 0;
                }
                columns = "B2";
                row = "METRO";
                break;
            case R.id.Mb3:
                if ( metro.get(3) > 0) {
                    numero =  metro.get(3) - 1;
                } else {
                    numero = 0;
                }
                columns = "B3";
                row = "METRO";
                break;

        }
        if (b.containsKey("nuevo")) {
            b.remove("nuevo");
            b.putString("nuevo", "" + numero);
        } else {
            b.putString("nuevo", "" + numero);
        }
        if (b.containsKey("COL")) {
            b.remove("COL");
            b.putString("COL", "" + columns);
        } else {
            b.putString("COL", "" + columns);
        }
        if (b.containsKey("ROW")) {
            b.remove("ROW");
            b.putString("ROW", "" + row);
        } else {
            b.putString("ROW", "" + row);
        }
        ((MainActivity) getActivity()).borrar();
        int busa1 = bus.get(0);
        int busb11 = bus.get(1);
        int busb21 = bus.get(2);
        int busb31 = bus.get(3);
        int busc11 = bus.get(4);
        int busc21 = bus.get(5);
        int cercaniasA1 = cercanias.get(0);
        int cercaniasb11 = cercanias.get(1);
        int cercaniasb21 = cercanias.get(2);
        int cercaniasb31 = cercanias.get(3);
        int cercaniasc11 = cercanias.get(4);
        int cercaniasc21 = cercanias.get(5);
        int ml11 = metro_ligero.get(0);
        int ml21 = metro_ligero.get(1);
        int ml31 = metro_ligero.get(2);
        int metroa1 = metro.get(0);
        int metrob11 = metro.get(1);
        int metrob21 = metro.get(2);
        int metrob31 = metro.get(3);
        busa.setText(String.valueOf(busa1) + " viajes");
        busb1.setText(String.valueOf(busb11) + " viajes");
        busb2.setText(String.valueOf(busb21) + " viajes");
        busb3.setText(String.valueOf(busb31) + " viajes");
        busc1.setText(String.valueOf(busc11) + " viajes");
        busc2.setText(String.valueOf(busc21) + " viajes");
        cercaniasA.setText(String.valueOf(cercaniasA1) + " viajes");
        cercaniasb1.setText(String.valueOf(cercaniasb11) + " viajes");
        cercaniasb2.setText(String.valueOf(cercaniasb21) + " viajes");
        cercaniasb3.setText(String.valueOf(cercaniasb31) + " viajes");
        cercaniasc1.setText(String.valueOf(cercaniasc11) + " viajes");
        cercaniasc2.setText(String.valueOf(cercaniasc21) + " viajes");
        ml1.setText(String.valueOf(ml11) + " viajes");
        ml2.setText(String.valueOf(ml21) + " viajes");
        ml3.setText(String.valueOf(ml31) + " viajes");
        metroa.setText(String.valueOf(metroa1) + " viajes");
        metrob1.setText(String.valueOf(metrob11) + " viajes");
        metrob2.setText(String.valueOf(metrob21) + " viajes");
        metrob3.setText(String.valueOf(metrob31) + " viajes");
    }
}
