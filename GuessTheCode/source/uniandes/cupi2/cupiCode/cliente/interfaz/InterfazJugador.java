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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiCode.cliente.mundo.CupiCodeException;
import uniandes.cupi2.cupiCode.cliente.mundo.Jugador;
import uniandes.cupi2.cupiCode.cliente.mundo.ThreadConectar;
import uniandes.cupi2.cupiCode.cliente.mundo.ThreadEnviarJugada;
import uniandes.cupi2.cupiCode.cliente.mundo.ThreadRecibirJugada;
import uniandes.cupi2.cupiCode.cliente.mundo.ThreadPedirPista;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazJugador extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private Jugador jugador;

    // ---------------------------------------.--------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Barra con el menú de opciones del juego.
     */
    private BarraMenu barraMenu;

    /**
     * Panel con la imagen superior.
     */
    private PanelImagen panelImagen;

    /**
     * Panel donde se muestran los códigos.
     */
    private PanelCodigos panelCodigos;

    /**
     * Panel donde se envía una jugada.
     */
    private PanelEnviarJugada panelEnviarJugada;

    /**
     * Panel con el historial de las jugadas realizadas.
     */
    private PanelHistorial panelHistorial;
    
    /**
     * Panel con las opciones de la aplicación.
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz para un cliente del juego.
     */
    public InterfazJugador( )
    {
        jugador = new Jugador( );
        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 800, 600 );

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Cupi Code" );

        // Inicializar el menú de opciones
        barraMenu = new BarraMenu( this );
        setJMenuBar( barraMenu );

        // Construir los paneles
        panelImagen = new PanelImagen( );
        panelCodigos = new PanelCodigos( );
        panelEnviarJugada = new PanelEnviarJugada( this, "Sin conexión" );
        panelHistorial = new PanelHistorial( );
        panelOpciones = new PanelOpciones( this );

        JPanel panelCentral = new JPanel( new GridLayout( 1, 2 ) );

        JPanel panelOeste = new JPanel( new GridLayout( 2, 1 ) );
        panelOeste.add( panelEnviarJugada);
        panelOeste.add( panelHistorial );

        panelCentral.add( panelOeste );

        JPanel panelEste = new JPanel( new BorderLayout( )  );
        panelEste.add( panelCodigos, BorderLayout.CENTER );
        panelEste.add( panelOpciones, BorderLayout.SOUTH );

        panelCentral.add( panelEste );

        add( panelImagen, BorderLayout.NORTH );
        add( panelCentral, BorderLayout.CENTER );

        setResizable( false );
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra un diálogo para pedir la información necesaria para iniciar un juego nuevo de cupiCode.
     */
    public void iniciarConexion( )
    {
        DialogoConectar dialogo = new DialogoConectar( this, jugador.darDireccionServidor( ), jugador.darPuertoServidor( ) );
        dialogo.setVisible( true );
    }

    /**
     * Intenta realizar una conexión.<br>
     * El proceso de conexión al servidor se hace en un hilo de la clase ThreadConectar.
     * @param pDialogo Diálogo que fue usado para recibir los datos. pDialogo!=null.
     * @param pAlias Alias del jugador. pAlias!=null && pAlias!="". 
     * @param pNombre Nombre del jugador. pNombre!=null && pNombre!="". 
     * @param pDireccion Dirección donde se encuentra el servidor. pDireccion!=null && pDireccion!="". 
     * @param pPuerto Puerto usado para la conexión. pPuerto!=null && pPuerto!="". 
     * @param pColores Colores del código. pColores!=null. 
     */
    public void conectar( DialogoConectar pDialogo, String pAlias, String pNombre, String pDireccion, int pPuerto, String[] pColores )
    {
        pDialogo.dispose( );
        barraMenu.inhabilitaIniciarJuego( );
        panelHistorial.borrarHistorial( );
        Thread t = new ThreadConectar( jugador, this, pAlias, pNombre, pDireccion, pPuerto, pColores );
        t.start( );
    }

    /**
     * Recibe la jugada del oponente. <br>
     * Las tareas relacionadas con esperar una jugada del oponente se hacen en un hilo de la clase ThreadEsperarJugada.
     */
    public void recibirJugada( )
    {
        if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
        {
            panelEnviarJugada.inhabilitarControles( );
            panelOpciones.activarPedirPista(false);
            Thread t = new ThreadRecibirJugada( jugador, this );
            t.start( );
            
        }
        
    }

    /**
     * Enviar una jugada al oponente. <br>
     * Las tareas relacionadas con enviar una jugada y esperar la respuesta del oponente se hacen en un hilo de la clase ThreadEnviarJugada. <br>
     * @param pColores Colores de la jugada. pColores!=null.
     */
    public void enviarJugada( String[] pColores )
    {
        if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
        {
            panelOpciones.activarPedirPista(true);
            Thread t = new ThreadEnviarJugada( jugador, this, pColores );
            t.start( );
        }
    }

    /**
     * Muestra cual fue el jugador que ganó el encuentro y le avisa al usuario.
     */
    public void mostrarGanador( )
    {
        panelEnviarJugada.inhabilitarControles( );
        barraMenu.habilitarIniciarJuego( );
        panelOpciones.activarPedirPista(false);
        if(jugador.darAliasGanador( ).equals( jugador.darAlias( ) ))
        {
            JOptionPane.showMessageDialog( this, "Felicitaciones. Ganó la partida.", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "El ganador del encuentro fue " + jugador.darAliasGanador( ).toUpperCase( ), "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
        }
        
    }

    /**
     * Actualiza la interfaz del usuario.
     */
    public void actualizarInterfaz( )
    {
        if(jugador.darCodigoJugador( ) != null)
        {
            panelCodigos.modificarCodigoJugador( jugador.darCodigoJugador( ) );
        }
        realizarCambios( );
        desactivarPedirPista();
    }
    
    /**
     * Desactiva o activa el botón de pedir pista.
     */
    public void desactivarPedirPista()
    {
        panelOpciones.activarPedirPista(!jugador.tieneDosTurnos( ));
    }
    
    /**
     * Actualiza la información del oponente.
     * @param pInformacion Información del oponente. pInformacion!=null && pInformacion!="".
     */
    public void actualizarInfoOponente(String pInformacion)
    {
        String pAlias = pInformacion.split( "-" )[0];
        String pVictorias = pInformacion.split( "-" )[1];
        String pDerrotas = pInformacion.split( "-" )[2];
        panelCodigos.actualizarInformacionOponente( pAlias,pVictorias , pDerrotas );
    }

    /**
     * Realiza cambios dependiendo del estado del juego.
     */
    private void realizarCambios( )
    {
        if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
        {
            ArrayList historial = jugador.darHistorial( );
            if( historial.size( ) > 0 )
            {
                panelHistorial.agregarCodigos( historial );
            }
            panelCodigos.modificarCodigoAdversario( jugador.darCodigoOponente( ) );
            habilitarEnviar( );
            panelEnviarJugada.modificarEstado( "Jugando" );
        }
        else if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
        {
            ArrayList historial = jugador.darHistorial( );
            if( historial.size( ) > 0 )
            {
                panelHistorial.agregarCodigos( historial );
            }
            panelEnviarJugada.modificarEstado( "Esperando jugada" );
        }
        else if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_ENCUENTRO )
        {
            panelEnviarJugada.modificarEstado( "Esperando un oponente" );
        }
        else if( jugador.darEstadoJuego( ) == Jugador.SIN_CONECTAR )
        {
            panelEnviarJugada.modificarEstado( "Sin conexión" );
        }
    }

    /**
     * Habilita la opción de enviar jugada.
     */
    public void habilitarEnviar( )
    {
        if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
        {
            panelEnviarJugada.habilitarControles( );
            panelOpciones.activarPedirPista(true);
        }
    }
    
    /**
     * Pide una pista al servidor.
     * Las tareas relacionadas con pedir una pista y esperar la respuesta del oponente se hacen en un hilo de la clase ThreadPedirPista. <br>
     */
    public void pedirPista( )
    {
        if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
        {
            Thread t = new ThreadPedirPista( jugador, this );
            t.start( );
        }
        
    }
    
    /**
     * Muestra un dialogo para que el jugador escoja un color.
     * @return Color escogido por el usuario.
     */
    public String escogerColor()
    {
        String [] colores = {"Amarillo", "Azul", "Verde", "Rojo"};
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout( 2, 1 ) );
        JLabel lbl = new JLabel("Escoja un color: ");
        JComboBox listaColores = new JComboBox(colores);
        panel.add(lbl);
        panel.add(listaColores);
        int selectedOption = -1;
        String color = "";
        while(selectedOption != 0)
        {
            selectedOption = JOptionPane.showOptionDialog(this, panel, "Asignar turno", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
            color = ( String )listaColores.getSelectedItem( );
        }
        String colorSalida ="";
        if(color.equals( "Amarillo" ))
        {
            colorSalida = PanelHistorial.AMARRILLO;
        }
        else if(color.equals( "Azul" ))
        {
            colorSalida = PanelHistorial.AZUL;
        }
        else if(color.equals( "Verde" ))
        {
            colorSalida = PanelHistorial.VERDE;
        }
        else if(color.equals( "Rojo" ))
        {
            colorSalida = PanelHistorial.ROJO;
        }
        
        return colorSalida;
    }
    
    /**
     * Muestra un mensaje con el error enviado.
     * @param pExcepcion Excepción con el error. pExcepcion != null.
     */
    public void mostrarError( CupiCodeException pExcepcion )
    {
        JOptionPane.showMessageDialog( this, pExcepcion.getMessage( ), "CupiCode", JOptionPane.ERROR_MESSAGE );
        actualizarInterfaz( );
    }
    
    /**
     * Muestra un mensaje de información con el mensaje enviado.
     * @param pMensaje Mensaje que se quiere mostrar. pMensaje != null && pMensaje!=""..
     */
    public void mostrarInformacion(String pMensaje)
    {
        JOptionPane.showMessageDialog( this, pMensaje, "CupiCode", JOptionPane.INFORMATION_MESSAGE );
        actualizarInterfaz( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = jugador.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = jugador.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación, creando una nueva interfaz.
     * @param args Parámetros de ejecución que no son usados.
     */
    public static void main( String[] args )
    {

        InterfazJugador interfaz = new InterfazJugador( );
        interfaz.setVisible( true );
    }

    

    
}
