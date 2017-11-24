package com.example.jdemu.pruebabottom;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.jdemu.pruebabottom.R.id.container;
import static com.example.jdemu.pruebabottom.R.id.visible;

/**
 * Created by jdemu on 21/11/2017.
 */

public class Recarga extends Fragment implements View.OnClickListener {
    Spinner jTrans;
    Spinner jNumero;
    ArrayList<String> trans = new ArrayList<>();
    ArrayList<String> Numero = new ArrayList<>();
    TextView a;
    TextView b1;
    TextView b2;
    TextView b3;
    TextView c1;
    TextView c2;
    TextView pos;
    int numeroSumar;
    ArrayList<Integer> bus = new ArrayList<>(), metro = new ArrayList<>(), cercanias = new ArrayList<>(), metro_ligero = new ArrayList<>();
    ArrayList<String> nombres;
    String busqueda;
    Button btnrecarga;
    Bundle b;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViewsInLayout();
        View vista = inflater.inflate(R.layout.recarga, container, false);
        jTrans = (Spinner) vista.findViewById(R.id.Transp);
        trans.add("BUS");
        trans.add("METRO");
        trans.add("CERCANIAS");
        trans.add("METRO_LIGERO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_item, trans);
        jTrans.setAdapter(dataAdapter);
        jNumero = (Spinner) vista.findViewById(R.id.spinnerNumero);
        for (int i = 1; i < 21; i++) {
            Numero.add("" + i);
        }
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_item, Numero);
        jNumero.setAdapter(dataAdapter2);
        a = (TextView) vista.findViewById(R.id.A);
        b1 = (TextView) vista.findViewById(R.id.B1);
        b2 = (TextView) vista.findViewById(R.id.B2);
        b3 = (TextView) vista.findViewById(R.id.B3);
        c1 = (TextView) vista.findViewById(R.id.C1);
        c2 = (TextView) vista.findViewById(R.id.c2);
        pos = (TextView) vista.findViewById(R.id.donde);

        a.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        c1.setVisibility(View.INVISIBLE);
        c2.setVisibility(View.INVISIBLE);
        pos.setVisibility(View.INVISIBLE);

        btnrecarga = (Button) vista.findViewById(R.id.btRecarga);
        btnrecarga.setVisibility(View.INVISIBLE);
        jNumero.setVisibility(View.INVISIBLE);
        a.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        b = getArguments();
        nombres = b.getStringArrayList("columnas");
        jTrans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostrar();
                if (b.containsKey("fila")) {
                    b.remove("fila");
                    b.putString("fila", String.valueOf(jTrans.getSelectedItem()));
                } else {
                    b.putString("fila", String.valueOf(jTrans.getSelectedItem()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        btnrecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = jNumero.getSelectedItemPosition() + 1;
                viajes();
                if (b.containsKey("numeroV")) {
                    b.remove("numeroV");
                    b.putString("numeroV", String.valueOf(posicion + numeroSumar));
                } else {
                    b.putString("numeroV", String.valueOf(posicion + numeroSumar));
                }
                Toast toast1 = Toast.makeText(getContext(), "RECARGADO", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
        return vista;

    }

    public void mostrar() {
        if (String.valueOf(jTrans.getSelectedItem()).equals("BUS") || String.valueOf(jTrans.getSelectedItem()).equals("CERCANIAS")) {
            a.setText(nombres.get(0));
            b1.setText(nombres.get(1));
            b2.setText(nombres.get(2));
            b3.setText(nombres.get(3));
            c1.setText(nombres.get(4));
            c2.setText(nombres.get(5));
            a.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            c1.setVisibility(View.VISIBLE);
            c2.setVisibility(View.VISIBLE);


        } else if (String.valueOf(jTrans.getSelectedItem()).equals("METRO")) {
            a.setText(nombres.get(0));
            b1.setText(nombres.get(1));
            b2.setText(nombres.get(2));
            b3.setText(nombres.get(3));
            a.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);

        } else if (String.valueOf(jTrans.getSelectedItem()).equals("METRO_LIGERO")) {
            a.setText(nombres.get(6));
            b1.setText(nombres.get(7));
            b2.setText(nombres.get(8));
            a.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.INVISIBLE);
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);

        }

    }


