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
package uniandes.cupi2.cupiCode.cliente.mundo;

import uniandes.cupi2.cupiCode.cliente.interfaz.InterfazJugador;

/**
 * Clase que representa un hilo de ejecución cuando se quiere conectar al cliente con el servidor.
 */
public class ThreadConectar extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Información del jugador.
     */
    private Jugador jugador;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazJugador principal;
    
    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Nombre de jugador.
     */
    private String nombre;

    /**
     * Dirección para localizar al servidor.
     */
    private String servidor;

    /**
     * Puerto a través del cual se realizará la conexión con el servidor.
     */
    private int puerto;

    /**
     * Arreglo de colores del jugador.
     */
    private String[] colores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * <b>pre: </b> El jugador y la interfaz principal ya fueron inicializados.<br>
     * <b>post: </b> El hilo quedó inicializado.<br>
     * @param pJuego Juego de CupiCode. pJuego != null.
     * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
     * @param pAlias Alias del jugador. pAliasJugador!=null && pAliasJugador!="".
     * @param pNombre Nombre del jugador. pNombreJugador != null && pNombreJugador!="".
     * @param pDireccionServidor Dirección para localizar al servidor. pDireccionServidor != null && pDireccionServidor!="".
     * @param pPuertoServidor Puerto a través del cual se realizará la conexión con el servidor. pPuertoServidor>0.
     * @param pColores Arreglo de colores del jugador. pColores != null.
     */
    public ThreadConectar( Jugador pJuego, InterfazJugador pInterfaz, String pAlias, String pNombre, String pDireccionServidor, int pPuertoServidor, String[] pColores )
    {
        jugador = pJuego;
        principal = pInterfaz;
        alias = pAlias;
        nombre = pNombre;
        servidor = pDireccionServidor;
        puerto = pPuertoServidor;
        colores = pColores;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza la conexión con el servidor e inicializa el tablero.<br>
     * Cuando se tiene la conexión y la información del tablero se actualiza la interfaz.
     * <b>post: </b> Se conectó al servidor y se empezó la partida.<br>
     */
    public void run( )
    {
        try
        { 
            jugador.conectar( alias, nombre, servidor, puerto, colores );

            principal.actualizarInterfaz( );
            
            String info = jugador.obtenerInfoOponente();
            principal.actualizarInfoOponente(info);
            
            boolean a = jugador.obtenerTurno(null);
            while(!a)
            {
                String color = principal.escogerColor( );
                a = jugador.obtenerTurno(color );
                
            }
            if(jugador.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE)
            {
                principal.mostrarInformacion( "Su oponente adivinó el color. Usted tiene el segundo turno." );
            }
            else if(jugador.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL)
            {
                principal.mostrarInformacion( "Adivinó el color. Usted tiene el primer turno." );
            }
            
            principal.actualizarInterfaz( );

            if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
            {
                principal.recibirJugada( );
            }
        }
        catch( CupiCodeException e )
        {
            principal.mostrarError( e );
        }
    }
}
