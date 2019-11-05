package com.rellum.moovme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.Admin;
import com.rellum.moovme.clases.Cliente;

import java.util.ArrayList;

public class AdministrarUsuariosActivity extends AppCompatActivity {
    private Spinner userTypeSpinner;
    private ListView usersListView;
    private ArrayList<String>clientes;
    private ArrayList<String>admins;
    private  ArrayAdapter<String> adapterClientes;
    private  ArrayAdapter<String> adapterAdmins;
    private ArrayList<String>clientes2;
    private View greyPanel4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        setTitle("Administrar Usuarios");

        userTypeSpinner=(Spinner)findViewById(R.id.userTypeSpinner);
        usersListView=(ListView)findViewById(R.id.usersListView);
        greyPanel4=(View)findViewById(R.id.greyPanel4);

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
                            if (!admins.get(pos).equals("admin")){
                                MainActivity.getListaDeUsuarios().deleteUserByUsername(admins.get(pos));
                            }else{
                                Toast toast=Toast.makeText(getApplicationContext(),"Error: No puedes eliminar al Default Admin",Toast.LENGTH_SHORT);
                                toast.show();
                            }

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

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                if (userTypeSpinner.getSelectedItem().toString().equals("Clientes")){
                    final Cliente cliente=(Cliente)MainActivity.getListaDeUsuarios().getUserByUsername(clientes.get(position));
                    if (cliente.isBanned()){
                        builder.setTitle("¿Desbanear cliente?");
                    }else{
                        builder.setTitle("¿Banear cliente?");
                    }
                    builder.setMessage("");

                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                           if (cliente.isBanned()){
                               cliente.undoBan();
                           }else {
                               cliente.ban();
                           }
                           updateList();
                           usersListView.setAdapter(adapterClientes);
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
                }
            }
        });



    }

    private void updateList(){
        clientes=MainActivity.getListaDeUsuarios().getAllClientes();
        clientes2=new ArrayList<>();
        for (String cliente : clientes) {
            Cliente cliente2=(Cliente)MainActivity.getListaDeUsuarios().getUserByUsername(cliente);
            if (cliente2.isBanned()){
                clientes2.add(cliente+"    "+"*BANEADO*");
            }else{
                clientes2.add(cliente);
            }
        }

        admins=MainActivity.getListaDeUsuarios().getAllAdmins();
        adapterClientes=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,clientes2);
        adapterAdmins=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,admins);
        if(userTypeSpinner.getSelectedItem().toString().equals("Clientes")){
            usersListView.setAdapter(adapterClientes);
        }else{
            usersListView.setAdapter(adapterAdmins);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.administrar_usuarios_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equals("Agregar Administrador")) {
            greyPanel4.setVisibility(View.VISIBLE);
            Intent agregarAdminIntent = new Intent(getApplicationContext(), AgregarAdminPopUp.class);
            startActivity(agregarAdminIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateList();

        greyPanel4.setVisibility(View.INVISIBLE);
    }
}