    @Override
    public void onClick(View v) {
        int text = v.getId();
        if (text == R.id.A) {
            if (String.valueOf(jTrans.getSelectedItem()).equals("METRO_LIGERO")) {
                busqueda = "ML1";
            } else {
                busqueda = "A";
            }

            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        } else if (text == R.id.B1) {
            if (String.valueOf(jTrans.getSelectedItem()).equals("METRO_LIGERO")) {
                busqueda = "ML2";
            } else {
                busqueda = "B1";
            }
            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        } else if (text == R.id.B2) {

            if (String.valueOf(jTrans.getSelectedItem()).equals("METRO_LIGERO")) {
                busqueda = "ML3";
            } else {
                busqueda = "B2";
            }
            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        } else if (text == R.id.C1) {
            busqueda = "C1";
            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        } else if (text == R.id.c2) {
            busqueda = "C2";
            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        } else if (text == R.id.B3) {
            busqueda = "B3";
            btnrecarga.setVisibility(View.VISIBLE);
            jNumero.setVisibility(View.VISIBLE);
        }
        if (b.containsKey("busqueda")) {
            b.remove("busqueda");
            b.putString("busqueda", busqueda);
        } else {
            b.putString("busqueda", busqueda);
        }
        pos.setVisibility(View.VISIBLE);
        pos.setText("Cuantos viajes " + busqueda+" desea añadir");

    }

    public void viajes() {
        ArrayList<Integer> act;
        if (b.getString("fila").equals("BUS")) {
            act = ObtenerDatosTransporte.getBus();
            if (b.getString("busqueda").equals("A")) {
                numeroSumar = act.get(0);
            } else if (b.getString("busqueda").equals("B1")) {
                numeroSumar = act.get(1);
            } else if (b.getString("busqueda").equals("B2")) {
                numeroSumar = act.get(2);
            } else if (b.getString("busqueda").equals("B3")) {
                numeroSumar = act.get(3);
            } else if (b.getString("busqueda").equals("C1")) {
                numeroSumar = act.get(4);
            } else if (b.getString("busqueda").equals("C2")) {
                numeroSumar = act.get(5);
            }
        } else if (b.getString("fila").equals("METRO")) {
            act = ObtenerDatosTransporte.getMetro();
            if (b.getString("busqueda").equals("A")) {
                numeroSumar = act.get(0);
            } else if (b.getString("busqueda").equals("B1")) {
                numeroSumar = act.get(1);
            } else if (b.getString("busqueda").equals("B2")) {
                numeroSumar = act.get(2);
            } else if (b.getString("busqueda").equals("B3")) {
                numeroSumar = act.get(3);
            }
        } else if (b.getString("fila").equals("CERCANIAS")) {
            act = ObtenerDatosTransporte.getCercanias();
            if (b.getString("busqueda").equals("A")) {
                numeroSumar = act.get(0);
            } else if (b.getString("busqueda").equals("B1")) {
                numeroSumar = act.get(1);
            } else if (b.getString("busqueda").equals("B2")) {
                numeroSumar = act.get(2);
            } else if (b.getString("busqueda").equals("B3")) {
                numeroSumar = act.get(3);
            } else if (b.getString("busqueda").equals("C1")) {
                numeroSumar = act.get(4);
            } else if (b.getString("busqueda").equals("C2")) {
                numeroSumar = act.get(5);
            }
        } else if (b.getString("fila").equals("METRO_LIGERO")) {
            act = ObtenerDatosTransporte.getMetro_ligero();
            if (b.getString("busqueda").equals("ML1")) {
                numeroSumar = act.get(0);
            } else if (b.getString("busqueda").equals("ML2")) {
                numeroSumar = act.get(1);
            } else if (b.getString("busqueda").equals("ML3")) {
                numeroSumar = act.get(2);
            }
        }
    }
}
