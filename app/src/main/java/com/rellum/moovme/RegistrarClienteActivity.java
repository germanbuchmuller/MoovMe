package com.rellum.moovme;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarClienteActivity extends AppCompatActivity {
    private EditText nombreYApellidoEditText;
    private EditText aliasEditText;
    private EditText usernameRegisterEditText;
    private EditText passwordRegisterEditText;
    private EditText phoneEditText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
        setTitle("Registrar Cliente");

        nombreYApellidoEditText = (EditText) findViewById(R.id.nombreYApellidoEditText);
        aliasEditText = (EditText) findViewById(R.id.aliasEditText);
        usernameRegisterEditText = (EditText) findViewById(R.id.usernameRegisterEditText);
        passwordRegisterEditText = (EditText) findViewById(R.id.passwordRegisterEditText);
        passwordRegisterEditText = (EditText) findViewById(R.id.passwordRegisterEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        signInButton = (Button) findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreYApellido = nombreYApellidoEditText.getText().toString();
                String alias = aliasEditText.getText().toString();
                String username = usernameRegisterEditText.getText().toString();
                String password = passwordRegisterEditText.getText().toString();
                String phone= phoneEditText.getText().toString();
                if (nombreYApellido.length()>0 && alias.length()>0 && username.length()>0 && password.length()>0 && phone.length()>0){
                    int phoneNumber = Integer.parseInt(phone);
                    try {
                        MainActivity.getListaDeUsuarios().agregarCliente(nombreYApellido,alias,username,password,phoneNumber);
                        Toast toast=Toast.makeText(getApplicationContext(),"Usuario registrado con éxito",Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }catch (RuntimeException exception){
                        Toast toast=Toast.makeText(getApplicationContext(),"Usuario ya registrado",Toast.LENGTH_SHORT);
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
