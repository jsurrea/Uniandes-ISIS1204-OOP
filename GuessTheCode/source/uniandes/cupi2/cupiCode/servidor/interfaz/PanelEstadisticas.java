package uniandes.cupi2.cupiCode.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Panel que contiene las estadísticas de los jugadores.
 * @author JuanUrrea
 */
public class PanelEstadisticas extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón refrescar.
	 */
	private final static String REFRESCAR = "Refrescar";

	/**
	 * Constante para el botón eliminar.
	 */
	private final static String ELIMINAR = "Eliminar";


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazCupiCode principal;

	/**
	 * Lista de jugadores.
	 */
	@SuppressWarnings("rawtypes")
	private JList listaJugadores;

	/**
	 * Botón Refrescar.
	 */
	private JButton btnRefrescar;

	/**
	 * Botón Eliminar.
	 */
	private JButton btnEliminar;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel.
	 * @param interfaz ventana principal de la aplicación.
	 */
	@SuppressWarnings("rawtypes")
	public PanelEstadisticas(InterfazCupiCode interfaz)
	{
		principal = interfaz;
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 150));
		listaJugadores = new JList();
		scroll.getViewport().add(listaJugadores);
		add(scroll, BorderLayout.CENTER);
		JPanel panelBotones= new JPanel();
		panelBotones.setLayout(new GridLayout(1,2));
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(this);
		btnRefrescar.setActionCommand(REFRESCAR);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setActionCommand(ELIMINAR);
		panelBotones.add(btnRefrescar);
		panelBotones.add(btnEliminar);
		add(panelBotones, BorderLayout.SOUTH);
		setBorder(new TitledBorder("Estadísticas jugadores"));
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza la lista de jugadores.
	 * @param jugadores Colección de jugadores.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void actualizarJugadores(Collection jugadores)
	{
		listaJugadores.setListData(jugadores.toArray());
	}

	/**
	 * Maneja los eventos de los botones.
	 * @param e evento de click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(REFRESCAR))
		{
			principal.actualizarJugadores();
		}
		else if(comando.equals(ELIMINAR))
		{
			principal.eliminarJugador();
		}
	}

}
