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
 * Clase que representa el hilo de ejecución cuando se quiere esperar la jugada del oponente.
 */
public class ThreadRecibirJugada extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Información del juego.
     */
    private Jugador jugador;

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para esperar la jugada.
     * <b>pre: </b> El jugador y la interfaz principal ya fueron inicializados.<br>
     * <b>post: </b> El hilo quedó inicializado.<br>
     * @param pJuego Información del juego. pJuego != null.
     * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
     */
    public ThreadRecibirJugada( Jugador pJuego, InterfazJugador pInterfaz )
    {
        super( );
        jugador = pJuego;
        principal = pInterfaz;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que espera la jugada del oponente. <br>
     * Cuando se tiene información sobre la jugada del oponente se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador, termina el juego y la conexión al servidor.
     */
    public void run( )
    {
        try
        {
            String mensaje = jugador.recibirJugada( );
            if(!mensaje.equals( "" ))
            {
                principal.mostrarInformacion( mensaje );
            }
            principal.habilitarEnviar( );
            principal.actualizarInterfaz( );

            if( jugador.juegoTerminado( ) )
            {
                jugador.terminarJuego( );
                principal.actualizarInterfaz( );
                principal.mostrarGanador( );
            }
        }
        catch( CupiCodeException e )
        {
            principal.mostrarError( e);
        }
    }
}
