/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyperbolicbaby.uady;

import static com.hyperbolicbaby.uady.TableroCalculador.porDiferenciaDeGoleo;
import static com.hyperbolicbaby.uady.TableroCalculador.porPuntajeEquipo;
import static com.hyperbolicbaby.uady.TableroCalculador.porNombreDeEquipo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KristianVikernes
 *
 */
public class TableroCalculadorTest {

    public TableroCalculadorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cargarExcelDeEquiposFutbol method, of class TableroCalculador.
     */
    @Test
    public void testCargarExcelDeEquiposFutbol() {
        System.out.println("cargarExcelDeEquiposFutbol");
        TableroCalculador instance = new TableroCalculador();
        instance.cargarExcelDeEquiposFutbol();

        //Se espera que se carguen los datos del framework ApachePOI y se guarden en el mismo orden en el cual se escribió el archivo .xlsx
        //El primero en la lista es el Equipo America. (El excel está ordenado de la a-z por nombre de los equipos) 
        //Ahora procederemos a revisar si en realidad, los datos corresponden este equipo de futbol.
        /*Datos del equipo:
         *nombreEquipo: America
         *partidosGanados = 6
         *partidosEmpatados = 7
         *partidosPerdidos = 1
         *golesAFavor = 22
         *golesEnContra = 11        
         */
        //Sí se cargó los datos del equipo de futbol América, pero se me olvidó la tilde a America XD.
        EquipoFutbol equipoFutbol = instance.getListaEquiposFutbol().get(0);
        assertEquals(equipoFutbol.getNombreEquipo(), "América");
        assertEquals(equipoFutbol.getPartidosGanados(), 6);
        assertEquals(equipoFutbol.getPartidosEmpatados(), 7);
        assertEquals(equipoFutbol.getPartidosPerdidos(), 1);
        assertEquals(equipoFutbol.getGolesAFavor(), 22);
        assertEquals(equipoFutbol.getGolesEnContra(), 11);

        /*Se hará una última prueba en éste método para revisar que el índice 1 (2da fila) en el excel se ha cargado correctamente
         *Datos del equipo:
         *nombreEquipo: Atlas
         *partidosGanados = 3
         *partidosEmpatados = 2
         *partidosPerdidos = 9
         *golesAFavor = 15
         *golesEnContra = 26        
         */
        // Ahora, hacemos asserEquals() con los datos esperados.
        EquipoFutbol equipoFutbol2 = instance.getListaEquiposFutbol().get(1);
        assertEquals(equipoFutbol2.getNombreEquipo(), "Atlas");
        assertEquals(equipoFutbol2.getPartidosGanados(), 3);
        assertEquals(equipoFutbol2.getPartidosEmpatados(), 2);
        assertEquals(equipoFutbol2.getPartidosPerdidos(), 9);
        assertEquals(equipoFutbol2.getGolesAFavor(), 15);
        assertEquals(equipoFutbol2.getGolesEnContra(), 26);
    }

    /**
     * Ahora se ha establecido 5 nuevos métodos, como se puede ver a
     * continuación. Procederemos con las pruebas unitarias.
     */
    @Test
    public void testCalcularPartidosJugados() {
        System.out.println("calcularPartidosJugados");
        int partidosGanados = 6, partidosEmpatados = 7, partidosPerdidos = 1;
        int partidosJugadosCalculo = partidosGanados + partidosEmpatados + partidosPerdidos;
        TableroCalculador instance = new TableroCalculador();
        int result = instance.calcularPartidosJugados(partidosGanados, partidosEmpatados, partidosPerdidos);

        assertEquals(partidosJugadosCalculo, result);
    }

    /**
     * Seguidamente, elaboraremos un caso de prueba para el método
     * calcularPuntajeEquipo. Procedemos...
     */
    @Test
    public void testCalcularPuntajeEquipo() {
        System.out.println("calcularPuntajeEquipo");
        int partidosGanados = 6;
        int partidosEmpatados = 7;

        int calculoPuntajeEquipo = ((partidosGanados * 3) + (partidosEmpatados * 1));
        TableroCalculador instance = new TableroCalculador();

        int result = instance.calcularPuntajeEquipo(partidosGanados, partidosEmpatados);
        assertEquals(calculoPuntajeEquipo, result);
    }

    /**
     * Este caso de prueba se hace para el método calcularDiferenciaDeGoleo. Ya
     * mero terminamos...
     */
    @Test
    public void testCalcularDiferenciaDeGoleo() {
        System.out.println("calcularDiferenciaDeGoleo");
        int golesAFavor = 22;
        int golesEnContra = 11;
        TableroCalculador instance = new TableroCalculador();
        int calculoDiferenciaDeGoleo = golesAFavor - golesEnContra;
        int result = instance.calcularDiferenciaDeGoleo(golesAFavor, golesEnContra);
        assertEquals(calculoDiferenciaDeGoleo, result);
    }

