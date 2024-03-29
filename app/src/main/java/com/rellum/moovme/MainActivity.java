package com.rellum.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rellum.moovme.clases.Admin;
import com.rellum.moovme.clases.AdministradorDeZonas;
import com.rellum.moovme.clases.Cliente;
import com.rellum.moovme.clases.AdministradorDeUsuarios;
import com.rellum.moovme.clases.OperadorDePuntaje;
import com.rellum.moovme.clases.Tarifario;
import com.rellum.moovme.clases.Terminal;
import com.rellum.moovme.clases.TipoDeActivo;
import com.rellum.moovme.clases.Usuario;
import com.rellum.moovme.clases.Zona;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static AdministradorDeUsuarios administradorDeUsuarios;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signInTextView;
    private static Usuario loggedInUser;
    private static Tarifario tarifas;
    public static HashMap<String,TipoDeActivo>tiposDeActivo;
    public static AdministradorDeZonas administradorDeZonas;
    private static Zona zonaActualDelCliente;
    private static Terminal termialActualDelCliente;
    private static OperadorDePuntaje operadorDePuntaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarifas=new Tarifario();
        tiposDeActivo=new HashMap<String, TipoDeActivo>();
        administradorDeZonas=new AdministradorDeZonas();
        administradorDeUsuarios = new AdministradorDeUsuarios();
        operadorDePuntaje=new OperadorDePuntaje();

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
                        Usuario user = administradorDeUsuarios.getUserByUsername(usernameEditText.getText().toString());
                        if (user.getPassword().equals(passwordEditText.getText().toString())){
                            if (user.getClass()== Admin.class) {
                                loggedInUser=user;
                                Intent logInAsAdminIntent = new Intent(getApplicationContext(), AdminMenuActivity.class);
                                startActivity(logInAsAdminIntent);
                            }else{
                                loggedInUser=(Cliente)user;
                                Cliente cliente=(Cliente)user;
                                if (!cliente.isBanned()) {
                                    if (loggedInUser.getAlquiler() == null) {
                                        Intent logInAsUserIntent = new Intent(getApplicationContext(), SeleccionarZonaClienteActivity.class);
                                        startActivity(logInAsUserIntent);
                                    } else {
                                        setZonaActualDelCliente(loggedInUser.getAlquiler().getZona());
                                        administradorDeZonas.ingresarClienteAZona((Cliente) getLoggedInUser(), getZonaActualDelCliente());
                                        Intent logInAsUser2Intent = new Intent(getApplicationContext(), MenuClientesActivity.class);
                                        startActivity(logInAsUser2Intent);
                                    }
                                }else {
                                    Toast toast=Toast.makeText(getApplicationContext(),"Error: Usuario Baneado",Toast.LENGTH_SHORT);
                                    toast.show();
                                }

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

    public static AdministradorDeUsuarios getAdministradorDeUsuarios(){
        return administradorDeUsuarios;
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

    public static Zona getZonaActualDelCliente() {
        return zonaActualDelCliente;
    }

    public static void setZonaActualDelCliente(Zona zonaActualDelCliente) {
        MainActivity.zonaActualDelCliente = zonaActualDelCliente;
    }

    public static Terminal getTermialActualDelCliente() {
        return termialActualDelCliente;
    }

    public static void setTermialActualDelCliente(Terminal termialActualDelCliente) {
        MainActivity.termialActualDelCliente = termialActualDelCliente;
    }

    public static OperadorDePuntaje getOperadorDePuntaje() {
        return operadorDePuntaje;
    }

    public static void updatePuntajes(OperadorDePuntaje nuevoOperadorDePuntaje){
        operadorDePuntaje=nuevoOperadorDePuntaje;
    }
}
