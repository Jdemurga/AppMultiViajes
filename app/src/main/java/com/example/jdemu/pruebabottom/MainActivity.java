package com.example.jdemu.pruebabottom;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getFragmentManager();
    ArrayList<Integer> bus, metro, cercanias, metro_ligero;
    ArrayList<String> nombresCol;
    SQLiteOpenHelper sq;
    SQLiteDatabase basedatos;
    Bundle b = new Bundle();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragmento_seleccionado;
            FragmentTransaction ts;


            switch (item.getItemId()) {

                case R.id.navigation_home:
                    //Resta
                    if (b.containsKey("numeroV")) {
                        actualizar();
                    }
                    fragmento_seleccionado = new Resta();
                    ts = fm.beginTransaction();
                    ts.replace(R.id.content, fragmento_seleccionado);
                    fragmento_seleccionado.setArguments(b);
                    ts.commit();
                    return true;

                case R.id.recargar:

                    b.putStringArrayList("columnas", nombresCol);
                    fragmento_seleccionado = new Recarga();
                    ts = fm.beginTransaction();
                    ts.replace(R.id.content, fragmento_seleccionado);
                    fragmento_seleccionado.setArguments(b);
                    ts.commit();
                    return true;

            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        sq = new SQLiteOpenHelper(this, "Transporte2.db3", null, 1) {
            String borrar = "drop table if exists Transporte";


            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                String sql = "CREATE TABLE `Transporte` (\n" +
                        "  `M_transporte` TEXT DEFAULT NULL,\n" +
                        "  `A` INTEGER DEFAULT NULL,\n" +
                        "  `B1` INTEGER DEFAULT NULL,\n" +
                        "  `B2` INTEGER DEFAULT NULL,\n" +
                        "  `B3` INTEGER DEFAULT NULL,\n" +
                        "  `C1` INTEGER DEFAULT NULL,\n" +
                        "  `C2` INTEGER DEFAULT NULL,\n" +
                        "  `ML1` INTEGER DEFAULT NULL,\n" +
                        "  `ML2` INTEGER DEFAULT NULL,\n" +
                        "  `ML3` INTEGER DEFAULT NULL\n" +
                        ") ;\n";

                sqLiteDatabase.execSQL(sql);
                ArrayList<String> TRANS = ObtenerDatosTransporte.insert();
                for (int i = 0; i < TRANS.size(); i++) {
                    String insert = TRANS.get(i);
                    sqLiteDatabase.execSQL(insert);

                }


            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,
                                  int i1) {

            }
        };
        basedatos = sq.getWritableDatabase();
        String selectTrans = "SELECT * FROM Transporte";
        Cursor cursorTrans = basedatos.rawQuery(selectTrans, null);
        ObtenerDatosTransporte.llenarArrays(cursorTrans);
        while (cursorTrans.moveToNext()) {
            String v = cursorTrans.getString(0);
            Log.v("datos", v);
        }
        bus = ObtenerDatosTransporte.getBus();
        metro = ObtenerDatosTransporte.getMetro();
        metro_ligero = ObtenerDatosTransporte.getMetro_ligero();
        cercanias = ObtenerDatosTransporte.getCercanias();
        nombresCol = ObtenerDatosTransporte.getColumnas();
        b.putIntegerArrayList("buses", bus);
        b.putIntegerArrayList("cercanias", cercanias);
        b.putIntegerArrayList("metro", metro);
        b.putIntegerArrayList("ligero", metro_ligero);
        Fragment frg = new Resta();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, frg);
        frg.setArguments(b);
        ft.commit();
    }

    public void actualizar() {
        String columna = b.getString("busqueda");
        String fila = b.getString("fila");
        String numeroV = b.getString("numeroV");
        SQLiteStatement stmt = basedatos.compileStatement("UPDATE Transporte SET " + columna + " = " + numeroV + " WHERE M_transporte = '" + fila + "'");
        stmt.execute();
        b.remove("numeroV");
        ObtenerDatosTransporte.borrartodo();
        String selectTrans = "SELECT * FROM Transporte";
        Cursor cursorTrans = basedatos.rawQuery(selectTrans, null);
        ObtenerDatosTransporte.llenarArrays(cursorTrans);
        bus = ObtenerDatosTransporte.getBus();
        metro = ObtenerDatosTransporte.getMetro();
        metro_ligero = ObtenerDatosTransporte.getMetro_ligero();
        cercanias = ObtenerDatosTransporte.getCercanias();
        nombresCol = ObtenerDatosTransporte.getColumnas();
        if (b.containsKey("buses")) {
            b.remove("buses");
            b.putIntegerArrayList("buses", bus);
        } else{
            b.putIntegerArrayList("buses", bus);
        }
        if (b.containsKey("cercanias")) {
            b.remove("cercanias");
            b.putIntegerArrayList("cercanias", cercanias);
        } else{
            b.putIntegerArrayList("cercanias", cercanias);
        }
        if (b.containsKey("metro")) {
            b.remove("metro");
            b.putIntegerArrayList("metro", metro);
        } else{
            b.putIntegerArrayList("metro", metro);
        }
        if (b.containsKey("ligero")) {
            b.remove("ligero");
            b.putIntegerArrayList("ligero", metro_ligero);
        } else{
            b.putIntegerArrayList("ligero", metro_ligero);
        }


    }

    public void borrar(){
        String columna = b.getString("COL");
        String fila = b.getString("ROW");
        String numeroV = b.getString("nuevo");
        SQLiteStatement stmt = basedatos.compileStatement("UPDATE Transporte SET " + columna + " = " + numeroV + " WHERE M_transporte = '" + fila + "'");
        stmt.execute();
        ObtenerDatosTransporte.borrartodo();
        String selectTrans = "SELECT * FROM Transporte";
        Cursor cursorTrans = basedatos.rawQuery(selectTrans, null);
        ObtenerDatosTransporte.llenarArrays(cursorTrans);
        bus = ObtenerDatosTransporte.getBus();
        metro = ObtenerDatosTransporte.getMetro();
        metro_ligero = ObtenerDatosTransporte.getMetro_ligero();
        cercanias = ObtenerDatosTransporte.getCercanias();
        nombresCol = ObtenerDatosTransporte.getColumnas();
        if (b.containsKey("buses")) {
            b.remove("buses");
            b.putIntegerArrayList("buses", bus);
        } else{
            b.putIntegerArrayList("buses", bus);
        }
        if (b.containsKey("cercanias")) {
            b.remove("cercanias");
            b.putIntegerArrayList("cercanias", cercanias);
        } else{
            b.putIntegerArrayList("cercanias", cercanias);
        }
        if (b.containsKey("metro")) {
            b.remove("metro");
            b.putIntegerArrayList("metro", metro);
        } else{
            b.putIntegerArrayList("metro", metro);
        }
        if (b.containsKey("ligero")) {
            b.remove("ligero");
            b.putIntegerArrayList("ligero", metro_ligero);
        } else{
            b.putIntegerArrayList("ligero", metro_ligero);
        }
    }
}
