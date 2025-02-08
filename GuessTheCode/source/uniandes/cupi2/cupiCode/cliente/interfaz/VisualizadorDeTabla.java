/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiCode
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiCode.cliente.interfaz;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase para darle formato a las celda de la tabla.
 */
public class VisualizadorDeTabla extends DefaultTableCellRenderer  
{ 
    /**
     * Pinta el componente de la celda.
     * @param pTabla Tabla del historial. pTabla !=null.
     * @param pValor Valor de la celda. pValor !=null.
     * @param pEstaSeleccionada  Celda seleccionada.
     * @param pEstaEnfocada Celda enfocada. 
     * @param pFila Fila de la celda. pFila>0.
     * @param pColumna Columna de la celda. pColumna>0.
     * @return Componente que pinta la celda.
     */
    public Component getTableCellRendererComponent(JTable pTabla, Object pValor, boolean pEstaSeleccionada, boolean pEstaEnfocada, int pFila, int pColumna) 
    { 
        Component c = super.getTableCellRendererComponent(pTabla, pValor, pEstaSeleccionada, pEstaEnfocada, pFila, pColumna); 
        
        if(pValor.toString( ).equals( PanelHistorial.AMARRILLO ))
            c.setBackground(Color.yellow); 
        else if(pValor.toString( ).equals( PanelHistorial.ROJO))
            c.setBackground(Color.red); 
        else if(pValor.toString( ).equals( PanelHistorial.VERDE))
            c.setBackground(Color.green); 
        else if(pValor.toString( ).equals( PanelHistorial.AZUL))
            c.setBackground(new Color(84,142,252)); 
        else
            c.setBackground(Color.white); 
        return c; 
    } 

} 