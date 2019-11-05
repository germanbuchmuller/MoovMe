package com.rellum.moovme.clases;

import com.rellum.moovme.MainActivity;

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

    public int calcularTiempoDeAlquiler(Integer[] horaDeEntrega){
        return (60*horaDeEntrega[0]-horaDeEmision[0])+(horaDeEntrega[1]-horaDeEmision[1]);
    }

    public boolean isHoraDeEntregaEstimadaAcertada(Integer[] horaDeEntrega){
        int minutosDeAlquiler=(60*horaDeEntrega[0]-horaDeEmision[0])+(horaDeEntrega[1]-horaDeEmision[1]);
        int minutosDeAlquilerEstimados=(60*horaDeEntregaEstimada[0]-horaDeEmision[0])+(horaDeEntregaEstimada[1]-horaDeEmision[1]);
        if (-5<=minutosDeAlquiler-minutosDeAlquilerEstimados || 5<=minutosDeAlquiler-minutosDeAlquilerEstimados){
            return true;
        }else{
            return false;
        }
    }

    public int getTotalAPagar(Integer[] horaDeEntrega ){
        return (MainActivity.getTarifas().getPrice(activoAlquilado,zona))*calcularTiempoDeAlquiler(horaDeEntrega);
    }

    public int getPuntaje(Integer[] horaDeEntrega){
        if (isHoraDeEntregaEstimadaAcertada(horaDeEntrega)){
            return  (int)(activoAlquilado.getType().getPuntos()*1.2);
        }else{
            return activoAlquilado.getType().getPuntos();
        }
    }
}
