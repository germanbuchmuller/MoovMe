package com.rellum.moovme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdministrarUsuariosActivity extends AppCompatActivity {
    private Spinner userTypeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        setTitle("Administrar Usuarios");

        userTypeSpinner=(Spinner)findViewById(R.id.userTypeSpinner);
        String[]userTypes= new String[2];
        userTypes[0]="Clientes";
        userTypes[1]="Administradores";
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,userTypes);
        userTypeSpinner.setAdapter(adapter);

        userTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (view.toString().equals("Clientes")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Seleccionado clientes", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
