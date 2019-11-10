package com.rellum.moovme.clases;

import com.rellum.moovme.MainActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Alquiler {
    private Cliente cliente;
    private Integer[]horaDeEmision;
    private Integer[]horaDeEntregaEstimada;
    private Terminal terminalDeSalida;
    private Activo activoAlquilado;
    private Zona zona;

    public Alquiler(Cliente cliente, Integer[] horaDeEmision, Integer[] horaDeEntregaEstimada, Terminal terminalDeSalida, Activo activoAlquilado,Zona zona) {
        this.cliente = cliente;
        this.horaDeEmision = horaDeEmision;
        this.horaDeEntregaEstimada = horaDeEntregaEstimada;
        this.terminalDeSalida = terminalDeSalida;
        this.activoAlquilado = activoAlquilado;
        this.zona=zona;
    }

    public Alquiler(Cliente cliente, Integer[] horaDeEmision, Terminal terminalDeSalida, Activo activoAlquilado, Zona zona) {
        this.cliente = cliente;
        this.horaDeEmision = horaDeEmision;
        this.terminalDeSalida = terminalDeSalida;
        this.activoAlquilado = activoAlquilado;
        horaDeEntregaEstimada=new Integer[2];
        horaDeEntregaEstimada[0]=-1;
        horaDeEntregaEstimada[1]=-1;
        this.zona=zona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer[] getHoraDeEmision() {
        return horaDeEmision;
    }

    public Integer[] getHoraDeEntregaEstimada() {
        return horaDeEntregaEstimada;
    }

    public Terminal getTerminalDeSalida() {
        return terminalDeSalida;
    }

    public Activo getActivoAlquilado() {
        return activoAlquilado;
    }
/*se le pasa la hora de entrega, porque ya sabe la hora de alquiler(hora de emisison)
    y hace todos los calculos para ver a que hora hay que pagar*/
    public int calcularTiempoDeAlquiler(Integer[] horaDeEntrega){
        Calendar horaEntrega=Calendar.getInstance();
        horaEntrega.set(Calendar.HOUR,horaDeEntrega[0]);
        horaEntrega.set(Calendar.MINUTE,horaDeEntrega[1]);
        Calendar horaEmision=Calendar.getInstance();
        horaEmision.set(Calendar.HOUR,horaDeEmision[0]);
        horaEmision.set(Calendar.MINUTE,horaDeEmision[1]);
        long returnResult= TimeUnit.MILLISECONDS.toMinutes(horaEntrega.getTime().getTime()-horaEmision.getTime().getTime());
        //hace la resta y te devuelve la diferencia de tiempo en milisegundos
        if (returnResult>0){
            return (int)(returnResult);
        }else {
            return (int)(24*60+returnResult);
        }

    }

    public Zona getZona() {
        return zona;
    }

    public boolean isHoraDeEntregaEstimadaAcertada(Integer[] horaDeEntrega){
        Calendar horaEntrega=Calendar.getInstance();
        horaEntrega.set(Calendar.HOUR,horaDeEntrega[0]);
        horaEntrega.set(Calendar.MINUTE,horaDeEntrega[1]);
        Calendar horaEmision=Calendar.getInstance();
        horaEmision.set(Calendar.HOUR,horaDeEntregaEstimada[0]);
        horaEmision.set(Calendar.MINUTE,horaDeEntregaEstimada[1]);
        long returnResult= TimeUnit.MILLISECONDS.toMinutes(horaEntrega.getTime().getTime()-horaEmision.getTime().getTime());
        if (returnResult<=5 && returnResult>=-5){
            return true;
        }else{
            return false;
        }
    }
/*le pasas la hora de entrega y te devuelve el precio*/
    public double getTotalAPagar(Integer[] horaDeEntrega ){
        return (MainActivity.getTarifas().getPrice(activoAlquilado,zona))*calcularTiempoDeAlquiler(horaDeEntrega);
    }
/*se fija si la hora estimada coincide, si coincide multiplica los puntos que te da el activo por 1.2
y si no, devuelve el puntaje
 */
    public int getPuntaje(Integer[] horaDeEntrega){
        if (isHoraDeEntregaEstimadaAcertada(horaDeEntrega)){
            return  (int)(activoAlquilado.getType().getPuntos()*1.2);
        }else{
            return activoAlquilado.getType().getPuntos();
        }
    }
}
