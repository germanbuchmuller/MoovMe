package com.rellum.moovme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rellum.moovme.clases.Cliente;
import com.rellum.moovme.clases.TipoDeActivo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MenuClientesActivity extends AppCompatActivity {
    private ArrayAdapter<String>rankingAdapter;
    private ArrayAdapter<String>activosAdapter;
    private ArrayList<TipoDeActivo>activosDisponibles;
    private HashMap<Cliente,Integer> ranking;
    private ListView rankingListView;
    private ListView activosListView;
    private View greyPanel3;
    private static TipoDeActivo tipoDeActivoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_clientes);
        setTitle("Bienvenido, "+MainActivity.getLoggedInUser().getFullname());
        rankingListView=(ListView)findViewById(R.id.rankingListView);
        activosListView=(ListView)findViewById(R.id.activosListView);
        greyPanel3=(View)findViewById(R.id.greyPanel3);
        updateLists();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        activosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                builder.setTitle("¿Deseas alquilar un/una "+activosDisponibles.get(position).toString()+"?");
                builder.setMessage("");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        greyPanel3.setVisibility(View.VISIBLE);
                        tipoDeActivoSeleccionado=activosDisponibles.get(position);
                        Intent menuClienteIntent=new Intent(getApplicationContext(),EstablecerHoraDeEntregaPopUp.class);
                        startActivity(menuClienteIntent);
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
        });
    }

    public static TipoDeActivo getTipoDeActivoSeleccionado() {
        return tipoDeActivoSeleccionado;
    }

    private void updateLists(){
        ranking=MainActivity.administradorDeZonas.getRanking(MainActivity.getZonaActualDelCliente());
        ArrayList<String>tempRanking=new ArrayList<>();

        Iterator iterator=ranking.keySet().iterator();
        while (iterator.hasNext()){
            Cliente cliente=(Cliente)iterator.next();
            tempRanking.add(cliente.getAlias()+"\t"+"\t Puntos: "+ranking.get(cliente));
        }

        rankingAdapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,tempRanking);
        rankingListView.setAdapter(rankingAdapter);
        activosDisponibles=MainActivity.getTermialActualDelCliente().getTiposDeActivoDisponible();
        ArrayList<String>tempActivos=new ArrayList<>();
        for (TipoDeActivo activosDisponible : activosDisponibles) {
            tempActivos.add(activosDisponible.toString());
        }
        if (tempActivos.size()==0){
            tempActivos.add("No hay activos disponibles en esta terminal");
        }
        activosAdapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,tempActivos);
        activosListView.setAdapter(activosAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateLists();
        greyPanel3.setVisibility(View.INVISIBLE);
    }
}
