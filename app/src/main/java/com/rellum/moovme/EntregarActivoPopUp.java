package com.rellum.moovme;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rellum.moovme.clases.Activo;
import com.rellum.moovme.clases.Alquiler;
import com.rellum.moovme.clases.Cliente;
import com.rellum.moovme.clases.Terminal;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EntregarActivoPopUp extends AppCompatActivity {
    private TextView horaDeEntregaTextView;
    private TextView minutosTextView;
    private TextView precioTextView;
    private EditText horaEntregaEditText;
    private EditText minutoEntregaEditText;
    private Button siguienteButton;
    private CheckBox canjearPuntosCheckBox;
    private Button pagarBtn;
    private String estado;
    private Integer[]horaDeEntrega;
    private TextView entregarEnTerminalTextView;
    private Spinner terminalAEntregarSpinner;
    private ArrayList<String> terminales;
    private ArrayAdapter<String> terminalesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregar_activo);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.4));

        horaDeEntregaTextView=(TextView)findViewById(R.id.horaDeEntregaTextView);
        minutosTextView=(TextView)findViewById(R.id.minutosTextView);
        precioTextView=(TextView)findViewById(R.id.precioTextView);
        horaEntregaEditText=(EditText)findViewById(R.id.horaEntregaEditText);
        minutoEntregaEditText=(EditText)findViewById(R.id.minutoEntregaEditText);
        siguienteButton=(Button)findViewById(R.id.siguienteButton);
        canjearPuntosCheckBox=(CheckBox)findViewById(R.id.canjearPuntosCheckBox);
        pagarBtn=(Button)findViewById(R.id.pagarBtn);
        entregarEnTerminalTextView=(TextView)findViewById(R.id.entregarEnTerminalTextView);
        terminalAEntregarSpinner=(Spinner)findViewById(R.id.terminalAEntregarSpinner);

        terminales=MainActivity.administradorDeZonas.getDireccionTerminalesListByZona(MainActivity.getZonaActualDelCliente());
        terminalesAdapter=new ArrayAdapter<String>(this,R.layout.spinner_view,R.id.nameTextView,terminales);
        terminalAEntregarSpinner.setAdapter(terminalesAdapter);

        estado="Estableciendo hora";

        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (horaEntregaEditText.getText().toString().length()>0 && minutoEntregaEditText.getText().toString().length()>0){
                    if (Integer.parseInt(horaEntregaEditText.getText().toString())<24&& Integer.parseInt(minutoEntregaEditText.getText().toString())<60){
                        horaDeEntrega = new  Integer[2];
                        horaDeEntrega[0]=Integer.parseInt(horaEntregaEditText.getText().toString());
                        horaDeEntrega[1]=Integer.parseInt(minutoEntregaEditText.getText().toString());

                        long precioAPagar=calcularPrecioAPagar();
                        precioTextView.setText("Precio: $"+precioAPagar);
                        canjearPuntosCheckBox.setText("¿Canjear "+getPuntajeACanjear()[0]+" puntos para obtener %"+getPuntajeACanjear()[1]+" de descuento?");
                        if (getPuntosDelCliente()>=getPuntajeACanjear()[0]){
                            canjearPuntosCheckBox.setEnabled(true);
                        }else{
                            canjearPuntosCheckBox.setEnabled(false);
                        }
                        canjearPuntosCheckBox.setVisibility(View.VISIBLE);
                        siguienteButton.setVisibility(View.INVISIBLE);
                        pagarBtn.setVisibility(View.VISIBLE);
                        minutoEntregaEditText.setVisibility(View.INVISIBLE);
                        horaEntregaEditText.setVisibility(View.INVISIBLE);
                        horaDeEntregaTextView.setVisibility(View.INVISIBLE);
                        precioTextView.setVisibility(View.VISIBLE);
                        minutosTextView.setVisibility(View.VISIBLE);
                        entregarEnTerminalTextView.setVisibility(View.VISIBLE);
                        terminalAEntregarSpinner.setVisibility(View.VISIBLE);
                        minutosTextView.setText("Minutos utilizado: "+getMinutosUtilizado());



                    }else{
                        Toast toast=Toast.makeText(getApplicationContext(),"Ingrese una hora válida",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Ingrese una hora válida",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        canjearPuntosCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    precioTextView.setText("Precio: $"+calcularPrecioFinalConDescuento());
                }else{
                    precioTextView.setText("Precio: $"+calcularPrecioAPagar());
                }
            }
        });

        pagarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagar();
                Toast toast=Toast.makeText(getApplicationContext(),"Pago realizado con éxito",Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });



    }

    private void pagar(){
        Alquiler alquiler=MainActivity.getLoggedInUser().getAlquiler();
        Terminal terminal= MainActivity.getZonaActualDelCliente().getTerminalByDireccion(terminalAEntregarSpinner.getSelectedItem().toString());
        terminal.agregarActivo(MainActivity.getLoggedInUser().getAlquiler().getActivoAlquilado());
        if (canjearPuntosCheckBox.isChecked()){
            MainActivity.getZonaActualDelCliente().consumirPuntosAlCliente((Cliente)MainActivity.getLoggedInUser(),getPuntajeACanjear()[0]);
        }
        MainActivity.getZonaActualDelCliente().agregarPuntosACliente((Cliente)MainActivity.getLoggedInUser(),alquiler.getPuntaje(horaDeEntrega));
        MainActivity.getLoggedInUser().setAlquiler(null);
    }

    private int calcularPrecioFinalConDescuento(){
        return (int)((MainActivity.getLoggedInUser().getAlquiler().getTotalAPagar(horaDeEntrega))*((100-getPuntajeACanjear()[1])*0.01));
    }

    private int getPuntosDelCliente(){
        return MainActivity.getZonaActualDelCliente().getPuntajeCliente((Cliente)MainActivity.getLoggedInUser());
    }

    private Integer[] getPuntajeACanjear(){
        try{
            return MainActivity.getOperadorDePuntaje().getPuntaje(MainActivity.getZonaActualDelCliente(),getActivoAlquilado().getType());
        }catch (Exception exception){
            Integer[]returnValue=new Integer[2];
            returnValue[0]=0;
            returnValue[1]=0;
            return returnValue;
        }

    }

    private Activo getActivoAlquilado(){
        return MainActivity.getLoggedInUser().getAlquiler().getActivoAlquilado();
    }

    private boolean cumplioHoraEstimada(){
        return MainActivity.getLoggedInUser().getAlquiler().isHoraDeEntregaEstimadaAcertada(horaDeEntrega);
    }

    private int calcularPrecioAPagar(){
        return (int)(MainActivity.getLoggedInUser().getAlquiler().getTotalAPagar(horaDeEntrega));
    }

    private long getMinutosUtilizado(){
        return MainActivity.getLoggedInUser().getAlquiler().calcularTiempoDeAlquiler(horaDeEntrega);
    }
}
