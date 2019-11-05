package com.rellum.moovme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.rellum.moovme.clases.Alquiler;
import com.rellum.moovme.clases.Cliente;

import java.util.Calendar;
import java.util.Date;

public class EstablecerHoraDeEntregaPopUp extends AppCompatActivity {
    private EditText horaEditText;
    private EditText minutoEditText;
    private CheckBox checkBoxHoraEntrega;
    private Button confirmarAlquilerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer_fecha_de_entrega);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));
        horaEditText=(EditText)findViewById(R.id.horaEditText);
        minutoEditText=(EditText)findViewById(R.id.minutoEditText);
        checkBoxHoraEntrega=(CheckBox)findViewById(R.id.checkBoxHoraEntrega);
        confirmarAlquilerBtn=(Button)findViewById(R.id.confirmarAlquilerBtn);
        horaEditText.setEnabled(false);
        minutoEditText.setEnabled(false);

        checkBoxHoraEntrega.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    horaEditText.setEnabled(true);
                    minutoEditText.setEnabled(true);
                }else{
                    horaEditText.setEnabled(false);
                    minutoEditText.setEnabled(false);
                }
            }
        });

        confirmarAlquilerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer[]time= new Integer[2];
                time[0] = Calendar.getInstance().getTime().getHours();
                time[1] = Calendar.getInstance().getTime().getMinutes();
                Alquiler alquiler;
                if (checkBoxHoraEntrega.isChecked()){
                    Integer[]time2= new Integer[2];
                    time2[0]= Integer.parseInt(horaEditText.getText().toString());
                    time2[1]= Integer.parseInt(minutoEditText.getText().toString());
                    alquiler=new Alquiler((Cliente) MainActivity.getLoggedInUser(),time,time2,MainActivity.getTermialActualDelCliente(),MainActivity.getTermialActualDelCliente().getActivo(MenuClientesActivity.getTipoDeActivoSeleccionado()));
                }else {
                    alquiler=new Alquiler((Cliente) MainActivity.getLoggedInUser(),time,MainActivity.getTermialActualDelCliente(),MainActivity.getTermialActualDelCliente().getActivo(MenuClientesActivity.getTipoDeActivoSeleccionado()));
                }
                MainActivity.getLoggedInUser().setAlquiler(alquiler);
            }
        });


    }
}
