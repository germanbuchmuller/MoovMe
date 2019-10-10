package com.rellum.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.Admin;
import com.rellum.moovme.clases.AdministradorDeZonas;
import com.rellum.moovme.clases.ListaDeUsuarios;
import com.rellum.moovme.clases.Tarifario;
import com.rellum.moovme.clases.TipoDeActivo;
import com.rellum.moovme.clases.Usuario;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static ListaDeUsuarios listaDeUsuarios;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signInTextView;
    private static Usuario loggedInUser;
    private static Tarifario tarifas;
    public static HashMap<String,TipoDeActivo>tiposDeActivo;
    public static AdministradorDeZonas administradorDeZonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarifas=new Tarifario();
        tiposDeActivo=new HashMap<String, TipoDeActivo>();
        administradorDeZonas=new AdministradorDeZonas();
        listaDeUsuarios = new ListaDeUsuarios();

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signInTextView = (TextView) findViewById(R.id.signInTextView);

        waitForAction();

    }

    private void waitForAction(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().length()==0 || passwordEditText.getText().toString().length()==0){
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese datos válidos",Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    try {
                        //Nos fijamos si existe el usuario ingresado
                        Usuario user = listaDeUsuarios.getUserByUsername(usernameEditText.getText().toString());
                        if (user.getPassword().equals(passwordEditText.getText().toString())){
                            if (user.getClass()== Admin.class) {
                                loggedInUser=user;
                                Intent logInAsAdminIntent = new Intent(getApplicationContext(), AdminMenuActivity.class);
                                startActivity(logInAsAdminIntent);
                            }else{
                                loggedInUser=user;
                                Toast toast = Toast.makeText(getApplicationContext(), "Ingresado como cliente", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }catch (RuntimeException exception){
                        Toast toast=Toast.makeText(getApplicationContext(),"Usuario no encontrado",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarClienteIntent = new Intent(getApplicationContext(), RegistrarClienteActivity.class);
                startActivity(registrarClienteIntent);
            }
        });
    }

    public static ListaDeUsuarios getListaDeUsuarios(){
        return listaDeUsuarios;
    }

    public static Usuario getLoggedInUser(){
        return loggedInUser;
    }

    public static Tarifario getTarifas(){
        return tarifas;
    }

    public static void updateTarifas(Tarifario newTarifas){
        tarifas=newTarifas;
    }


}
