package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoDeReprobados extends AppCompatActivity {
    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv = (ListView) findViewById(R.id.lista);

        AdminSQLiteOpenHelper1 db = new AdminSQLiteOpenHelper1(getApplicationContext(), null,null, 1);
        lista = db.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "codigo: " +lista.get(position)+"\n";
                informacion+="Nombre: "+lista.get(position)+"\n";
                informacion+="Apellido: "+lista.get(position)+"\n";
                informacion+="Telefono: "+lista.get(position)+"\n";

                Toast.makeText(getApplicationContext(),"informacion", Toast.LENGTH_LONG).show();
            }
        });
    }
}
