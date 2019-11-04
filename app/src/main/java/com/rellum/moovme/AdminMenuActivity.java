package com.rellum.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rellum.moovme.clases.Usuario;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMenuActivity extends AppCompatActivity {
    private Usuario loggedInUser;
    private Button administrarTarifasBtn;
    private Button administrarActivosBtn;
    private Button administrarUsuariosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);


        this.loggedInUser=MainActivity.getLoggedInUser();
        setTitle("Bienvenido, "+loggedInUser.getFullname());

        administrarTarifasBtn=(Button)findViewById(R.id.administrarTarifasBtn);
        administrarActivosBtn=(Button)findViewById(R.id.administrarActivosBtn);
        administrarUsuariosBtn=(Button)findViewById(R.id.administrarUsuariosBtn);

        administrarUsuariosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent administrarUsuariosIntent=new Intent(getApplicationContext(),AdministrarUsuariosActivity.class);
                startActivity(administrarUsuariosIntent);
            }
        });

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
