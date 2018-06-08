/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hyperbolicbaby.uady;

/**
 *
 * @author Christian Tuyub
 *
 */

public class EquipoFutbol {
    
    private String nombreEquipo;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    
    private int partidosJugados;
    private int puntajeEquipo;
    private int diferenciaGoleo;
    private int posicion;

    public EquipoFutbol() {
        
    }

    public EquipoFutbol(String nombreEquipo, int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesAFavor, int golesEnContra) {
        this.nombreEquipo = nombreEquipo;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados = partidosEmpatados;
        this.partidosPerdidos = partidosPerdidos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        
        this.partidosJugados = 0;
        this.puntajeEquipo = 0;
        this.diferenciaGoleo = 0;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }
    
    //Calculados por TableroCalculador, inicializados en 0 desde constructor

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPuntajeEquipo() {
        return puntajeEquipo;
    }

    public void setPuntajeEquipo(int puntajeEquipo) {
        this.puntajeEquipo = puntajeEquipo;
    }

    public int getDiferenciaGoleo() {
        return diferenciaGoleo;
    }

    public void setDiferenciaGoleo(int diferenciaGoleo) {
        this.diferenciaGoleo = diferenciaGoleo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "EquipoFutbol: \n" + "nombreEquipo: " + this.getNombreEquipo() + ".\npartidosGanados: " + this.getPartidosGanados() + ".\npartidosEmpatados: " + this.getPartidosEmpatados()
                                  + ".\npartidosPerdidos: " + this.getPartidosPerdidos() + ".\ngolesAFavor: " + this.getGolesAFavor() + ".\ngolesEnContra: " + this.getGolesEnContra() 
                                  + ".\npartidosJugados: " + this.getPartidosJugados() + ".\npuntajeEquipo: " + this.getPuntajeEquipo() + ".\ndiferenciaGoleo: " + this.getDiferenciaGoleo() + ".\n";
    }
    
}
