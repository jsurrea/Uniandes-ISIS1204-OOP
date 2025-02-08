package uniandes.cupi2.cupiCode.cliente.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con la opciones de la aplicación.
 */
public class PanelOpciones extends JPanel implements ActionListener
{

 // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando opción 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando opción 2.
     */
    private static final String OPCION_2 = "OPCION_2";
    
    /**
     * Comando pedir pista.
     */
    private static final String PEDIR_PISTA = "PEDIR_PISTA";

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón opción 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón opción 2.
     */
    private JButton btnOpcion2;
    
    /**
     * Botón pedir pista.
     */
    private JButton btnPedirPista;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazJugador principal;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
    /**
     * Constructor del panel con las opciones de la aplicación.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
     */
    public PanelOpciones( InterfazJugador pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 3, 1) );
        
        btnPedirPista = new JButton( "Pedir pista" );
        btnPedirPista.setActionCommand( PEDIR_PISTA );
        btnPedirPista.addActionListener( this );
        
        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );

        add( btnPedirPista );
        add( btnOpcion1 );
        add( btnOpcion2 );
        
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Desactiva o activa el botón de pedir pista.
     * @param pActivo true si el botón esta activo, false en caso contrario.
     */
    public void activarPedirPista( boolean pActivo )
    {
        btnPedirPista.setEnabled( pActivo );
        
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        if( OPCION_1.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( PEDIR_PISTA.equals( pEvento.getActionCommand( ) ) )
        {
            principal.pedirPista( );
        }
    }
}
