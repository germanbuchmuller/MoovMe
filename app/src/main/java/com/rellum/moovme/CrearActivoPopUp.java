package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rellum.moovme.clases.TipoDeActivo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CrearActivoPopUp extends AppCompatActivity {
    private EditText tipoDeActivoEditText;
    private Button guardarBtn;
    private EditText puntosEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_activo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.3));

        tipoDeActivoEditText=(EditText)findViewById(R.id.tipoDeActivoEditText);
        guardarBtn=(Button)findViewById(R.id.guardarBtn);
        puntosEditText=(EditText)findViewById(R.id.puntosEditText);

        guardarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipoDeActivoEditText.getText().toString().length()>0 && puntosEditText.getText().toString().length()>0){
                    MainActivity.tiposDeActivo.put(tipoDeActivoEditText.getText().toString(),new TipoDeActivo(tipoDeActivoEditText.getText().toString(),Integer.parseInt(puntosEditText.getText().toString())));
                    Toast toast=Toast.makeText(getApplicationContext(),"Activo creado con éxito",Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese datos válidos",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
