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

public class mainProvisional {
    public static void main(String[] args) {        
        
        EquipoFutbol equipoFutbol = new EquipoFutbol();
        TableroCalculador tableroTest = new TableroCalculador();
        tableroTest.cargarExcelDeEquiposFutbol();
        equipoFutbol = tableroTest.obtenerEquipoEnPrimeraPosicion(tableroTest.getListaEquiposFutbol());
        tableroTest.imprimirDatosEquipoEnPrimeraPosicion(equipoFutbol, 0);
        
    }
}