    /**
     * Último caso de prueba para el método obtenerEquipoEnPrimeraPosicion. Esto
     * se pone feo... En nuestro caso, el equipo Toluca debe ser devuelto.
     * Procederemos con la prueba...
     */
    @Test
    public void testObtenerEquipoEnPrimeraPosicion() {
        System.out.println("obtenerEquipoEnPrimeraPosicion");
        List<EquipoFutbol> listaEquiposFutbolDesdeExcel;
        TableroCalculador tableroCalculador = new TableroCalculador();
        tableroCalculador.cargarExcelDeEquiposFutbol();

        listaEquiposFutbolDesdeExcel = tableroCalculador.getListaEquiposFutbol();
        EquipoFutbol equipoFutbol = new EquipoFutbol();

        int calculoPartidosJugados;
        int calculoPuntajeEquipo;
        int calculoDiferenciaDeGoleo;

        List<EquipoFutbol> listaEquiposFutbolConDatosExtraCalculados = new ArrayList<>();

        for (int index = 0; index < listaEquiposFutbolDesdeExcel.size(); index++) {

            equipoFutbol = listaEquiposFutbolDesdeExcel.get(index);

            calculoPartidosJugados = tableroCalculador.calcularPartidosJugados(equipoFutbol.getPartidosGanados(), equipoFutbol.getPartidosEmpatados(), equipoFutbol.getPartidosPerdidos());
            equipoFutbol.setPartidosJugados(calculoPartidosJugados);

            calculoPuntajeEquipo = tableroCalculador.calcularPuntajeEquipo(equipoFutbol.getPartidosGanados(), equipoFutbol.getPartidosEmpatados());
            equipoFutbol.setPuntajeEquipo(calculoPuntajeEquipo);

            calculoDiferenciaDeGoleo = tableroCalculador.calcularDiferenciaDeGoleo(equipoFutbol.getGolesAFavor(), equipoFutbol.getGolesEnContra());
            equipoFutbol.setDiferenciaGoleo(calculoDiferenciaDeGoleo);

            listaEquiposFutbolConDatosExtraCalculados.add(index, equipoFutbol);
        }

        Collections.sort(listaEquiposFutbolConDatosExtraCalculados, Collections.reverseOrder(porPuntajeEquipo()));

        //Si existe el caso particular de empate por puntaje y diferencia de goleo, los siguientes métodos se usarán.
        List<EquipoFutbol> listaEquiposFutbolConMismoPuntaje = new ArrayList<>();

        if (listaEquiposFutbolConDatosExtraCalculados.get(0).getPuntajeEquipo() == listaEquiposFutbolConDatosExtraCalculados.get(1).getPuntajeEquipo()) {

            for (int newIndex = 0; newIndex < 2; newIndex++) {

                listaEquiposFutbolConMismoPuntaje.add(listaEquiposFutbolConDatosExtraCalculados.get(newIndex));

            }

            Collections.sort(listaEquiposFutbolConMismoPuntaje, Collections.reverseOrder(porDiferenciaDeGoleo()));

            if (listaEquiposFutbolConMismoPuntaje.get(0).getDiferenciaGoleo() == listaEquiposFutbolConMismoPuntaje.get(1).getDiferenciaGoleo()) {
                List<EquipoFutbol> listaEquiposFutbolConMismaDiferenciaDeGoleo = new ArrayList<>();

                for (int anotherIndex = 0; anotherIndex < 2; anotherIndex++) {

                    listaEquiposFutbolConMismaDiferenciaDeGoleo.add(listaEquiposFutbolConMismoPuntaje.get(anotherIndex));
                    Collections.sort(listaEquiposFutbolConMismaDiferenciaDeGoleo, porNombreDeEquipo());

                }
                
                tableroCalculador.imprimirDatosEquipoEnPrimeraPosicion(listaEquiposFutbolConMismaDiferenciaDeGoleo.get(0), 0);
            }

            tableroCalculador.imprimirDatosEquipoEnPrimeraPosicion(listaEquiposFutbolConMismoPuntaje.get(0), 0);

        } else {

            tableroCalculador.imprimirDatosEquipoEnPrimeraPosicion(listaEquiposFutbolConDatosExtraCalculados.get(0), 0);

        }

        //Última prueba
        EquipoFutbol resultadoEsperado = new EquipoFutbol("Toluca", 9, 3, 2, 20, 12);
        resultadoEsperado.setPartidosJugados(14);
        resultadoEsperado.setPuntajeEquipo(30);
        resultadoEsperado.setDiferenciaGoleo(8);

        EquipoFutbol resultado = listaEquiposFutbolConDatosExtraCalculados.get(0);

        System.out.println(resultadoEsperado.toString());
        System.out.println(resultado.toString());
        
        //asserEquals() para comprobar que en efecto, el equipo resultante es el de nombre "Toluca"
        assertEquals(resultadoEsperado.getNombreEquipo(), resultado.getNombreEquipo());

    }

}
