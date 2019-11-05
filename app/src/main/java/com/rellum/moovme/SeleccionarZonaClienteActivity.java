package com.rellum.moovme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rellum.moovme.clases.Cliente;
import com.rellum.moovme.clases.Zona;

import java.util.ArrayList;

public class SeleccionarZonaClienteActivity extends AppCompatActivity {
    private ListView zonasASeleccionarListView;
    private ArrayAdapter<String> zonasAdapater;
    private ArrayAdapter<String> terminalesAdapter;
    private ArrayList<String> zonas;
    private ArrayList<String> terminales;
    private String estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_zona_cliente);
        setTitle("Seleccione su zona");

        estado="Seleccionando Zona";


            zonasASeleccionarListView=(ListView)findViewById(R.id.zonasASeleccionarListView);
            zonas=MainActivity.administradorDeZonas.getZonasListToString();
            zonasAdapater=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,zonas);

            zonasASeleccionarListView.setAdapter(zonasAdapater);

            zonasASeleccionarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (estado.equals("Seleccionando Zona")){
                        MainActivity.setZonaActualDelCliente(MainActivity.administradorDeZonas.getZona(zonas.get(position)));
                        MainActivity.administradorDeZonas.ingresarClienteAZona((Cliente)MainActivity.getLoggedInUser(),MainActivity.getZonaActualDelCliente());
                        actualizarListViewConTerminales();
                        estado="Seleccionando Terminal";
                    }else if (estado.equals("Seleccionando Terminal")){
                        MainActivity.setTermialActualDelCliente(MainActivity.administradorDeZonas.getTerminal(terminales.get(position)));
                        Toast toast = Toast.makeText(getApplicationContext(),"Terminal seleccionada: "+MainActivity.getTermialActualDelCliente().getDireccion(),Toast.LENGTH_LONG);
                        toast.show();
                        Intent menuClienteIntent=new Intent(getApplicationContext(),MenuClientesActivity.class);
                        startActivity(menuClienteIntent);
                    }

                }
            });


    }

    private void actualizarListViewConTerminales(){
        setTitle("Seleccione terminal en "+MainActivity.getZonaActualDelCliente().getNombre());
        terminales=MainActivity.administradorDeZonas.getDireccionTerminalesListByZona(MainActivity.getZonaActualDelCliente());
        terminalesAdapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,terminales);

        zonasASeleccionarListView.setAdapter(terminalesAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (MainActivity.getLoggedInUser().getAlquiler()!=null){
           finish();
        }
    }
}
