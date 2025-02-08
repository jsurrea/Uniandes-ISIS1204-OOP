package uniandes.cupi2.cupiCode.servidor.interfaz;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uniandes.cupi2.cupiCode.servidor.mundo.*;

/**
 * Ventana principal del servidor de la aplicación.
 * @author JuanUrrea
 */
public class InterfazCupiCode extends JFrame
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;	


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Clase principal del servidor.
	 */
	private CupiCode servidor;

	/**
	 * Panel con la información de los encuentros actuales.
	 */
	private PanelEncuentros panelEncuentros;

	/**
	 * Panel con las estadísticas de los jugadores.
	 */
	private PanelEstadisticas panelEstadisticas;

	/**
	 * Panel de extensión.
	 */
	private PanelOpciones panelOpciones;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa la ventana.
	 * @param pServidor Clase principal del mundo del servidor.
	 */
	public InterfazCupiCode(CupiCode pServidor)
	{
		servidor = pServidor;
		setLayout(new GridLayout(3,1));
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Servidor CupiCode");
		setLocationRelativeTo(null);
		panelEncuentros = new PanelEncuentros(this);
		panelEstadisticas = new PanelEstadisticas(this);
		panelOpciones = new PanelOpciones(this);
		actualizarEncuentros();
		actualizarJugadores();
		add(panelEncuentros);
		add(panelEstadisticas);
		add(panelOpciones);
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza la colección de encuentros.
	 */
	public void actualizarEncuentros()
	{
		panelEncuentros.actualizarEncuentros(servidor.darListaActualizadaEncuentros());
	}

	/**
	 * Actualiza la lista de jugadores.
	 */
	@SuppressWarnings({ "rawtypes" })
	public void actualizarJugadores()
	{
		try
		{
			Collection jugadores = servidor.darAdministradorResultados().consultarTodos();
			panelEstadisticas.actualizarJugadores(jugadores);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,  "Ocurrió un error consultando la lista de jugadores:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Elimina el jugador cuyo alias es introducido por el usuario.
	 */
	public void eliminarJugador()
	{
		try
		{
			String resultado = JOptionPane.showInputDialog(this, "Si desea eliminar un usuario, digite su alias:", "Eliminar Jugador", JOptionPane.WARNING_MESSAGE);
			servidor.darAdministradorResultados().eliminarUsuario(resultado);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar al usuario:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método para la extensión 1.
	 */
	public void opcion1()
	{
		JOptionPane.showMessageDialog(this, servidor.metodo1(), "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método para la extensión 2.
	 */
	public void opcion2()
	{
		JOptionPane.showMessageDialog(this, servidor.metodo2(), "Respuesta", JOptionPane.INFORMATION_MESSAGE);
	}


	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Ejecuta la aplicación.
	 * @param args parámetros de ejecución.
	 */
	public static void main( String[] args )
	{
		try
		{
			String archivoPropiedades = "./data/servidor.properties";
			CupiCode pServidor = new CupiCode(archivoPropiedades);
			InterfazCupiCode interfaz = new InterfazCupiCode(pServidor);
			interfaz.setVisible(true);
			pServidor.recibirConexiones();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
