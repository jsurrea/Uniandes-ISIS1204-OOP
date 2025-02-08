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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import uniandes.cupi2.cupiCode.cliente.mundo.Codigo;

/**
 * Panel con el historial de jugadas del usuario.
 */
public class PanelHistorial extends JPanel
{
    // --------------------------------------------------------
    // Constantes
    // --------------------------------------------------------

    /**
     * Constante color amarillo.
     */
    public final static String AMARRILLO = "Y";

    /**
     * Constante color azul.
     */
    public final static String AZUL = "B";

    /**
     * Constante color rojo.
     */
    public final static String ROJO = "R";

    /**
     * Constante color verde.
     */
    public final static String VERDE = "G";

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Modelo de la tabla.
     */
    private DefaultTableModel modeloTabla;

    /**
     * Tabla con los datos de las jugadas.
     */
    private JTable tabla;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Constructor del panel que muestra el historial de jugadas.
     */
    public PanelHistorial( )
    {
        setBorder( BorderFactory.createTitledBorder( "Historial" ) );
        setLayout( new BorderLayout( ) );

        modeloTabla = new DefaultTableModel( );
        tabla = new JTable( modeloTabla )
        {
            public boolean isCellEditable( int col, int row )
            {
                return false;
            }
        };
        
        tabla.setPreferredSize(new Dimension( 500, 500 ));
        tabla.setColumnSelectionAllowed( false );
        tabla.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tabla.setFont( new Font( "Arial", Font.BOLD, 12 ) );
        tabla.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
        tabla.getTableHeader( ).setReorderingAllowed( false );
        
        VisualizadorDeTabla myRenderer = new VisualizadorDeTabla();
        tabla.setDefaultRenderer(Object.class, myRenderer);
        
        for(int i = 0 ; i< 6;i++)
        {
            modeloTabla.addColumn( "C" + (i+1));  
        }

        modeloTabla.addColumn( "#Colores" );
        modeloTabla.addColumn( "#Posiciones" );
        
        for(int i = 0 ; i< 6;i++)
        {
            tabla.getColumn("C" + (i+1)).setPreferredWidth( 15);
        }
        
        JScrollPane panelScroll = new JScrollPane( tabla );
        add( panelScroll, BorderLayout.CENTER );

    }

    // --------------------------------------------------------
    // Métodos
    // --------------------------------------------------------

    /**
     * Agregar un código a la tabla.
     * @param pCodigos Historial de códigos. pCodigo != null.
     */
    public void agregarCodigos( ArrayList pCodigos )
    {
        borrarHistorial( );
        for(int j = 0; j<pCodigos.size( );j++)
        {
            Codigo pCodigo = ( Codigo )pCodigos.get(j);
            String[] colores = pCodigo.darColores( );
            String [] fila = new String [colores.length+2];
            for( int i = 0; i < colores.length; i++ )
            {
                if( colores[ i ].equals( Codigo.AMARILLO ) )
                    fila[i]= AMARRILLO; 
    
                else if( colores[ i ].equals( Codigo.AZUL ) )
                    fila[i]= AZUL;
    
                else if( colores[ i ].equals( Codigo.ROJO ) )
                    fila[i]= ROJO;
    
                else if( colores[ i ].equals( Codigo.VERDE ) )
                    fila[i]= VERDE;
            }
    
            fila [colores.length] = ""+pCodigo.darCantidadColoresCorrectos( );
            fila [colores.length+1] = ""+pCodigo.darCantidadPosicionesCorrectas( );
            modeloTabla.addRow( fila );
        }
    }

    /**
     * Borra la tabla del historial de jugadas.
     */
    public void borrarHistorial( )
    {
        int cant = modeloTabla.getRowCount( );
        for( int i = 0; i < cant; i++ )
        {
            modeloTabla.removeRow( 0 );
        }
    }

}
