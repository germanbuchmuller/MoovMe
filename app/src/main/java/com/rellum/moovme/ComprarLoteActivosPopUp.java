package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rellum.moovme.clases.Activo;
import com.rellum.moovme.clases.LoteDeActivos;
import com.rellum.moovme.clases.TipoDeActivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ComprarLoteActivosPopUp extends AppCompatActivity {
    private Spinner tipoDeActivoSpinner;
    private Spinner zonaAEntregarSpinner;
    private Button comprarBtn;
    private EditText cantidadDeActivosEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_lote_activos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));
        tipoDeActivoSpinner=(Spinner)findViewById(R.id.tipoDeActivoSpinner);
        zonaAEntregarSpinner=(Spinner)findViewById(R.id.zonaAEntregarSpinner);
        comprarBtn=(Button)findViewById(R.id.comprarBtn);
        cantidadDeActivosEditText=(EditText)findViewById(R.id.cantidadDeActivosEditText);

        ArrayList<String> stringList=new ArrayList<String>();
        Iterator<TipoDeActivo> iterator = MainActivity.tiposDeActivo.values().iterator();
        int num=0;
        while (iterator.hasNext()) {
            stringList.add(iterator.next().toString());
            num+=1;
        }
        Collections.sort(stringList);


        ArrayList<String> zonas = MainActivity.administradorDeZonas.getZonasListToString();


        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,stringList);
        tipoDeActivoSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2=new ArrayAdapter<>(this,R.layout.spinner_view,R.id.nameTextView,zonas);
        zonaAEntregarSpinner.setAdapter(adapter2);

        comprarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantidadDeActivosEditText.getText().toString().length()>0){
                    if (Integer.parseInt(cantidadDeActivosEditText.getText().toString())>0) {
                        if (tipoDeActivoSpinner.getSelectedItem() != null) {
                            if (zonaAEntregarSpinner.getSelectedItem() != null) {

                                String tipoDeActivoSeleccionado=tipoDeActivoSpinner.getSelectedItem().toString();
                                String zonaSeleccionada=zonaAEntregarSpinner.getSelectedItem().toString();
                                int cantidadDeActivosSeleccionada=Integer.parseInt(cantidadDeActivosEditText.getText().toString());
                                int lastIdLote = MainActivity.administradorDeZonas.getLastIdLote();

                                try {

                                    TipoDeActivo tipoDeActivo=MainActivity.tiposDeActivo.get(tipoDeActivoSeleccionado);
                                    ArrayList<Activo> activosAEntregar=new ArrayList<Activo>();
                                    for (int i = 0; i < cantidadDeActivosSeleccionada; i++) {
                                        Activo temp=new Activo(tipoDeActivo);
                                        activosAEntregar.add(temp);
                                    }

                                    LoteDeActivos loteDeActivosAEntregar = new LoteDeActivos((lastIdLote+1),activosAEntregar,zonaSeleccionada);
                                    MainActivity.administradorDeZonas.entregarLoteDeActivos(loteDeActivosAEntregar,zonaSeleccionada);

                                    Toast toast = Toast.makeText(getApplicationContext(), "Lote entregado a la zona: "+zonaSeleccionada, Toast.LENGTH_SHORT);
                                    toast.show();
                                    finish();
                                }catch (RuntimeException exception){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Error: "+zonaSeleccionada+" no contiene terminales", Toast.LENGTH_SHORT);
                                    toast.show();
                                }


                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Seleccione una zona válida", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Seleccione un tipo de activo válido", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }else {
                        Toast toast=Toast.makeText(getApplicationContext(),"Ingrese una cantidad de activos a comprar válida",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese una cantidad de activos a comprar válida",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });





    }
}
