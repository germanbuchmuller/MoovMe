package com.rellum.moovme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarAdminPopUp extends AppCompatActivity {
    private EditText adminPasswordEditText;
    private EditText adminUsernameEditText;
    private EditText fullnameEditText;
    private Button guardarAdmingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_admin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));

        guardarAdmingBtn=(Button)findViewById(R.id.guardarAdmingBtn);
        adminPasswordEditText=(EditText)findViewById(R.id.adminPasswordEditText);
        adminUsernameEditText=(EditText)findViewById(R.id.adminUsernameEditText);
        fullnameEditText=(EditText)findViewById(R.id.fullnameEditText);

        guardarAdmingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminPasswordEditText.getText().toString().length()>0 && adminUsernameEditText.getText().toString().length()>0 && fullnameEditText.getText().toString().length()>0){
                    try{
                        MainActivity.getAdministradorDeUsuarios().agregarAdmin(fullnameEditText.getText().toString(),adminUsernameEditText.getText().toString(),adminPasswordEditText.getText().toString());
                        Toast toast=Toast.makeText(getApplicationContext(),"Administrador agregado con éxito",Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }catch (RuntimeException exception){
                        Toast toast=Toast.makeText(getApplicationContext(),"Error: Usuario ya registrado",Toast.LENGTH_SHORT);
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
