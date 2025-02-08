/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_sudoku
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.sudoku.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa el panel donde se encuentran los botones de opciones.
 * @author JuanUrrea
 */
public class PanelOpciones extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón Cargar.
	 */
	public final static String CARGAR = "Cargar";

	/**
	 * Constante para el botón Reiniciar.
	 */
	public final static String REINICIAR = "Reiniciar";

	/**
	 * Constante para el botón Validar.
	 */
	public final static String VALIDAR = "Validar";

	/**
	 * Constante para el botón Solucionar.
	 */
	public final static String SOLUCIONAR = "Solucionar";

	/**
	 * Constante para el botón Opción 1.
	 */
	public final static String OPCION1 = "Opcion1";

	/**
	 * Constante para el botón Opción 2.
	 */
	public final static String OPCION2 = "Opcion2";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Botón Cargar.
	 */
	private JButton btCargar;

	/**
	 * Botón Reiniciar.
	 */
	private JButton btReiniciar;

	/**
	 * Botón Validar.
	 */
	private JButton btValidar;

	/**
	 * Botón Solucionar.
	 */
	private JButton btSolucionar;

	/**
	 * Botón Opción 1.
	 */
	private JButton btOpcion1;

	/**
	 * Botón Opción 2.
	 */
	private JButton btOpcion2;

	/**
	 * Interfaz del programa.
	 */
	private InterfazSudoku ventana;


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea un nuevo panel de opciones.
	 * Post: Se ha establecido la distribución y el borde del pánel. Se ha inicializado ventana
	 * y los botones. Estos se han añadido al pánel, se les ha añadido ActionListener y su
	 * comando; además, se han deshabilitado.
	 * @param i Interfaz del programa.
	 */
	public PanelOpciones(InterfazSudoku i)
	{
		setBorder(BorderFactory.createTitledBorder("Opciones"));
		setLayout( new GridLayout(1, 6, 5, 5));

		ventana = i;
		btCargar = new JButton("Cargar");
		btReiniciar = new JButton("Reiniciar");
		btValidar = new JButton("Validar");
		btSolucionar = new JButton("Solucionar");
		btOpcion1 = new JButton("Opción 1");
		btOpcion2 = new JButton("Opción 2");

		add(btCargar);
		add(btReiniciar);
		add(btValidar);
		add(btSolucionar);
		add(btOpcion1);
		add(btOpcion2);

		btCargar.addActionListener(this);
		btReiniciar.addActionListener(this);
		btValidar.addActionListener(this);
		btSolucionar.addActionListener(this);
		btOpcion1.addActionListener(this);
		btOpcion2.addActionListener(this);

		btCargar.setActionCommand(CARGAR);
		btReiniciar.setActionCommand(REINICIAR);
		btValidar.setActionCommand(VALIDAR);
		btSolucionar.setActionCommand(SOLUCIONAR);
		btOpcion1.setActionCommand(OPCION1);
		btOpcion2.setActionCommand(OPCION2);

		btReiniciar.setEnabled(false);
		btValidar.setEnabled(false);
		btSolucionar.setEnabled(false);
		btOpcion1.setEnabled(false);
		btOpcion2.setEnabled(false);
	}

	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Activa todos los botones.
	 * Pre: Los botones se encuentran inicializados.
	 * Post: Los botones han quedado activados.
	 */
	public void activarBotones()
	{
		btReiniciar.setEnabled(true);
		btValidar.setEnabled(true);
		btSolucionar.setEnabled(true);
		btOpcion1.setEnabled(true);
		btOpcion2.setEnabled(true);
	}

	/**
	 * Desactiva los botones Validar y Solucionar.
	 * Pre: Los botones se encuentran inicializados.
	 * Post: Los botones han quedado desactivados.
	 */
	public void desactivarBotones()
	{
		btValidar.setEnabled(false);
		btSolucionar.setEnabled(false);
	}

	/**
	 * Manejo de eventos del usuario.
	 * @param e Evento de usuario. e != null.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(CARGAR))
		{
			ventana.cargar();
		}
		else if(comando.equals(REINICIAR))
		{
			ventana.reiniciar();
		}
		else if(comando.equals(VALIDAR))
		{
			ventana.validar();
		}
		else if (comando.equals(SOLUCIONAR))
		{
			ventana.solucionar();
		}
		else if(comando.equals(OPCION1))
		{
			ventana.reqFuncOpcion1();
		}
		else if(comando.equals(OPCION2))
		{
			ventana.reqFuncOpcion2();
		}
	}
}
