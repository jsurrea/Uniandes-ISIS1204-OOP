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
 * Clase que representa un hilo de ejecución cuando se quiere pedir una pista.
 */
public class ThreadPedirPista extends Thread
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

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para enviar la jugada.
     * <b>pre: </b> El jugador y la interfaz principal ya fueron inicializados.<br>
     * <b>post: </b> El hilo quedó inicializado.<br>
     * @param pJuego Información del juego. pJuego != null.
     * @param pInterfaz Ventana principal de la aplicación. pInterfaz != null.
     */
    public ThreadPedirPista( Jugador pJuego, InterfazJugador pInterfaz )
    {
        super( );

        jugador = pJuego;
        principal = pInterfaz;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza el envío del mensaje y espera la respuesta. <br>
     * Cuando se tiene información con la pista se la muestra al jugador.<br>.
     */
    public void run( )
    {
        try
        {
            String pista = jugador.pedirPista( );
            if(pista != null)
            {
                String posicion = pista.split( "-" )[0];
                String colorCodificado = pista.split( "-" )[1];
                String color = "";
                if(colorCodificado.equals( "Y" ))
                {
                    color = "amarillo";
                }
                else if(colorCodificado.equals( "B" ))
                {
                    color = "azul";
                }
                else if(colorCodificado.equals( "R" ))
                {
                    color = "rojo";
                }
                else if(colorCodificado.equals( "G" ))
                {
                    color = "verde";
                }
                principal.mostrarInformacion( "Pista :"+" La posición " +posicion+" es de color "+color);
                principal.actualizarInterfaz( );
                principal.recibirJugada( );
            }
            else
            {
                principal.mostrarError(new CupiCodeException( "No llego la pista." ));
            }
            
        }
        catch( CupiCodeException e )
        {
            principal.mostrarError(e);
        }
    }
}
