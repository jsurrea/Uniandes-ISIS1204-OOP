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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Clase donde está definido el menú de la aplicación.
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opción iniciar juego.
     */
    private static final String INICIAR_JUEGO = "IniciarJuego";

    /**
     * Comando de la opción salir.
     */
    private static final String SALIR = "salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación del cliente.
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Menú inicio del juego.
     */
    private JMenu menuInicio;

    /**
     * Opción iniciar juego del menú inicio.
     */
    private JMenuItem itemIniciarJuego;

    /**
     * Opción salir del menú inicio.
     */
    private JMenuItem itemSalir;

    /**
     * Menú extensiones del juego.
     */
    private JMenu menuExtension;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el menú para la aplicación de CupiCode.
     * @param pPrincipal Ventana principal de la aplicación del cliente. pPrincipal != null.
     */
    public BarraMenu( InterfazJugador pPrincipal )
    {
        principal = pPrincipal;

        menuInicio = new JMenu( "Inicio" );
        add( menuInicio );

        itemIniciarJuego = new JMenuItem( "Iniciar juego" );
        itemIniciarJuego.setActionCommand( INICIAR_JUEGO );
        itemIniciarJuego.addActionListener( this );
        menuInicio.add( itemIniciarJuego );

        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuInicio.add( itemSalir );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inhabilita la opción de iniciar juego.
     */
    public void inhabilitaIniciarJuego( )
    {
        itemIniciarJuego.setEnabled( false );
    }

    /**
     * Habilita la opción de iniciar juego.
     */
    public void habilitarIniciarJuego( )
    {
        itemIniciarJuego.setEnabled( true );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
    
        if( SALIR.equals( comando ) )
        {
            principal.dispose( );
        }
        else if( INICIAR_JUEGO.equals( comando ) )
        {
            principal.iniciarConexion( );
        }
    }
}
