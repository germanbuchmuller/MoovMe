package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rellum.moovme.clases.TipoDeActivo;
import com.rellum.moovme.clases.Zona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarPuntajePopUp extends AppCompatActivity {
    private Spinner activoSpinner;
    private Spinner zonasSpinner2;
    private EditText puntajeEditText;
    private Button guardarPuntajeBtn;
    private EditText descuentoEditText;
    private ArrayList<String>tiposDeActivos;
    private ArrayList<String>zonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_puntaje);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));

        activoSpinner=(Spinner)findViewById(R.id.activoSpinner);
        zonasSpinner2=(Spinner)findViewById(R.id.zonasSpinner2);
        puntajeEditText=(EditText)findViewById(R.id.puntajeEditText);
        guardarPuntajeBtn=(Button)findViewById(R.id.guardarPuntajeBtn);
        descuentoEditText=(EditText)findViewById(R.id.descuentoEditText);

        updateSpinners();

        guardarPuntajeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (puntajeEditText.getText().toString().length()>0 && descuentoEditText.getText().toString().length()>0 ){
                    Zona zonaSelected=MainActivity.administradorDeZonas.getZona(zonasSpinner2.getSelectedItem().toString());
                    TipoDeActivo activoSelected=MainActivity.tiposDeActivo.get(activoSpinner.getSelectedItem().toString());
                    int puntaje=Integer.parseInt(puntajeEditText.getText().toString());
                    int descuento=Integer.parseInt(descuentoEditText.getText().toString());
                    MainActivity.getOperadorDePuntaje().agregarPuntaje(zonaSelected,activoSelected,puntaje,descuento);
                    finish();
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese datos válidos",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });



    }

    private void updateSpinners(){
        tiposDeActivos=new ArrayList<String>();
        Iterator<TipoDeActivo> iterator = MainActivity.tiposDeActivo.values().iterator();
        while (iterator.hasNext()) {
            tiposDeActivos.add(iterator.next().toString());

        }
        Collections.sort(tiposDeActivos);

        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,tiposDeActivos);
        activoSpinner.setAdapter(adapter);

        zonas=MainActivity.administradorDeZonas.getZonasListToString();
        ArrayAdapter<String> zonasAdapter;
        zonasAdapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,zonas);
        zonasSpinner2.setAdapter(zonasAdapter);
    }
}
