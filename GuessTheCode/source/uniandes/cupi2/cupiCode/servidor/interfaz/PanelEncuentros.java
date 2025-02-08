package uniandes.cupi2.cupiCode.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Collection;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel que contiene los encuentros actuales. 
 * @author JuanUrrea
 */
public class PanelEncuentros extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón Refrescar.
	 */
	private final static String REFRESCAR = "Refrescar";


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazCupiCode principal;

	/**
	 * Lista de encuentros.
	 */
	@SuppressWarnings("rawtypes")
	private JList listaEncuentros;

	/**
	 * Botón Refrescar.
	 */
	private JButton botonRefrescar;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel.
	 * @param interfaz Ventana principal de la aplicación.
	 */
	@SuppressWarnings("rawtypes")
	public PanelEncuentros(InterfazCupiCode interfaz)
	{
		principal = interfaz;
		setLayout( new BorderLayout());
		setSize(new Dimension(500,200));    
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(500, 150));
		listaEncuentros = new JList();
		scroll.getViewport().add(listaEncuentros);
		add(scroll, BorderLayout.CENTER);  
		JPanel panelRefrescar= new JPanel(); 
		panelRefrescar.setLayout(new GridBagLayout());
		botonRefrescar = new JButton("Refrescar");
		botonRefrescar.addActionListener(this);
		botonRefrescar.setActionCommand(REFRESCAR);
		panelRefrescar.add(botonRefrescar);
		add(panelRefrescar, BorderLayout.SOUTH);
		setBorder(new TitledBorder("Encuentros"));
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza el panel de encuentros.
	 * @param encuentros actualmente en curso.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actualizarEncuentros(Collection encuentros)
	{
		listaEncuentros.setListData( encuentros.toArray());
	}

	/**
	 * Maneja los eventos del botón
	 * @param e evento de click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(REFRESCAR))
		{
			principal.actualizarEncuentros();
		}
	}
}
