package com.rellum.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rellum.moovme.clases.TipoDeActivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AdministrarActivosActivity extends AppCompatActivity {
    private View greyPanel;
    private ListView tiposDeActivoListView;
    private ArrayList<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_activos);
        setTitle("Administrar Activos");
        greyPanel=(View)findViewById(R.id.greyPanel);
        tiposDeActivoListView=(ListView)findViewById(R.id.tiposDeActivoListView);

       updateListView();

    }

    public void updateListView(){
        stringList=new ArrayList<String>();
        Iterator<TipoDeActivo> iterator = MainActivity.tiposDeActivo.values().iterator();
        int num=0;
        while (iterator.hasNext()) {
            stringList.add(iterator.next().toString());
            num+=1;
        }
        Collections.sort(stringList);

        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,R.layout.list_view_info,R.id.nameTextView,stringList);
        tiposDeActivoListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.administrar_activos_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equals("Crear tipo de activo")){
            greyPanel.setVisibility(View.VISIBLE);
            Intent crearActivoIntent=new Intent(getApplicationContext(), CrearActivoPopUp.class);
            startActivity(crearActivoIntent);
        }else if (item.getTitle().toString().equals("Comprar lote de activos")){
            greyPanel.setVisibility(View.VISIBLE);
            Intent comprarLoteIntent=new Intent(getApplicationContext(), ComprarLoteActivosPopUp.class);
            startActivity(comprarLoteIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        greyPanel.setVisibility(View.INVISIBLE);
        updateListView();

    }

}
