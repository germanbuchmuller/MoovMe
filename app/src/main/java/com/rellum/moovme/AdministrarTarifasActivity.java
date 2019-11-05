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

import com.rellum.moovme.clases.Tarifario;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdministrarTarifasActivity extends AppCompatActivity {
    private static Tarifario tarifas;
    private ListView tarifasListView;
    private ArrayList<String>stringList;
    private View greyPanel2;
    private String selectedZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_tarifas);
        setTitle("Administrar Zonas y Tarifas");

        //Obtenemos el tarifario de MainActivity
        tarifas=MainActivity.getTarifas();
        selectedZona="-";
        tarifasListView=(ListView) findViewById(R.id.tarifasListView);
        greyPanel2=(View)findViewById(R.id.greyPanel2);
        updateListView();

        tarifasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedZona=view.toString();
                tarifasListView.setVisibility(View.INVISIBLE);
            }
        });
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        tarifasListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                builder.setTitle("¿Eliminar zona?");
                builder.setMessage("");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                       MainActivity.administradorDeZonas.eliminarZona(stringList.get(position));
                       updateListView();
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

    public void updateListView(){
        //Obtenemos todas las zonas guardadas en el tarifario
        stringList=MainActivity.administradorDeZonas.getZonasListToString();


        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,stringList);
        tarifasListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_manu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equals("Agregar Tarifa")){
            greyPanel2.setVisibility(View.VISIBLE);
            Intent agregarTarifaIntent=new Intent(getApplicationContext(), AgregarTarifaActivity.class);
            startActivity(agregarTarifaIntent);
        }else if (item.getTitle().toString().equals("Agregar Zona")){
            greyPanel2.setVisibility(View.VISIBLE);
            Intent crearZonaIntent=new Intent(getApplicationContext(), CrearZonaPopUp.class);
            startActivity(crearZonaIntent);
        }else if (item.getTitle().toString().equals("Agregar Terminal a Zona")){
            greyPanel2.setVisibility(View.VISIBLE);
            Intent crearTerminalIntent=new Intent(getApplicationContext(), CrearTerminalPopUp.class);
            startActivity(crearTerminalIntent);
        }else if(item.getTitle().toString().equals("Agregar Puntaje")){
            greyPanel2.setVisibility(View.VISIBLE);
            Intent agregarPuntajeIntent=new Intent(getApplicationContext(), AgregarPuntajePopUp.class);
            startActivity(agregarPuntajeIntent);
        }



        return super.onOptionsItemSelected(item);
    }

    public static Tarifario getTarifas(){
        return tarifas;
    }

    public static void updateTarifas(Tarifario newTarifas){
        tarifas=newTarifas;
        MainActivity.updateTarifas(newTarifas);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateListView();
        greyPanel2.setVisibility(View.INVISIBLE);
    }

}
