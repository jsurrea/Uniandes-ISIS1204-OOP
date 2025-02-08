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
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiCode.cliente.mundo.Codigo;

/**
 * Panel donde se envía una jugada al contrincante.
 */
public class PanelEnviarJugada extends JPanel implements ActionListener, MouseListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando enviar jugada.
     */
    private static final String ENVIAR = "ENVIAR";

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
     * Nombre para la imagen 6.
     */
    private static final String COLOR_6 = "COLOR_6";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

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
     * Etiqueta color 6.
     */
    private JLabel lbColor6;

    /**
     * Nombre del color6.
     */
    private String color6;

    /**
     * Etiqueta con estado.
     */
    private JLabel lbEstado;

    /**
     * Botón enviar jugada.
     */
    private JButton btnEnviar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Determina si los controles están habilitados o no.
     */
    private boolean controlesHabilitados;

    /**
     * Ventana principal de la aplicación del cliente.
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel donde se envía la jugada.
     * @param pPrincipal Ventana principal de la aplicación del cliente. pPrincipal != null.
     * @param pEstado Estado de los botones del panel (Habilitados o deshabilitados). pEstado!=null && pEstado!="".
     */
    public PanelEnviarJugada( InterfazJugador pPrincipal, String pEstado )
    {
        principal = pPrincipal;
        controlesHabilitados = false;
        setLayout(new BorderLayout( ) );

        
        JPanel panelEstado = new JPanel();
        panelEstado.setBorder( BorderFactory.createTitledBorder( "Estado" ) );
        lbEstado = new JLabel( pEstado );
        lbEstado.setFont( new Font( "Arial Black", Font.ITALIC, 20 ) );
        lbEstado.setForeground( Color.BLUE );
        panelEstado.add(lbEstado);
        add(panelEstado, BorderLayout.NORTH);
        
        JPanel panelJugada = new JPanel();
        panelJugada.setBorder( BorderFactory.createTitledBorder( "Realizar jugada" ) );
        panelJugada.setLayout( new BorderLayout( ) );

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

        btnEnviar = new JButton( "Enviar" );
        btnEnviar.setActionCommand( ENVIAR );
        btnEnviar.addActionListener( this );
        btnEnviar.setEnabled( false );

        JPanel panelColores = new JPanel( new GridLayout( 1, 6 ) );
        panelColores.add( lbColor1 );
        panelColores.add( lbColor2 );
        panelColores.add( lbColor3 );
        panelColores.add( lbColor4 );
        panelColores.add( lbColor5 );
        panelColores.add( lbColor6 );

        panelJugada.add( panelColores, BorderLayout.CENTER );
        panelJugada.add( btnEnviar, BorderLayout.SOUTH );
        
        add(panelJugada, BorderLayout.CENTER);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Modifica el texto de la etiqueta estado.
     * @param pEstado Nuevo texto de la etiqueta estado. pEstado!=null && pEstado!="".
     */
    public void modificarEstado( String pEstado )
    {
        lbEstado.setText( pEstado );
    }

    /**
     * Habilita la opción de enviar jugada.
     */
    public void habilitarControles( )
    {
        controlesHabilitados = true;
        btnEnviar.setEnabled( true );
    }

    /**
     * Deshabilita la opción de enviar jugada.
     */
    public void inhabilitarControles( )
    {
        controlesHabilitados = false;
        btnEnviar.setEnabled( false );
    }

    /**
     * Reinicia las imágenes de los colores.
     */
    private void reiniciarColores( )
    {
        lbColor1.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color1 = Codigo.SIN_COLOR;
    
        lbColor2.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color2 = Codigo.SIN_COLOR;
    
        lbColor3.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color3 = Codigo.SIN_COLOR;
    
        lbColor4.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color4 = Codigo.SIN_COLOR;
        
        lbColor5.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color5 = Codigo.SIN_COLOR;
        
        lbColor6.setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        color6 = Codigo.SIN_COLOR;
    
    }

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
        if( ENVIAR.equals( pEvento.getActionCommand( ) ) )
        {
            if( !color1.equals( Codigo.SIN_COLOR ) && !color2.equals( Codigo.SIN_COLOR ) && !color3.equals( Codigo.SIN_COLOR ) && !color4.equals( Codigo.SIN_COLOR ) && !color5.equals( Codigo.SIN_COLOR ) && !color6.equals( Codigo.SIN_COLOR ) )
            {
                String[] colores = { color1, color2, color3, color4, color5, color6 };
                reiniciarColores( );
                principal.enviarJugada( colores );
            }
            else
            {
                JOptionPane.showMessageDialog( principal, "Debe escoger los colores de su código", "Enviar Jugada", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Método que se llama cuando se hace click sobre la superficie de una imagen.
     * @param pEvento Evento del click sobre una imagen. pEvento!=null.
     */
    public void mousePressed( MouseEvent pEvento )
    {
        if( controlesHabilitados )
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
    }

    /**
     * Método no se implementa.
     * @param pEvento Evento.
     */
    public void mouseReleased( MouseEvent pEvento )
    {
        // No se requiere
    }

    /**
     * Método no se implementa
     * @param pEvento Evento
     */
    public void mouseClicked( MouseEvent pEvento )
    {
        // No se requiere
    }

    /**
     * Método no se implementa
     * @param pEvento Evento
     */
    public void mouseEntered( MouseEvent pEvento )
    {
        // No se requiere
    }

    /**
     * Método no se implementa
     * @param pEvento Evento
     */
    public void mouseExited( MouseEvent pEvento )
    {
        // No se requiere
    }
}
