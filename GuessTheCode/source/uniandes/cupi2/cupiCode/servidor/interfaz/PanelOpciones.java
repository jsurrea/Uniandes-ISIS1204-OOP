package uniandes.cupi2.cupiCode.servidor.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.*;

/**
 * Panel que contiene los dos botones de opciones de extensión.
 * @author JuanUrrea
 */
public class PanelOpciones extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón Opción 1.
	 */
	private final static String OPCION1 = "Opción 1";

	/**
	 * Constante para el botón Opción 2.
	 */
	private final static String OPCION2 = "Opción 2";


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazCupiCode principal;

	/**
	 * Botón opción 1.
	 */
	private JButton btnOpcion1;

	/**
	 * Botón opción 2.
	 */
	private JButton btnOpcion2;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel.
	 * @param interfaz Ventana principal de la aplicación.
	 */
	public PanelOpciones(InterfazCupiCode interfaz)
	{
		principal = interfaz;
		setBorder(new TitledBorder("Opciones"));
		setLayout(new GridLayout(1,2));
		btnOpcion1 = new JButton("Opción 1");
		btnOpcion1.setActionCommand(OPCION1);
		btnOpcion1.addActionListener(this);
		add(btnOpcion1);
		btnOpcion2 = new JButton("Opción 2");
		btnOpcion2.setActionCommand(OPCION2);
		btnOpcion2.addActionListener(this);
		add(btnOpcion2);
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Maneja los eventos de los botones.
	 * @param e evento de click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(OPCION1))
		{
			principal.opcion1();
		}
		else if(comando.equals(OPCION2))
		{
			principal.opcion2();
		}
	}

}
