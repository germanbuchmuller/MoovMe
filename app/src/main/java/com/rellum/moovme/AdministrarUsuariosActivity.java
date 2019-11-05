package com.rellum.moovme;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AdministrarUsuariosActivity extends AppCompatActivity {
    private Spinner userTypeSpinner;
    private ListView usersListView;
    private ArrayList<String>clientes;
    private ArrayList<String>admins;
    private  ArrayAdapter<String> adapterClientes;
    private  ArrayAdapter<String> adapterAdmins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        setTitle("Administrar Usuarios");

        userTypeSpinner=(Spinner)findViewById(R.id.userTypeSpinner);
        usersListView=(ListView)findViewById(R.id.usersListView);

        String[]userTypes= new String[2];
        userTypes[0]="Clientes";
        userTypes[1]="Administradores";
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,userTypes);
        userTypeSpinner.setAdapter(adapter);


        updateList();
        usersListView.setAdapter(adapterClientes);

        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Clientes")){

                    usersListView.setAdapter(adapterClientes);
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Seleccionado Administradores",Toast.LENGTH_SHORT);
                    usersListView.setAdapter(adapterAdmins);
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        usersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, final View view,
                                           final int pos, long id) {


                builder.setTitle("¿Eliminar usuario?");
                builder.setMessage("");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (userTypeSpinner.getSelectedItem().toString().equals("Clientes")){
                            MainActivity.getListaDeUsuarios().deleteUserByUsername(clientes.get(pos));
                        }else{
                            MainActivity.getListaDeUsuarios().deleteUserByUsername(admins.get(pos));
                        }

                        updateList();
                        if (userTypeSpinner.getSelectedItem().toString().equals("Clientes")){
                            usersListView.setAdapter(adapterClientes);
                        }else{
                            usersListView.setAdapter(adapterAdmins);
                        }
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });



    }

    private void updateList(){
        clientes=MainActivity.getListaDeUsuarios().getAllClientes();
        admins=MainActivity.getListaDeUsuarios().getAllAdmins();
        adapterClientes=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,clientes);
        adapterAdmins=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,admins);
    }
}
