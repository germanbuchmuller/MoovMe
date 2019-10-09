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

public class CrearTerminalPopUp extends AppCompatActivity {
    private Spinner zonasSpinner;
    private EditText direccionEditText;
    private Button guardarBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_terminal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));

        zonasSpinner=(Spinner)findViewById(R.id.zonaSpinner);
        direccionEditText=(EditText)findViewById(R.id.direccionEditText);
        guardarBtn3=(Button)findViewById(R.id.guardarBtn3);

        String[]zonas= new String[MainActivity.administradorDeZonas.getZonasListToString().size()];
        for (int i = 0; i < MainActivity.administradorDeZonas.getZonasListToString().size(); i++) {
            zonas[i]=MainActivity.administradorDeZonas.getZonasListToString().get(i);
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,zonas);
        zonasSpinner.setAdapter(adapter);

        guardarBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (direccionEditText.getText().toString().length()>0){
                    try{
                        MainActivity.administradorDeZonas.agregarTerminal(zonasSpinner.getSelectedItem().toString(),direccionEditText.getText().toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Terminal agregada con éxito", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }catch (RuntimeException exception){
                        Toast toast = Toast.makeText(getApplicationContext(), "Error: Terminal ya existente", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Ingrese una dirección válida", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
