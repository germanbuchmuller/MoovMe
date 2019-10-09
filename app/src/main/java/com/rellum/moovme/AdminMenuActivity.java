package com.rellum.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.Activo;
import com.rellum.moovme.clases.Usuario;

import java.util.ArrayList;

public class AdminMenuActivity extends AppCompatActivity {
    private Usuario loggedInUser;
    private ArrayList<Activo> listaActivos;
    private Button administrarTarifasBtn;
    private Button administrarActivosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        this.loggedInUser=MainActivity.getLoggedInUser();
        setTitle("Bienvenido, "+loggedInUser.getFullname());

        administrarTarifasBtn=(Button)findViewById(R.id.administrarTarifasBtn);
        administrarActivosBtn=(Button)findViewById(R.id.administrarActivosBtn);
        administrarActivosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent administrarActivosIntent = new Intent(getApplicationContext(), AdministrarActivosActivity.class);
                startActivity(administrarActivosIntent);
            }
        });
        administrarTarifasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent administrarTarifasIntent = new Intent(getApplicationContext(), AdministrarTarifasActivity.class);
                startActivity(administrarTarifasIntent);
            }
        });
    }
}
