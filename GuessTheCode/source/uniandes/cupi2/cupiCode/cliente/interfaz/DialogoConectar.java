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

import javax.swing.JDialog;

/**
 * Diálogo usado para pedir los datos necesarios para iniciar una conexión.
 */
public class DialogoConectar extends JDialog
{
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
     * Panel donde se ingresan los datos.
     */
    private PanelDatosJuego panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo.
     * @param pPrincipal Ventana principal de la aplicación del cliente. pPrincipal != null. 
     * @param pDireccion Dirección del servidor para inicializar el panel. pDireccion!=null && pDireccion!="".
     * @param pPuerto Puerto para inicializar el panel. pPuerto!=null && pPuerto!="".
     */
    public DialogoConectar( InterfazJugador pPrincipal, String pDireccion, int pPuerto )
    {
        super( pPrincipal, true );

        setTitle( "Conectarse a servidor" );

        principal = pPrincipal;
        panelDatos = new PanelDatosJuego( this, pDireccion, pPuerto );
        add( panelDatos );

        setTitle( "Datos de la conexión" );
        setSize( 450, 350 );
        setResizable( false );

        setLocationRelativeTo( principal );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Intenta realizar una conexión.
     * @param pAlias Alias del jugador. pAlias!=null && pAlias!="". 
     * @param pNombre Nombre del jugador. pNombre!=null && pNombre!="". 
     * @param pDireccion Dirección donde se encuentra el servidor. pDireccion!=null && pDireccion!="".
     * @param pPuerto Puerto usado para la conexión. pPuerto!=null && pPuerto!="".
     * @param pColores Colores escogidos por el usuario para su código. pColores!=null.
     */
    public void conectar( String pAlias, String pNombre, String pDireccion, int pPuerto, String[] pColores )
    {
        principal.conectar( this, pAlias, pNombre, pDireccion, pPuerto, pColores );
    }

}
