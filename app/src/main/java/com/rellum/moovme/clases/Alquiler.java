package com.rellum.moovme.clases;

public class Alquiler {
    private Cliente cliente;
    private Integer[]horaDeEmision;
    private Integer[]horaDeEntregaEstimada;
    private Terminal terminalDeSalida;
    private Activo activoAlquilado;

    public Alquiler(Cliente cliente, Integer[] horaDeEmision, Integer[] horaDeEntregaEstimada, Terminal terminalDeSalida, Activo activoAlquilado) {
        this.cliente = cliente;
        this.horaDeEmision = horaDeEmision;
        this.horaDeEntregaEstimada = horaDeEntregaEstimada;
        this.terminalDeSalida = terminalDeSalida;
        this.activoAlquilado = activoAlquilado;
    }

    public Alquiler(Cliente cliente, Integer[] horaDeEmision, Terminal terminalDeSalida, Activo activoAlquilado) {
        this.cliente = cliente;
        this.horaDeEmision = horaDeEmision;
        this.terminalDeSalida = terminalDeSalida;
        this.activoAlquilado = activoAlquilado;
        horaDeEntregaEstimada=new Integer[2];
        horaDeEntregaEstimada[0]=-1;
        horaDeEntregaEstimada[1]=-1;
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

    public int getTotalAPagar(int precioPorMinuto,Integer[] horaDeEntrega ){
        return precioPorMinuto*calcularTiempoDeAlquiler(horaDeEntrega);
    }
}
