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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Clase que se encarga de actualizar los códigos, manejar las comunicaciones con el servidor y generar los mensajes con la información del juego para el jugador.<br>
 * Clase conoce la parte fija de los mensajes del protocolo de comunicación.<br>
 * <b>inv:</b><br>
 * estadoJuego pertenece a {SIN_CONECTAR, ESPERANDO_LOCAL, ESPERANDO_OPONENTE, ESPERANDO_RESPUESTA}<br>
 * estadoJuego = SIN_CONECTAR cuando el cliente no se ha conectado al servidor o cuando el juego ha terminado<br>
 * estadoJuego = SIN_CONECTAR => juegoTerminado = true<br>
 * estadoJuego != SIN_CONECTAR => canal != null<br>
 * estadoJuego != SIN_CONECTAR => out != null<br>
 * estadoJuego != SIN_CONECTAR => in != null<br>
 * estadoJuego != SIN_CONECTAR => codigo != null<br>
 * estadoJuego != SIN_CONECTAR => codigoAdversario != null<br>
 * estadoJuego != SIN_CONECTAR => servidor != null<br>
 * estadoJuego != SIN_CONECTAR => nombreJugador != null <br>
 * estadoJuego != SIN_CONECTAR => puerto > 0
 */
public class Jugador
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que no se ha establecido la conexión con el servidor para jugar.
     */
    public static final int SIN_CONECTAR = 0;

    /**
     * Indica que se está esperando que el jugador local realice una jugada.
     */
    public static final int ESPERANDO_LOCAL = 1;

    /**
     * Indica que se está esperando a que el oponente realice una jugada.
     */
    public static final int ESPERANDO_OPONENTE = 2;

    /**
     * Indica que se acaba de enviar la jugada del jugador local y se está esperando la respuesta del cliente.
     */
    public static final int ESPERANDO_RESPUESTA = 3;

    /**
     * Indica que el jugador se acaba de conectar con el servidor y está esperando un oponente para iniciar un juego.
     */
    public static final int ESPERANDO_ENCUENTRO = 4;
    

    /**
     * Mensaje inicial de un jugador.
     */
    public static final String JUGADOR = "JUGADOR";
    
    /**
     * Mensaje de error.
     */
    public static final String ERROR = "ERROR";

    /**
     * Mensaje para indicar que un jugador tiene el primer turno.
     */
    public static final String PRIMER_TURNO = "TURNO:1";

    /**
     * Mensaje para indicar que un jugador tiene el segundo turno.
     */
    public static final String SEGUNDO_TURNO = "TURNO:2";

    /**
     * Mensaje para enviar la información de una jugada.
     */
    public static final String JUGADA = "JUGADA";
    
    /**
     * Mensaje para enviar una pista.
     */
    public static final String PISTA = "PISTA";

    /**
     * Mensaje para recibir el resultado de una jugada.
     */
    public static final String RESULTADO = "RESULTADO";

    /**
     * Mensaje para indicar que un jugador descubrió la combinación de colores del contrincante.
     */
    public static final String FIN_JUEGO = "FIN_JUEGO";

    /**
     * Mensaje para indicar quien fue el ganador del juego.
     */
    public static final String GANADOR = "GANADOR";
    
    /**
     * Mensaje para enviar la información del oponente.
     */
    public static final String INFO = "INFO";
    
    /**
     * Mensaje para escoger un color para la asignación del turno.
     */
    public static final String ESCOGER_COLOR = "ESCOGER_COLOR";
    
    /**
     * Mensaje que indica que el jugador pasó el turno.
     */
    public static final String PASO = "PASO";
    
    /**
     * Mensaje para escoger enviar un color.
     */
    public static final String COLOR = "COLOR";
    
    /**
     * Constante color amarillo.
     */
    public final static String AMARILLO = "Y";

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

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Código del jugador.
     */
    private Codigo codigo;

    /**
     * Último código enviado por el oponente.
     */
    private Codigo codigoOponente;

    /**
     * Historial de códigos enviados por el jugador.
     */
    private ArrayList historial;

    /**
     * Indica el estado actual del juego.
     */
    private int estadoJuego;

    /**
     * Indicador para saber si un juego ya terminó.
     */
    private boolean juegoTerminado;

    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Nombre del jugador.
     */
    private String nombre;

    /**
     * Dirección de servidor al cual está conectado.
     */
    private String servidor;

    /**
     * Puerto usado para conectarse.
     */
    private int puerto;

    /**
     * Nombre del ganador del juego actual.
     */
    private String aliasGanador;

    /**
     * Canal usado para comunicarse con el servidor.
     */
    private Socket canal;

    /**
     * Flujo que envía los datos al servidor a través del socketServidor.
     */
    private PrintWriter out;

    /**
     * Flujo de donde se leen los datos que llegan del servidor a través del socketServidor.
     */
    private BufferedReader in;
       
    /**
     * Indica si el jugador perdió turno.
     */
    private boolean perdioTurno;
    
    /**
     * Indica si el jugador tiene dos turnos.
     */
    private boolean dosTurnos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el juego de CupiCode.
     * <b>post: </b> La información del jugador quedó inicializada.<br>
     */
    public Jugador( )
    {
        codigo = null;
        codigoOponente = null;
        historial = new ArrayList( );
        nombre = "";
        servidor = "localhost";
        puerto = 9999;
        estadoJuego = SIN_CONECTAR;
        juegoTerminado = true;
        aliasGanador = "";
        verificarInvariante( );
    }

    /**
     * Retorna el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String darNombre( )
    {
        return nombre;
    }
    
    /**
     * Retorna el alias del jugador.
     * @return Alias del jugador.
     */
    public String darAlias( )
    {
        return alias;
    }

    /**
     * Retorna la dirección del servidor.
     * @return Dirección del servidor.
     */
    public String darDireccionServidor( )
    {
        return servidor;
    }

    /**
     * Retorna el puerto usado para conectarse al servidor.
     * @return Puerto de comunicación.
     */
    public int darPuertoServidor( )
    {
        return puerto;
    }

    /**
     * Retorna el alias del jugador que ganó el juego actual.
     * @return Alias del ganador del juego.
     */
    public String darAliasGanador( )
    {
        return aliasGanador;
    }

    /**
     * Retorna el estado actual del juego.
     * @return Estado del juego.
     */
    public int darEstadoJuego( )
    {
        return estadoJuego;
    }

    /**
     * Retorna el código del jugador.
     * @return Código del jugador.
     */
    public Codigo darCodigoJugador( )
    {
        return codigo;
    }

    /**
     * Retorna el último código del oponente.
     * @return Último código del oponente.
     */
    public Codigo darCodigoOponente( )
    {
        return codigoOponente;
    }

    /**
     * Retorna el historial de intentos del jugador.
     * @return Historial de intentos del jugador.
     */
    public ArrayList darHistorial( )
    {
        return historial;
    }

    /**
     * Indica si el juego actual ya terminó.
     * @return True si el juego terminó o false en caso contrario.
     */
    public boolean juegoTerminado( )
    {
        return juegoTerminado;
    }
    
    /**
     * Indica si el jugador tiene dos turnos.
     * @return True si tiene dos turnos, false en caso contrario.
     */
    public boolean tieneDosTurnos()
    {
        return dosTurnos;
    }

    /**
     * Establece la conexión con el servidor del juego y envía los datos del jugador para poder empezar un juego.<br>
     * Este método termina cuando se consigue un oponente y se establece la conexión entre los dos jugadores.
     * <b>post:</b> Quedó conectado al servidor, canal de escritura y de lectura quedan inicializados.<br>
     * @param pAlias Alias del jugador. pAlias !=null && pAlias !="".
     * @param pNombre Nombre del jugador local. pNombre != null && pNombre!="".
     * @param pDireccion La dirección usada para encontrar el servidor. pDireccion != null && pDireccion!="".
     * @param pPuerto Puerto usado para realizar la conexión. pPuerto > 0.
     * @param pColores Arreglo de colores del código de este jugador. pColores != null.
     * @throws CupiCodeException Se lanza esta excepción si hay problemas estableciendo la comunicación.
     */
    public void conectar( String pAlias, String pNombre, String pDireccion, int pPuerto, String[] pColores ) throws CupiCodeException
    {
        alias = pAlias;
        nombre = pNombre;
        servidor = pDireccion;
        puerto = pPuerto;
        try
        {
            // Conectar al servidor
            canal = new Socket( pDireccion, pPuerto );
            out = new PrintWriter( canal.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
            // iniciar el juego
            iniciarJuego( pColores );
        }
        catch( UnknownHostException e )
        {
            throw new CupiCodeException( "No fue posible establecer una conexión al servidor. " );
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "No fue posible establecer una conexión al servidor. " );
        }
        verificarInvariante( );
    }

    /**
     * Recibe la jugada del oponente, la procesa y envía la respuesta al servidor. <br>
     * Si el juego termina, este método debe cambiar el valor de juegoTerminado<br>
     * <b>pre:</b> estadoJuego = ESPERANDO_OPONENTE.
     * <b>post:</b> Se actualizó estadoJuego.
     * @throws CupiCodeException Se lanza esta excepción si hay problemas en la comunicación.
     * @return Mensaje que se quiere visualizar.
     */
    public String recibirJugada( ) throws CupiCodeException
    {
        try
        {
            // JUGADA:<Color1>:<Color2>:<Color3>:<Color4>
            String datosJugada[] = in.readLine( ).split( ":" );
            String mensaje = "";
            if(datosJugada[0].equals( "JUGADA" ))
            {
            
                String[] colores = new String[Codigo.CANT_COLORES];
                String [] jugada = datosJugada[1].split( "-" );
    
                for( int i = 0; i < Codigo.CANT_COLORES; i++ )
                {
                    colores[ i ] = jugada[ ( i  ) ];
                }
    
                // Registrar el intento del oponente
                codigoOponente.cambiarColores( colores );
                int cantColores = codigoOponente.calcularCantidadColoresCorrectos( codigo );
                int cantPosiciones = codigoOponente.calcularCantidadPosicionesCorrectas( codigo );
    
                // Responder al oponente sobre su intento
                if( cantPosiciones == Codigo.CANT_COLORES )
                {
                    // El juego termina por el que oponente adivinó el código
                    juegoTerminado = true;
                    out.println( FIN_JUEGO );
                }
                else
                {
                    out.println( RESULTADO + ":" + cantColores + ":" + cantPosiciones );
                }
                
                
                if(perdioTurno &&!juegoTerminado)
                {
                    perdioTurno = false;
                    recibirJugada( );
                    estadoJuego = ESPERANDO_LOCAL;
                }
                else
                {
                    estadoJuego = ESPERANDO_LOCAL;
                }
            }
            else if(datosJugada[0].equals( "PASO" ))
            {
                mensaje = "Tiene dos turnos ya que su oponente pidió una pista.";
                estadoJuego = ESPERANDO_LOCAL;
                dosTurnos = true;
            }
            else if(datosJugada[0].equals( "ERROR" ))
            {
                throw new CupiCodeException(datosJugada[1]);
            }
            verificarInvariante( );

            return mensaje;
        }
        catch( Exception e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor. " );
        }
    }

    /**
     * Envía una jugada y procesa la respuesta del oponente. <br>
     * <b>pre:</b>estadoJugada = ESPERANDO_LOCAL.
     * <b>post: </b> Se envió la jugada al servidor.<br>
     * @param pColores Combinación de colores escogida por el jugador. pColores != null.
     * @throws CupiCodeException Se lanza esta excepción si hay problemas en la comunicación.
     */
    public void enviarJugada( String[] pColores ) throws CupiCodeException
    {
        try
        {
            // Enviar el mensaje
            String mensaje = JUGADA+":";
            for( int i = 0; i < Codigo.CANT_COLORES; i++ )
            {
                if(i==Codigo.CANT_COLORES-1)
                {
                    mensaje += pColores[ i ];
                }
                else
                {
                    mensaje += pColores[ i ]+"-";
                }
                
            }
            
            out.println( mensaje );
            estadoJuego = ESPERANDO_RESPUESTA;

            // Leer la respuesta enviada por el oponente
            String respuesta = in.readLine( );

            if( respuesta.startsWith( FIN_JUEGO ) )
            {
                juegoTerminado = true;
                Codigo nuevo = new Codigo( pColores );
                nuevo.cambiarCantidadColoresCorrectos( Codigo.CANT_COLORES );
                nuevo.cambiarCantidadPosicionesCorrectas( Codigo.CANT_COLORES );
                historial.add( nuevo );
            }
            else
            {
                String datosRespuesta[] = respuesta.split( ":" );
                
                // RESPUESTA:<cantColores>:<cantPosiciones>
                Codigo nuevo = new Codigo( pColores );
                nuevo.cambiarCantidadColoresCorrectos( Integer.parseInt( datosRespuesta[ 1 ] ) );
                nuevo.cambiarCantidadPosicionesCorrectas( Integer.parseInt( datosRespuesta[ 2 ] ) );
                historial.add( nuevo );
            }
            if(dosTurnos && !juegoTerminado)
            {
                estadoJuego = ESPERANDO_LOCAL;
                dosTurnos=false;
            }
            else
            {
                estadoJuego = ESPERANDO_OPONENTE;
            }
            
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor. ");
        }
        verificarInvariante( );
    }
    
    /**
     * Pide una pista al servidor.
     * <b>post: </b> Se pidió una pista al servidor.<br>
     * @return Pista dada por servidor, null si no le llegó ninguna pista.
     * @throws CupiCodeException Se lanza un excepción si se piden más de dos pistas.
     */
    public String pedirPista() throws CupiCodeException
    {   
        try
        {
            // Enviar el mensaje
            String mensaje = PISTA;
            out.println( mensaje );
            estadoJuego = ESPERANDO_RESPUESTA;

            // Leer la respuesta enviada por el oponente
            String respuesta = in.readLine( );

            if( respuesta.startsWith( PISTA ) )
            {
                String pista = respuesta.split( ":" )[1];
                if(pista.equals( ERROR ))
                {
                    estadoJuego = ESPERANDO_LOCAL;
                    throw new CupiCodeException( "Solo se pueden pedir dos pistas por juego." );
                }
                else
                {
                    estadoJuego = ESPERANDO_OPONENTE;
                    perdioTurno = true;
                    return pista;
                }

            }
            else
            {
                throw new CupiCodeException( "Recibió un mensaje inesperado." );
            }
            
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor.");
        }
    }

    /**
     * Realiza las tareas necesarias para terminar el juego.<br>
     * Se obtiene el nombre del ganador, la conexión con el servidor se cierra y el estado del juego pasa a SIN_CONECTAR.<br>
     * <b>pre:</b>juegoTerminado = true <br>
     * <b>post:</b> Se cierra la conexión al servidor y se cierran los canales de lectura y escritura. <br>
     * @throws CupiCodeException Se lanza esta excepción si hay problemas en la comunicación.
     */
    public void terminarJuego( ) throws CupiCodeException
    {
        try
        {
            // Leer el mensaje con el nombre del ganador
            // GANADOR:<alias>
            String mensajeFin = in.readLine( );
            aliasGanador = mensajeFin.split( ":" )[ 1 ];
            estadoJuego = SIN_CONECTAR;

            // Cerrar la conexión al servidor
            out.close( );
            in.close( );
            canal.close( );

            out = null;
            in = null;
            canal = null;
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor. " );
        }
        verificarInvariante( );
    }

    /**
     * Envía el color seleccionado por el usuario y espera a que el servidor informa el turno asignado.
     * <b>post: </b> Envió el color para obtener el turno y asignó el turno obtenido al terminar el protocolo.<br>
     * @param pColor Color escogido por el usuario.
     * @return True si ya se terminó el proceso de determinar el turno o false en caso contrario.
     * @throws CupiCodeException Se lanza esta excepción si hay un problema leyendo del canal.
     */
    public boolean obtenerTurno( String pColor ) throws CupiCodeException
    {
        try
        {
            if(pColor==null)
            {
                //Comienza el protocolo de asignar turno, espera por mensaje del servidor para que el jugador empiece a escoger el color o el turno asignado por el servidor.
                String mensaje = in.readLine( );
                if( ESCOGER_COLOR.equals( mensaje ) )
                {
                    return false;
                }
                else if( PRIMER_TURNO.equals( mensaje ) )
                {
                    estadoJuego = ESPERANDO_LOCAL;
                    return true;
                }
                else if( SEGUNDO_TURNO.equals( mensaje ) )
                {
                    estadoJuego = ESPERANDO_OPONENTE;
                    return true;
                }
                else
                {
                    estadoJuego = SIN_CONECTAR;
                    if(mensaje ==null)
                    {
                        throw new CupiCodeException( "Ocurrió un error con su oponente. Reinicie la aplicación." );
                    }
                    else
                    {
                        if(ERROR.equals( mensaje.split( ":" )[0] ))
                        {
                            throw new CupiCodeException( mensaje.split( ":" )[1] );
                        }
                        else
                        {
                            throw new CupiCodeException( "El mensaje no fue el esperado." );
                        }
                    }
                }
            }
            else
            {
                //Envía el color escogido por el jugador y recibe la respuesta del servidor ya sea escoger otro color o asignar el turno.
                out.println(COLOR+":"+pColor);
                String mensaje = in.readLine( );
                if( ESCOGER_COLOR.equals( mensaje ) )
                {
                    return false;
                }
                else if( PRIMER_TURNO.equals( mensaje ) )
                {
                    estadoJuego = ESPERANDO_LOCAL;
                    return true;
                }
                else if( SEGUNDO_TURNO.equals( mensaje ) )
                {
                    estadoJuego = ESPERANDO_OPONENTE;
                    return true;
                }
                else
                {
                    estadoJuego = SIN_CONECTAR;
                    if(mensaje ==null)
                    {
                        throw new CupiCodeException( "Ocurrió un error con su oponente. Reinicie la aplicación." );
                    }
                    else
                    {
                        if(ERROR.equals( mensaje.split( ":" )[0] ))
                        {
                            throw new CupiCodeException( mensaje.split( ":" )[1] );
                        }
                        else
                        {
                            throw new CupiCodeException( "El mensaje no fue el esperado." );
                        }
                    }
                }
                
            } 
            
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor. " );
        }
    }
    
    /**
     * Recibe del servidor la información del oponente.
     * <b>post: </b> Se recibió la información del oponente.<br>
     * @return Información del oponente.
     * @throws CupiCodeException Se lanza esta excepción si hay un problema leyendo del canal.
     */
    public String obtenerInfoOponente( ) throws CupiCodeException
    {
        try
        {
            
            String informacion = in.readLine( );
            if(informacion == null)
            {
                estadoJuego = SIN_CONECTAR;
                throw new CupiCodeException( "Ocurrió un error con su oponente. Reinicie la aplicación." );
            }
            else if( informacion.split( ":" ).length>1 )
            {
                if(INFO.equals( informacion.split( ":" )[0] ))
                {
                   return informacion.split( ":" )[1];
                }
                else if(ERROR.equals( informacion.split( ":" )[0] ))
                {
                    throw new CupiCodeException( informacion.split( ":" )[1] );
                }
                else
                {
                    throw new CupiCodeException( "El mensaje no fue el esperado." );
                }
            }
            else
            {
                throw new CupiCodeException( "El mensaje no fue el esperado." );
            }
   
        }
        catch( IOException e )
        {
            throw new CupiCodeException( "Se presentaron problemas con la conexión al servidor. " );
        }
    }

    // -----------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------

    /**
     * Envía al servidor los mensajes necesarios para iniciar un juego.
     * <b>post: </b> Se envió la información para iniciar el juego.<br>
     * @param pColores Arreglo de colores del código de este jugador. pColores != null.
     * @throws IOException Se lanza esta excepción si hay un problema leyendo del canal.
     */
    private void iniciarJuego( String[] pColores ) throws IOException
    {
        juegoTerminado = false;
        aliasGanador = "";

        codigo = new Codigo( pColores );
        codigoOponente = new Codigo( );
        
        String cadenaColores = "";
        for(int i =0; i< pColores.length;i++)
        {
            if(i==pColores.length-1)
            {
                cadenaColores+=pColores[i]; 
            }
            else
            {
                cadenaColores+=pColores[i]+"-"; 
            }
            
        }

        // Enviar el nombre del jugador
        out.println( JUGADOR + ":" + alias+":"+nombre+":"+cadenaColores );

        // Actualizar estado a ESPERANDO_ENCUENTRO
        estadoJuego = ESPERANDO_ENCUENTRO;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * estadoJuego pertenece a {SIN_CONECTAR, ESPERANDO_LOCAL, ESPERANDO_OPONENTE, ESPERANDO_RESPUESTA}<br>
     * estadoJuego = SIN_CONECTAR => juegoTerminado = true<br>
     * estadoJuego != SIN_CONECTAR => canal != null<br>
     * estadoJuego != SIN_CONECTAR => out != null<br>
     * estadoJuego != SIN_CONECTAR => in != null<br>
     * estadoJuego != SIN_CONECTAR => codigo != null<br>
     * estadoJuego != SIN_CONECTAR => codigoAdversario != null<br>
     * estadoJuego != SIN_CONECTAR => servidor != null<br>
     * estadoJuego != SIN_CONECTAR => nombreJugador != null <br>
     * estadoJuego != SIN_CONECTAR => puerto > 0
     */
    private void verificarInvariante( )
    {
        assert ( estadoJuego == SIN_CONECTAR || estadoJuego == ESPERANDO_LOCAL || estadoJuego == ESPERANDO_OPONENTE || estadoJuego == ESPERANDO_RESPUESTA ) : "El estado no es válido.";
        if( estadoJuego == SIN_CONECTAR )
            assert juegoTerminado : "Valor inválido de atributo juegoTerminado.";
        else
        {
            assert ( canal == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión.";
            assert ( out == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión.";
            assert ( in == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión.";
            assert ( codigo != null ) : "El codigo del jugador no puede ser null.";
            assert ( codigoOponente != null ) : "El codigo del oponente no puede ser null.";
            assert ( servidor != null ) : "La dirección del servidor no puede ser null.";
            assert ( nombre != null ) : "El nombre del jugador no puede ser null.";
            assert ( puerto > 0 ) : "El puerto debe ser mayor a 0.";
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión 2.
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
