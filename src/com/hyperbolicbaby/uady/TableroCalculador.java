/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyperbolicbaby.uady;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Christian Tuyub
 *
 */
public class TableroCalculador {

    private static final String TABLERO_EXCEL = "excelDependency/tablero.xlsx";
    private List<EquipoFutbol> listaEquiposFutbol;
    private EquipoFutbol equipoEnPrimeraPosicion;

    public TableroCalculador() {
        this.listaEquiposFutbol = new ArrayList<>();
        this.equipoEnPrimeraPosicion = new EquipoFutbol();
    }

    public List<EquipoFutbol> getListaEquiposFutbol() {
        //return Collections.unmodifiableList(listaEquiposFutbol);
        return listaEquiposFutbol;
    }

    public void cargarExcelDeEquiposFutbol() {

        String nombreEquipo = null;
        int partidosGanados = 0;
        int partidosEmpatados = 0;
        int partidosPerdidos = 0;
        int golesAFavor = 0;
        int golesEnContra = 0;
        EquipoFutbol equipoFutbol;

        try {

            FileInputStream excelFile = new FileInputStream(new File(TABLERO_EXCEL));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            iterator.next();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    int indexOfCurrentCell;
                    Cell currentCell = cellIterator.next();
                    indexOfCurrentCell = currentCell.getColumnIndex();

                    switch (indexOfCurrentCell) {
                        case 0:
                            nombreEquipo = currentCell.getStringCellValue();
                            break;
                        case 1:
                            partidosGanados = (int) currentCell.getNumericCellValue();
                            break;
                        case 2:
                            partidosEmpatados = (int) currentCell.getNumericCellValue();
                            break;
                        case 3:
                            partidosPerdidos = (int) currentCell.getNumericCellValue();
                            break;
                        case 4:
                            golesAFavor = (int) currentCell.getNumericCellValue();
                            break;
                        case 5:
                            golesEnContra = (int) currentCell.getNumericCellValue();
                            break;
                        default:
                            break;
                    }
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    /*if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }*/

                }

                equipoFutbol = new EquipoFutbol(nombreEquipo, partidosGanados, partidosEmpatados, partidosPerdidos, golesAFavor, golesEnContra);

                listaEquiposFutbol.add(equipoFutbol);

            }

        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int calcularPartidosJugados(int partidosGanados, int partidosEmpatados, int partidosPerdidos) {
        int calculoPartidosJugados = partidosGanados + partidosEmpatados + partidosPerdidos;

        return calculoPartidosJugados;
    }

    public int calcularPuntajeEquipo(int partidosGanados, int partidosEmpatados) {
        int calculoPuntajeEquipo = ((partidosGanados * 3) + (partidosEmpatados * 1));

        return calculoPuntajeEquipo;
    }

    public int calcularDiferenciaDeGoleo(int golesAFavor, int golesEnContra) {
        int calculoDiferenciaDeGoleo = golesAFavor - golesEnContra;

        return calculoDiferenciaDeGoleo;
    }

    public EquipoFutbol obtenerEquipoEnPrimeraPosicion(List<EquipoFutbol> listaEquiposFutbol) {
        
        EquipoFutbol equipoFutbol = new EquipoFutbol();
        
        int calculoPartidosJugados;
        int calculoPuntajeEquipo;
        int calculoDiferenciaDeGoleo;

        List<EquipoFutbol> listaEquiposFutbolConDatosExtraCalculados = new ArrayList<>();

        for (int index = 0; index < listaEquiposFutbol.size(); index++) {

            equipoFutbol = listaEquiposFutbol.get(index);

            calculoPartidosJugados = this.calcularPartidosJugados(equipoFutbol.getPartidosGanados(), equipoFutbol.getPartidosEmpatados(), equipoFutbol.getPartidosPerdidos());
            equipoFutbol.setPartidosJugados(calculoPartidosJugados);

            calculoPuntajeEquipo = this.calcularPuntajeEquipo(equipoFutbol.getPartidosGanados(), equipoFutbol.getPartidosEmpatados());
            equipoFutbol.setPuntajeEquipo(calculoPuntajeEquipo);

            calculoDiferenciaDeGoleo = this.calcularDiferenciaDeGoleo(equipoFutbol.getGolesAFavor(), equipoFutbol.getGolesEnContra());
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

                    equipoEnPrimeraPosicion = (listaEquiposFutbolConMismaDiferenciaDeGoleo.get(0));
                }
            }

            equipoEnPrimeraPosicion = (listaEquiposFutbolConMismoPuntaje.get(0));

        } else {

            equipoEnPrimeraPosicion = (listaEquiposFutbolConDatosExtraCalculados.get(0));

        }
        
        return equipoEnPrimeraPosicion;
    }

    public static Comparator<EquipoFutbol> porPuntajeEquipo() {
        return new Comparator<EquipoFutbol>() {
            @Override
            public int compare(EquipoFutbol equipo1, EquipoFutbol equipo2) {
                return equipo1.getPuntajeEquipo() - equipo2.getPuntajeEquipo();
            }
        };
    }

    public static Comparator<EquipoFutbol> porDiferenciaDeGoleo() {
        return new Comparator<EquipoFutbol>() {
            @Override
            public int compare(EquipoFutbol equipo1, EquipoFutbol equipo2) {
                return equipo1.getDiferenciaGoleo() - equipo2.getDiferenciaGoleo();
            }
        };
    }

    public static Comparator<EquipoFutbol> porNombreDeEquipo() {
        return new Comparator<EquipoFutbol>() {
            @Override
            public int compare(EquipoFutbol equipo1, EquipoFutbol equipo2) {
                return equipo1.getNombreEquipo().compareTo(equipo2.getNombreEquipo());
            }
        };
    }

    public void imprimirDatosEquipoEnPrimeraPosicion(EquipoFutbol equipoFutbolPrimeraPosicion, int indexEquipo) {
        System.out.println(equipoFutbolPrimeraPosicion.toString() + "Posición: " + (indexEquipo + 1) + "er lugar");
    }

}
