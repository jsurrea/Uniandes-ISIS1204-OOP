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

/**
 * Clase que representa un panel donde se muestra la información del Sudoku.
 * @author JuanUrrea
 */
public class PanelInformacion extends JPanel
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Etiqueta para el nombre del sudoku.
	 */
	private JLabel lbNombre;

	/**
	 * Etiqueta para el número de pistas iniciales.
	 */
	private JLabel lbPistas;

	/**
	 * Etiqueta para el número de casillas en blanco.
	 */
	private JLabel lbCasillas;

	/**
	 * Etiqueta para el número de movimientos
	 */
	private JLabel lbMovimientos;

	/**
	 * Caja de texto para el nombre del sudoku.
	 */
	private JTextField txtNombre;

	/**
	 * Caja de texto para el número de pistas iniciales.
	 */
	private JTextField txtPistas;

	/**
	 * Caja de texto para el número de casillas en blanco.
	 */
	private JTextField txtCasillas;

	/**
	 * Caja de texto para el número de movimientos
	 */
	private JTextField txtMovimientos;


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea un nuevo panel de información.
	 * Post: Se ha establecido el borde y la distribución del pánel. Se han inicializado las
	 * etiquetas y las cajas de texto. Las cajas de texto no se pueden editar. Tanto etiquetas
	 * como cajas de texto se han añadido al pánel de manera intercalada.
	 */
	public PanelInformacion()
	{
		setBorder(BorderFactory.createTitledBorder("Información"));
		setLayout(new GridLayout(4,2,5,5));

		lbNombre = new JLabel("Nombre");
		lbPistas = new JLabel("No. pistas iniciales");
		lbCasillas = new JLabel("Casillas en blanco");
		lbMovimientos = new JLabel("Movimientos realizados");

		txtNombre = new JTextField();
		txtPistas = new JTextField();
		txtCasillas = new JTextField();
		txtMovimientos = new JTextField();

		txtNombre.setEditable(false);
		txtPistas.setEditable(false);
		txtCasillas.setEditable(false);
		txtMovimientos.setEditable(false);

		add(lbNombre);
		add(txtNombre);
		add(lbPistas);
		add(txtPistas);
		add(lbCasillas);
		add(txtCasillas);
		add(lbMovimientos);
		add(txtMovimientos);
	}


	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Carga la información de las cajas de texto.
	 * Pre: Las cajas de texto ya se encuentran inicializadas.
	 * Post: Las cajas de texto ahora muestran los valores que llegaron por parámetro y 
	 * txtMovimientos inicia en 0.
	 * @param pNombre Nombre del Sudoku. pNombre != null && pNombre != "".
	 * @param pPistas Número de pistas iniciales del Sudoku. pPistas > 0
	 * @param pCasillas Número de casillas en blanco al cargar el Sudoku. pCasillas > 0
	 */
	public void cargarInformacion(String pNombre, int pPistas, int pCasillas)
	{
		txtNombre.setText(pNombre);
		txtPistas.setText(pPistas + "");
		txtCasillas.setText(pCasillas + "");
		txtMovimientos.setText(0+"");
	}

	/**
	 * Reestablece el número de casillas en blanco al que llega por parámetro y el número de 
	 * movimientos a 0.
	 * Pre: txtCasillas y txtMovimientos se encuentran inicializados.
	 * Post: txtMovimientos tiene como texto 0 y txtCasillas tiene como texto el número que 
	 * llegó por parámetro.
	 * @param pCasillas Número de casillas en blanco.
	 */
	public void reiniciarInformacion(int pCasillas)
	{
		txtCasillas.setText(pCasillas+"");
		txtMovimientos.setText(0+"");
	}

	/**
	 * Restablece el número de casillas en blanco a 0.
	 * Pre: txtCasillas se encuentra inicializado.
	 * Post: El texto de txtCasillas muestra 0.
	 */
	public void actualizarSolucion()
	{
		txtCasillas.setText(0+"");
	}

	/**
	 * Agrega un movimiento al número de movimientos realizados.
	 * Pre: txtMovimientos se encuentra inicializado.
	 * Post: Se ha aumentado en uno el valor de txtMovimientos.
	 * @param pComando Indica si se debe agregar el movimiento o no.
	 */
	public void agregarNuevoMovimiento(boolean pComando)
	{
		if(pComando)
		{
			int n = Integer.parseInt(txtMovimientos.getText());
			n+=1;
			txtMovimientos.setText(n+"");
		}
	}

	/**
	 * Cambia el valor de txtCasillas por aquel que llega por parámetro
	 * Pre: txtCasillas se encuentra inicializado.
	 * Post: Se ha cambiado el valor de txtCasillas.
	 * @param pEnBlanco Número de casillas en blanco en el tablero.
	 */
	public void actualizarCasillasEnBlanco(int pEnBlanco)
	{
		txtCasillas.setText(pEnBlanco+"");
	}
}