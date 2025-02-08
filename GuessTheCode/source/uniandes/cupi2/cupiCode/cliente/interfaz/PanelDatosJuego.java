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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupiCode.cliente.mundo.Codigo;

/**
 * Panel donde se ingresan los datos necesarios para iniciar una conexión.
 */
public class PanelDatosJuego extends JPanel implements ActionListener, MouseListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Nombre para la imagen 1.
     */
    private static final String COLOR_1 = "COLOR_1";

    /**
     * Nombre para la imagen 2.
     */
    private static final String COLOR_2 = "COLOR_2";

    /**
     * Nombre para la imagen 3.
     */
    private static final String COLOR_3 = "COLOR_3";

    /**
     * Nombre para la imagen 4.
     */
    private static final String COLOR_4 = "COLOR_4";
    
    /**
     * Nombre para la imagen 5.
     */
    private static final String COLOR_5 = "COLOR_5";

    /**
     * Nombre para la imagen 6
     */
    private static final String COLOR_6 = "COLOR_6";

    /**
     * Comando para el botón conectar.
     */
    private static final String CONECTAR = "Conectar";

    /**
     * Comando para el botón cancelar.
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al diálogo que contiene al panel.
     */
    private DialogoConectar dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta alias.
     */
    private JLabel etiquetaAlias;
    
    /**
     * Etiqueta nombre.
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta dirección servidor.
     */
    private JLabel etiquetaServidor;

    /**
     * Etiqueta puerto.
     */
    private JLabel etiquetaPuerto;

    /**
     * Etiqueta colores.
     */
    private JLabel etiquetaColores;
    
    /**
     * Campo de texto para el alias del jugador.
     */
    private JTextField txtAlias;

    /**
     * Campo de texto para el nombre del jugador.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para la dirección servidor.
     */
    private JTextField txtServidor;

    /**
     * Campo de texto para el puerto.
     */
    private JTextField txtPuerto;

    /**
     * Etiqueta color 1.
     */
    private JLabel lbColor1;

    /**
     * Nombre del color 1.
     */
    private String color1;

    /**
     * Etiqueta color 2.
     */
    private JLabel lbColor2;

    /**
     * Nombre del color 2.
     */
    private String color2;

    /**
     * Etiqueta color 3.
     */
    private JLabel lbColor3;

    /**
     * Nombre del color3.
     */
    private String color3;

    /**
     * Etiqueta color 4.
     */
    private JLabel lbColor4;

    /**
     * Nombre del color4.
     */
    private String color4;
    
    /**
     * Etiqueta color 5.
     */
    private JLabel lbColor5;

    /**
     * Nombre del color5.
     */
    private String color5;
    
    /**
     * Etiqueta color 5.
     */
    private JLabel lbColor6;

    /**
     * Nombre del color5.
     */
    private String color6;

    /**
     * Botón conectar.
     */
    private JButton botonConectar;

    /**
     * Botón cancelar.
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel que hace parte del diálogo para conectarse al servidor.
     * @param pDialogoConectar Diálogo dentro del cual está este panel. pDialogoConectar!=null.
     * @param pDireccion Dirección del servidor para inicializar el panel. pDireccion!=null && pDireccion!="".
     * @param pPuerto Puerto para inicializar el panel. pPuerto!=null && pDireccion!="".
     */
    public PanelDatosJuego( DialogoConectar pDialogoConectar, String pDireccion, int pPuerto )
    {
        dialogo = pDialogoConectar;

        setLayout( new BorderLayout( ) );

        etiquetaAlias = new JLabel( "Alias del jugador:" );
        etiquetaNombre = new JLabel( "Nombre del jugador:" );
        etiquetaServidor = new JLabel( "Dirección servidor:" );
        etiquetaPuerto = new JLabel( "Puerto:" );
        etiquetaColores = new JLabel( "Código secreto:" );

        
        txtAlias = new JTextField();
        txtNombre = new JTextField( );
        txtServidor = new JTextField( pDireccion );
        txtPuerto = new JTextField( "" + pPuerto );

        lbColor1 = new JLabel( );
        lbColor1.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor1.addMouseListener( this );
        lbColor1.setName( COLOR_1 );
        lbColor1.setToolTipText( "Haga click para cambiar el color" );

        color1 = Codigo.SIN_COLOR;

        lbColor2 = new JLabel( );
        lbColor2.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor2.addMouseListener( this );
        lbColor2.setName( COLOR_2 );
        lbColor2.setToolTipText( "Haga click para cambiar el color" );

        color2 = Codigo.SIN_COLOR;

        lbColor3 = new JLabel( );
        lbColor3.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor3.addMouseListener( this );
        lbColor3.setName( COLOR_3 );
        lbColor3.setToolTipText( "Haga click para cambiar el color" );

        color3 = Codigo.SIN_COLOR;

        lbColor4 = new JLabel( );
        lbColor4.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor4.addMouseListener( this );
        lbColor4.setName( COLOR_4 );
        lbColor4.setToolTipText( "Haga click para cambiar el color" );

        color4 = Codigo.SIN_COLOR;
        
        lbColor5 = new JLabel( );
        lbColor5.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor5.addMouseListener( this );
        lbColor5.setName( COLOR_5 );
        lbColor5.setToolTipText( "Haga click para cambiar el color" );

        color5 = Codigo.SIN_COLOR;
        
        lbColor6 = new JLabel( );
        lbColor6.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        lbColor6.addMouseListener( this );
        lbColor6.setName( COLOR_6 );
        lbColor6.setToolTipText( "Haga click para cambiar el color" );

        color6 = Codigo.SIN_COLOR;

        botonConectar = new JButton( "Conectar" );
        botonConectar.setActionCommand( CONECTAR );
        botonConectar.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        JPanel panelCentral = new JPanel( new GridLayout( 5, 1 ,20,20) );
        panelCentral.add( txtAlias );
        panelCentral.add( txtNombre );
        panelCentral.add( txtServidor );
        panelCentral.add( txtPuerto );

        JPanel panelColores = new JPanel( new GridLayout( 1, 6 ) );
        panelColores.add( lbColor1 );
        panelColores.add( lbColor2 );
        panelColores.add( lbColor3 );
        panelColores.add( lbColor4 );
        panelColores.add( lbColor5 );
        panelColores.add( lbColor6 );

        panelCentral.add( panelColores );
        add( panelCentral, BorderLayout.CENTER );

        JPanel panelOeste = new JPanel( new GridLayout( 5, 1 ) );
        panelOeste.add( etiquetaAlias );
        panelOeste.add( etiquetaNombre );
        panelOeste.add( etiquetaServidor );
        panelOeste.add( etiquetaPuerto );
        panelOeste.add( etiquetaColores );
        add( panelOeste, BorderLayout.WEST );

        JPanel panelSur = new JPanel( new GridLayout( 1, 2 ) );
        panelSur.add( botonConectar );
        panelSur.add( botonCancelar );
        add( panelSur, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Rota la imagen del color.
     * @param pImagenColor Etiqueta con la imagen de color. pImagenColor!=null.
     * @param pColor Referencia al color. pColor!=null && pColor!="".
     * @return Nuevo color.
     */
    private String rotarImagen( JLabel pImagenColor, String pColor )
    {
        if( pColor.equals( Codigo.SIN_COLOR ) )
        {
            pColor = Codigo.AMARILLO;
            pImagenColor.setIcon( new ImageIcon( "./data/imagenes/amarillo.png" ) );
        }
        else if( pColor.equals( Codigo.AMARILLO ) )
        {
            pColor = Codigo.AZUL;
            pImagenColor.setIcon( new ImageIcon( "./data/imagenes/azul.png" ) );
        }
        else if( pColor.equals( Codigo.AZUL ) )
        {
            pColor = Codigo.ROJO;
            pImagenColor.setIcon( new ImageIcon( "./data/imagenes/rojo.png" ) );
        }
        else if( pColor.equals( Codigo.ROJO ) )
        {
            pColor = Codigo.VERDE;
            pImagenColor.setIcon( new ImageIcon( "./data/imagenes/verde.png" ) );
        }
        else if( pColor.equals( Codigo.VERDE ) )
        {
            pColor = Codigo.AMARILLO;
            pImagenColor.setIcon( new ImageIcon( "./data/imagenes/amarillo.png" ) );
        }
        return pColor;
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( CONECTAR.equals( comando ) )
        {
            try
            {
                String alias = txtAlias.getText( );
                String nombre = txtNombre.getText( );
                String direccion = txtServidor.getText( );

                String strPuerto = txtPuerto.getText( );

                if( nombre != null && !nombre.equals( "" ) && alias != null && !alias.equals( "" ) && direccion != null && !direccion.equals( "" ) && strPuerto != null && !strPuerto.equals( "" ) )
                {
                    int puerto = Integer.parseInt( strPuerto );

                    if( !color1.equals( Codigo.SIN_COLOR ) && !color2.equals( Codigo.SIN_COLOR ) && !color3.equals( Codigo.SIN_COLOR ) && !color4.equals( Codigo.SIN_COLOR ) && !color5.equals( Codigo.SIN_COLOR )&& !color6.equals( Codigo.SIN_COLOR ) )
                    {
                        String[] colores = { color1, color2, color3, color4, color5, color6 };
                        dialogo.conectar( alias, nombre, direccion, puerto, colores );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( null, "Debe escoger los colores de su código", "Conectarse a servidor", JOptionPane.ERROR_MESSAGE );
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "Los campos del nombre de jugador, de la dirección del servidor y del puerto no pueden estar vacíos", "Conectarse a servidor", JOptionPane.ERROR_MESSAGE );
                }
            }
            catch( NumberFormatException nfe )
            {
                JOptionPane.showMessageDialog( dialogo, "El valor numérico del puerto debe ser un número" );
            }
        }
    }

    /**
     * Método usado cuando se hace click sobre la superficie de una imagen.
     * @param pEvento Evento del click sobre una imagen. pEvento!=null.
     */
    public void mousePressed( MouseEvent pEvento )
    {
        String nombreComponente = pEvento.getComponent( ).getName( );
        if( nombreComponente.equals( COLOR_1 ) )
        {
            color1 = rotarImagen( lbColor1, color1 );
        }
        else if( nombreComponente.equals( COLOR_2 ) )
        {
            color2 = rotarImagen( lbColor2, color2 );
        }
        else if( nombreComponente.equals( COLOR_3 ) )
        {
            color3 = rotarImagen( lbColor3, color3 );
        }
        else if( nombreComponente.equals( COLOR_4 ) )
        {
            color4 = rotarImagen( lbColor4, color4 );
        }
        else if( nombreComponente.equals( COLOR_5 ) )
        {
            color5 = rotarImagen( lbColor5, color5 );
        }
        else if( nombreComponente.equals( COLOR_6 ) )
        {
            color6 = rotarImagen( lbColor6, color6 );
        }
    }

    /**
     * Este método no se implementa
     * @param e El evento
     */
    public void mouseClicked( MouseEvent e )
    {
        // No se requiere
    }

    /**
     * Este método no se implementa
     * @param e El evento
     */
    public void mouseReleased( MouseEvent e )
    {
        // No se requiere
    }

    /**
     * Este método no se implementa
     * @param e El evento
     */
    public void mouseEntered( MouseEvent e )
    {
        // No se requiere
    }

    /**
     * Este método no se implementa
     * @param e El evento
     */
    public void mouseExited( MouseEvent e )
    {
        // No se requiere
    }

}
