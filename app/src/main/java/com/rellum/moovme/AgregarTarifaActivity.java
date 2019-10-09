package com.rellum.moovme;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.Tarifario;

public class AgregarTarifaActivity extends AppCompatActivity {
    private Tarifario tarifas;
    private EditText zonaEditText;
    private EditText precioEditText;
    private Spinner activoSpinner;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarifa);
        setTitle("Agregar Tarifa");
        //obtenemos el tarifario de la actividad AdministrarTarifasActivity
        tarifas= AdministrarTarifasActivity.getTarifas();

        zonaEditText = (EditText) findViewById(R.id.zonaEditText);
        precioEditText = (EditText) findViewById(R.id.precioEditText);
        activoSpinner = (Spinner) findViewById(R.id.activoSpinner);
        addBtn = (Button) findViewById(R.id.addBtn);
        String[]tiposDeActivo= new String[MainActivity.tiposDeActivo.size()];
        for (int i = 0; i < MainActivity.tiposDeActivo.size(); i++) {
            tiposDeActivo[i]=MainActivity.tiposDeActivo.get(i).toString();
        }

        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,tiposDeActivo);
        activoSpinner.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zonaEditText.getText().toString().length()>0 && precioEditText.getText().toString().length()>0){
                    String zona = zonaEditText.getText().toString();
                    String firstLetter= ""+zona.charAt(0);
                    firstLetter=firstLetter.toUpperCase();
                    zona=zona.substring(1,zona.length());
                    zona=firstLetter+zona;
                    int precio= Integer.parseInt(precioEditText.getText().toString());
                    /*
                    switch (activoSpinner.getSelectedItem().toString()){
                        case "Auto":
                            try {
                                tarifas.agregarTarifa(new Auto(0),zona,precio);
                                Toast toast=Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_SHORT);
                                toast.show();
                            }catch (RuntimeException exception){
                                Toast toast=Toast.makeText(getApplicationContext(),"Error: tarifa ya guardada",Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            break;
                        case "Bicicleta":
                            try {
                                tarifas.agregarTarifa(new Bicicleta(0),zona,precio);
                                Toast toast=Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_SHORT);
                                toast.show();
                            }catch (RuntimeException exception){
                                Toast toast=Toast.makeText(getApplicationContext(),"Error: tarifa ya guardada",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            break;
                        case "Monopatín":
                            try {
                                tarifas.agregarTarifa(new Monopatin(0),zona,precio);
                                Toast toast=Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_SHORT);
                                toast.show();
                            }catch (RuntimeException exception){
                                Toast toast=Toast.makeText(getApplicationContext(),"Error: tarifa ya guardada",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            break;
                        case "Moto":
                            try {
                                tarifas.agregarTarifa(new Moto(0),zona,precio);
                                Toast toast=Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_SHORT);
                                toast.show();
                            }catch (RuntimeException exception){
                                Toast toast=Toast.makeText(getApplicationContext(),"Error: tarifa ya guardada",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            break;
                    }

                     */

                }else {
                    Toast toast4=Toast.makeText(getApplicationContext(),"Ingrese datos válidos",Toast.LENGTH_SHORT);
                    toast4.show();
                }
            }
        });

    }
}
