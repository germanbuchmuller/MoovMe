package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearZonaPopUp extends AppCompatActivity {

    private EditText nombreDeZonaEditText;
    private Button guardarBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_zona);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.3));
        nombreDeZonaEditText=(EditText)findViewById(R.id.nombreDeZonaEditText);
        guardarBtn2=(Button)findViewById(R.id.guardarBtn2);

        guardarBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDeZonaEditText.getText().toString().length()>0){
                    try {
                        MainActivity.administradorDeZonas.agregarZona(nombreDeZonaEditText.getText().toString());
                        Toast toast = Toast.makeText(getApplicationContext(), "Zona creada con éxito", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }catch (RuntimeException exception){
                        Toast toast = Toast.makeText(getApplicationContext(), "Zona ya existente", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese datos válidos",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }


}
