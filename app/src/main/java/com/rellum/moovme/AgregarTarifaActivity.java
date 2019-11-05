package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.Activo;
import com.rellum.moovme.clases.Tarifario;
import com.rellum.moovme.clases.TipoDeActivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AgregarTarifaActivity extends AppCompatActivity {
    private Tarifario tarifas;
    private Spinner zonaSpinner2;
    private EditText precioEditText;
    private Spinner activoSpinner;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarifa);
        setTitle("Agregar Tarifa");
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));

        //obtenemos el tarifario de la actividad AdministrarTarifasActivity
        tarifas= AdministrarTarifasActivity.getTarifas();

        zonaSpinner2 = (Spinner) findViewById(R.id.zonaSpinner2);
        precioEditText = (EditText) findViewById(R.id.precioEditText);
        activoSpinner = (Spinner) findViewById(R.id.activoSpinner);
        addBtn = (Button) findViewById(R.id.addBtn);
        ArrayList<String> zonas = MainActivity.administradorDeZonas.getZonasListToString();

        ArrayList<String> tiposDeActivo=new ArrayList<String>();
        Iterator<TipoDeActivo> iterator = MainActivity.tiposDeActivo.values().iterator();
        int num=0;
        while (iterator.hasNext()) {
            tiposDeActivo.add(iterator.next().toString());
            num+=1;
        }
        Collections.sort(tiposDeActivo);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,zonas);
        zonaSpinner2.setAdapter(adapter);

        ArrayAdapter<String>adapter2=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,tiposDeActivo);
        activoSpinner.setAdapter(adapter2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zonaSpinner2.getSelectedItem()!=null){
                    if (activoSpinner.getSelectedItem()!=null){
                        if (precioEditText.getText().toString().length()>0){
                            try {
                                Activo unActivo = new Activo(MainActivity.tiposDeActivo.get(activoSpinner.getSelectedItem().toString()),0);
                                tarifas.agregarTarifa(unActivo,zonaSpinner2.getSelectedItem().toString(),Double.parseDouble(precioEditText.getText().toString()));
                                Toast toast4=Toast.makeText(getApplicationContext(),"Tarifa creada con éxito",Toast.LENGTH_SHORT);
                                toast4.show();
                                finish();
                            }catch (RuntimeException exception){
                                Toast toast4=Toast.makeText(getApplicationContext(),"Tarifa ya creada",Toast.LENGTH_SHORT);
                                toast4.show();
                            }


                        }else {
                            Toast toast4=Toast.makeText(getApplicationContext(),"Ingrese un precio válido",Toast.LENGTH_SHORT);
                            toast4.show();
                        }
                    }else {
                        Toast toast4=Toast.makeText(getApplicationContext(),"Seleccione un tipo de activo válido",Toast.LENGTH_SHORT);
                        toast4.show();
                    }

                }else {
                    Toast toast4=Toast.makeText(getApplicationContext(),"Seleccione una zona válida",Toast.LENGTH_SHORT);
                    toast4.show();
                }
            }
        });

    }
}
