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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.cupiCode.cliente.mundo.Codigo;

/**
 * Panel con el código secreto del jugador y la información del oponente.
 */
public class PanelCodigos extends JPanel
{
    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------

    /**
     * Etiqueta código oponente.
     */
    private JLabel lbCodigoAdv;
    
    /**
     * Etiqueta alias del oponente.
     */
    private JLabel lbAlias;
    
    /**
     * Etiqueta con el alias del oponente.
     */
    private JLabel lbAliasOponente;
    
    /**
     * Etiqueta victorias.
     */
    private JLabel lbVictorias;
    
    /**
     * Etiqueta con las victorias del oponente.
     */
    private JLabel lbVictoriasOponente;
    
    /**
     * Etiqueta victorias.
     */
    private JLabel lbDerrotas;
    
    /**
     * Etiqueta con las victorias del oponente.
     */
    private JLabel lbDerrotasOponente;

    /**
     * Imágenes de colores.
     */
    private JLabel[] colores;

    /**
     * Imágenes de colores oponente.
     */
    private JLabel[] coloresOponente;

    /**
     * Indica si el código del jugador fue inicializado o no.
     */
    private boolean hayCodigoJugador;
    
    /**
     * Panel del código del oponente
     */
    private JPanel panelOponente;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Constructor del panel que muestra el código secreto del jugador y la información del oponente.
     */
    public PanelCodigos( )
    {
        setLayout( new BorderLayout( ) );

        lbCodigoAdv = new JLabel( "Última jugada: " );
        lbAlias = new JLabel( "Alias: " );
        lbAliasOponente = new JLabel( "" );
        lbVictorias = new JLabel( "Juegos ganados: " );
        lbVictoriasOponente = new JLabel( "" );
        lbDerrotas = new JLabel( "Juegos perdidos: " );
        lbDerrotasOponente = new JLabel( "" );
          
        colores = new JLabel[Codigo.CANT_COLORES];
        coloresOponente = new JLabel[Codigo.CANT_COLORES];

        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            colores[ i ] = new JLabel( );
            colores[ i ].setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );

            coloresOponente[ i ] = new JLabel( );
            coloresOponente[ i ].setIcon( new ImageIcon( "./data/imagenes/sinColor.png" ) );
        }
        hayCodigoJugador = false;
        
        JPanel panelCodigoSecreto = new JPanel();
        panelCodigoSecreto.setLayout( new BorderLayout( ) );
        panelCodigoSecreto.setBorder( BorderFactory.createTitledBorder( "Código secreto" ) );
        JPanel panelCentral = new JPanel( new GridLayout( 1, 4 ) );
        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            panelCentral.add( colores[ i ] );
        }
        panelCodigoSecreto.add(panelCentral, BorderLayout.CENTER );

        panelOponente = new JPanel( );
        panelOponente.setBorder( BorderFactory.createTitledBorder( "Información oponente:" ) );
        panelOponente.setLayout( new BorderLayout( ) );
        panelOponente.add( lbCodigoAdv, BorderLayout.WEST);
        
        JPanel panelEtiquetas = new JPanel( new GridLayout( 4, 1 ) );
        panelEtiquetas.add( lbAlias );
        panelEtiquetas.add( lbVictorias );
        panelEtiquetas.add( lbDerrotas );
        panelEtiquetas.add( lbCodigoAdv );
        panelOponente.add( panelEtiquetas, BorderLayout.WEST);
        
        JPanel panelCentral2 = new JPanel( new GridLayout( 1, 4 ) );
        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            panelCentral2.add( coloresOponente[ i ] );
        }
        
        JPanel panelContenido = new JPanel( new GridLayout( 4, 1 ) );
        panelContenido.add( lbAliasOponente );
        panelContenido.add( lbVictoriasOponente );
        panelContenido.add( lbDerrotasOponente );
        panelContenido.add( panelCentral2 );
        panelOponente.add( panelContenido, BorderLayout.CENTER);

        add( panelCodigoSecreto, BorderLayout.NORTH );
        add( panelOponente, BorderLayout.CENTER );
    }

    // --------------------------------------------------------
    // Métodos
    // --------------------------------------------------------

    /**
     * Determina si el código del jugador fue inicializado.
     * @return True si fue inicializado o false de lo contrario.
     */
    public boolean hayCodigoJugador( )
    {
        return hayCodigoJugador;
    }

    /**
     * Modifica el código del jugador.
     * @param pCodigo Código del jugador. pCodigo != null.
     */
    public void modificarCodigoJugador( Codigo pCodigo )
    {
        String[] coloresCod = pCodigo.darColores( );
        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            if( coloresCod[ i ].equals( Codigo.AMARILLO ) )
                colores[ i ].setIcon( new ImageIcon( "./data/imagenes/amarillo.png" ) );

            else if( coloresCod[ i ].equals( Codigo.AZUL ) )
                colores[ i ].setIcon( new ImageIcon( "./data/imagenes/azul.png" ) );

            else if( coloresCod[ i ].equals( Codigo.ROJO ) )
                colores[ i ].setIcon( new ImageIcon( "./data/imagenes/rojo.png" ) );

            else if( coloresCod[ i ].equals( Codigo.VERDE ) )
                colores[ i ].setIcon( new ImageIcon( "./data/imagenes/verde.png" ) );
        }
        hayCodigoJugador = true;
    }

    /**
     * Modifica el código del oponente.
     * @param pCodigo Código del oponente. pCodigo != null.
     */
    public void modificarCodigoAdversario( Codigo pCodigo )
    {
        String[] coloresCod = pCodigo.darColores( );
        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            if( coloresCod[ i ].equals( Codigo.AMARILLO ) )
                coloresOponente[ i ].setIcon( new ImageIcon( "./data/imagenes/amarillo.png" ) );

            else if( coloresCod[ i ].equals( Codigo.AZUL ) )
                coloresOponente[ i ].setIcon( new ImageIcon( "./data/imagenes/azul.png" ) );

            else if( coloresCod[ i ].equals( Codigo.ROJO ) )
                coloresOponente[ i ].setIcon( new ImageIcon( "./data/imagenes/rojo.png" ) );

            else if( coloresCod[ i ].equals( Codigo.VERDE ) )
                coloresOponente[ i ].setIcon( new ImageIcon( "./data/imagenes/verde.png" ) );
        }
    }

    /**
     * Actualizar la información del oponente.
     * @param pAlias Alias del oponente. pAlias!=null &pAlias!="".
     * @param pVictorias Victorias del oponente. pVictorias!=null && pVictorias!="".
     * @param pDerrotas Derrotas del oponente. pDerrotas!=null && pDerrotas!="".
     */
    public void actualizarInformacionOponente(String pAlias, String pVictorias, String pDerrotas)
    {    
       lbAliasOponente.setText( pAlias );
       lbVictoriasOponente.setText( pVictorias );
       lbDerrotasOponente.setText( pDerrotas );
    }
}
