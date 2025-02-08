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
import uniandes.cupi2.sudoku.mundo.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa un panel de movimientos.
 * @author JuanUrrea
 */
public class PanelMovimiento extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante para el botón Ingresar.
	 */
	public final static String INGRESAR = "Ingresar";

	/**
	 * Constante para el botón Borrar.
	 */
	public final static String BORRAR = "Borrar";

	/**
	 * Constante para el botón Abajo.
	 */
	public final static String ABAJO = "Abajo";

	/**
	 * Constante para el botón Arriba.
	 */
	public final static String ARRIBA = "Arriba";

	/**
	 * Constante para el botón Derecha.
	 */
	public final static String DERECHA = "Derecha";

	/**
	 * Constante para el botón Izquierda.
	 */
	public final static String IZQUIERDA = "Izquierda";

	/**
	 * Constante para el botón IzqArr.
	 */
	public final static String IZQARR = "IzqArr";

	/**
	 * Constante para el botón DerArr.
	 */
	public final static String DERARR = "DerArr";

	/**
	 * Constante para el botón IzqAb.
	 */
	public final static String IZQAB = "IzqAb";

	/**
	 * Constante para el botón DerAb.
	 */
	public final static String DERAB = "DerAb";

	/**
	 * Ruta de la imagen Cupi2.
	 */
	public final static String PHCUPI2 = "data/imagenes/centro.png";

	/**
	 * Ruta de la imagen Abajo.
	 */
	public final static String PHABAJO = "data/imagenes/abajo.png";

	/**
	 * Ruta de la imagen Arriba.
	 */
	public final static String PHARRIBA = "data/imagenes/arriba.png";

	/**
	 * Ruta de la imagen Derecha.
	 */
	public final static String PHDERECHA = "data/imagenes/derecha.png";

	/**
	 * Ruta de la imagen Izquierda.
	 */
	public final static String PHIZQUIERDA = "data/imagenes/izquierda.png";

	/**
	 * Ruta de la imagen IzqArr.
	 */
	public final static String PHIZQARR = "data/imagenes/izqArr.png";

	/**
	 * Ruta de la imagen DerArr.
	 */
	public final static String PHDERARR = "data/imagenes/derArr.png";

	/**
	 * Ruta de la imagen IzqAb.
	 */
	public final static String PHIZQAB = "data/imagenes/izqAb.png";

	/**
	 * Ruta de la imagen DerAb.
	 */
	public final static String PHDERAB = "data/imagenes/derAb.png";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Botón para navegar hacia abajo.
	 */
	private JButton btAbajo;

	/**
	 * Botón para navegar hacia arriba.
	 */
	private JButton btArriba;

	/**
	 * Botón para navegar hacia abajo a la derecha.
	 */
	private JButton btDerAb;

	/**
	 * Botón para navegar hacia arriba a la derecha.
	 */
	private JButton btDerArr;

	/**
	 * Botón para navegar hacia la derecha.
	 */
	private JButton btDerecha;

	/**
	 * Botón para navegar hacia abajo a la izquierda.
	 */
	private JButton btIzqAb;

	/**
	 * Botón para navegar hacia arriba a la izquierda.
	 */
	private JButton btIzqArr;

	/**
	 * Botón para navegar hacia la izquierda.
	 */
	private JButton btIzquierda;

	/**
	 * Botón para ingresar un número en el Sudoku.
	 */
	private JButton btIngresar;

	/**
	 * Botón para borrar un número en el Sudoku.
	 */
	private JButton btBorrar;

	/**
	 * Etiqueta de la imagen que se ubica en el centro de los botones de movimiento.
	 */
	private JLabel lbCupi2;

	/**
	 * Etiqueta para la condición de pista.
	 */
	private JLabel lbPista;

	/**
	 * Etiqueta para el número de la fila actual.
	 */
	private JLabel lbFila;

	/**
	 * Etiqueta para el número de la columna actual.
	 */
	private JLabel lbColumna;

	/**
	 * CheckBox para la condición de pista.
	 */
	private JCheckBox cbPista;

	/**
	 * Caja de texto para el número de la fila actual.
	 */
	private JTextField txtFila;

	/**
	 * Etiqueta para el número de la columna actual.
	 */
	private JTextField txtColumna;

	/**
	 * Interfaz del programa.
	 */
	private InterfazSudoku ventana;


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea un nuevo panel de movimiento.
	 * Pre: Las rutas corresponden a fotos.
	 * Post: Se ha establecido el borde y la distribución del pánel. Se han inicializado los
	 * atributos de la ventana, los botones, las etiquetas, la CheckBox y las cajas de texto.
	 * Las cajas de texto no se pueden editar. Se ha añadidoa cada botón un ActionListener y 
	 * su Comando; además se han desactivado al igual que la foto y la CheckBox. Se han añadido
	 * las fotos necesarias y se han centrado. Se han añadido los elementos en dos páneles 
	 * internos distintos.
	 * @param i La interfaz con la que interactúa el panel.
	 */
	public PanelMovimiento(InterfazSudoku i)
	{
		setBorder(BorderFactory.createTitledBorder("Movimiento"));
		setLayout(new GridLayout(2,1));

		ventana = i;
		btAbajo = new JButton();
		btArriba = new JButton();
		btDerAb = new JButton();
		btDerArr = new JButton();
		btDerecha = new JButton();
		btIzqAb = new JButton();
		btIzqArr = new JButton();
		btIzquierda = new JButton();
		btIngresar = new JButton("Ingresar");
		btBorrar = new JButton("Borrar");
		lbCupi2 = new JLabel();
		lbPista = new JLabel("Pista:");
		lbFila = new JLabel("Fila:");
		lbColumna = new JLabel("Columna:");
		cbPista = new JCheckBox();
		txtFila = new JTextField();
		txtColumna = new JTextField();

		txtFila.setEditable(false);
		txtColumna.setEditable(false);

		btIngresar.addActionListener(this);
		btBorrar.addActionListener(this);
		btIzqArr.addActionListener(this);
		btArriba.addActionListener(this);
		btDerArr.addActionListener(this);
		btIzquierda.addActionListener(this);
		btDerecha.addActionListener(this);
		btIzqAb.addActionListener(this);
		btAbajo.addActionListener(this);
		btDerAb.addActionListener(this);
		btIngresar.setActionCommand(INGRESAR);
		btBorrar.setActionCommand(BORRAR);
		btIzqArr.setActionCommand(IZQARR);
		btArriba.setActionCommand(ARRIBA);
		btDerArr.setActionCommand(DERARR);
		btIzquierda.setActionCommand(IZQUIERDA);
		btDerecha.setActionCommand(DERECHA);
		btIzqAb.setActionCommand(IZQAB);
		btAbajo.setActionCommand(ABAJO);
		btDerAb.setActionCommand(DERAB);
		btIngresar.setEnabled(false);
		btBorrar.setEnabled(false);
		btIzqArr.setEnabled(false);
		btArriba.setEnabled(false);
		btDerArr.setEnabled(false);
		btIzquierda.setEnabled(false);
		btDerecha.setEnabled(false);
		btIzqAb.setEnabled(false);
		btAbajo.setEnabled(false);
		btDerAb.setEnabled(false);

		lbCupi2.setIcon(new ImageIcon(PHCUPI2));
		lbCupi2.setHorizontalAlignment(JLabel.CENTER);
		lbCupi2.setEnabled(false);

		cbPista.setSelected(false);
		cbPista.setEnabled(false);

		JPanel superior = new JPanel();
		superior.setLayout(new GridLayout(3, 3, 2, 2));
		btIzqArr.setIcon(new ImageIcon(PHIZQARR));
		btIzqArr.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btIzqArr);
		btArriba.setIcon(new ImageIcon(PHARRIBA));
		btArriba.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btArriba);
		btDerArr.setIcon(new ImageIcon(PHDERARR));
		btDerArr.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btDerArr);
		btIzquierda.setIcon(new ImageIcon(PHIZQUIERDA));
		btIzquierda.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btIzquierda);
		superior.add(lbCupi2);
		btDerecha.setIcon(new ImageIcon(PHDERECHA));
		btDerecha.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btDerecha);
		btIzqAb.setIcon(new ImageIcon(PHIZQAB));
		btIzqAb.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btIzqAb);
		btAbajo.setIcon(new ImageIcon(PHABAJO));
		btAbajo.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btAbajo);
		btDerAb.setIcon(new ImageIcon(PHDERAB));
		btDerAb.setHorizontalAlignment(JLabel.CENTER);
		superior.add(btDerAb);
		add(superior);

		JPanel inferior = new JPanel();
		inferior.setLayout(new GridLayout(4, 2, 5, 5));
		inferior.add(lbPista);
		inferior.add(cbPista);
		inferior.add(lbFila);
		inferior.add(txtFila);
		inferior.add(lbColumna);
		inferior.add(txtColumna);
		inferior.add(btIngresar);
		inferior.add(btBorrar);
		add(inferior);
	}


	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Activa los botones, la foto y establece el texto de las cajas de texto en 1.
	 * Pre: Los botones, la foto y las cajas de texto se encuentran inicializados.
	 * Post: Los botones y la foto han quedado activados; las cajas de texto muestran 1.
	 */
	public void activarMovimiento()
	{
		btIngresar.setEnabled(true);
		btBorrar.setEnabled(true);
		btIzqArr.setEnabled(true);
		btArriba.setEnabled(true);
		btDerArr.setEnabled(true);
		btIzquierda.setEnabled(true);
		btDerecha.setEnabled(true);
		btIzqAb.setEnabled(true);
		btAbajo.setEnabled(true);
		btDerAb.setEnabled(true);
		lbCupi2.setEnabled(true);
		txtFila.setText(1+"");
		txtColumna.setText(1+"");
	}

	/**
	 * Desactiva los botones, la foto y vacía el texto de las cajas de texto.
	 * Pre: Los botones, la foto y las cajas de texto se encuentran inicializados.
	 * Post: Los botones y la foto han quedado desactivados; las cajas de texto están vacías. 	
	 */
	public void desactivarMovimiento()
	{
		btIngresar.setEnabled(false);
		btBorrar.setEnabled(false);
		btIzqArr.setEnabled(false);
		btArriba.setEnabled(false);
		btDerArr.setEnabled(false);
		btIzquierda.setEnabled(false);
		btDerecha.setEnabled(false);
		btIzqAb.setEnabled(false);
		btAbajo.setEnabled(false);
		btDerAb.setEnabled(false);
		lbCupi2.setEnabled(false);
		txtFila.setText("");
		txtColumna.setText("");
	}

	/**
	 * Actualiza el panel según la nueva localización de la casilla seleccionada.
	 * Pre: Las casillas, las cajas de textos, los botones y la CheckBox se encuentran 
	 * inicializados.
	 * Post: Las cajas de texto contienen los valores que llegan por parámetro más uno. Si la
	 * casilla seleccionada es pista, se desactivan los botones y se selecciona la CheckBox; en
	 * caso contrario, se activan los botones y se deselecciona la CheckBox.
	 * @param nFila Nuevo número de fila.
	 * @param nColumna Nuevo número de columna.
	 * @param pCasillas Matriz de casillas del Sudoku.
	 */
	public void actualizarMovimiento(int nFila, int nColumna, Casilla[][] pCasillas)
	{
		txtFila.setText(nFila+1+"");
		txtColumna.setText(nColumna+1+"");
		if(pCasillas[nFila][nColumna].esPista())
		{
			btIngresar.setEnabled(false);
			btBorrar.setEnabled(false);
			cbPista.setSelected(true);
		}
		else 
		{
			btIngresar.setEnabled(true);
			btBorrar.setEnabled(true);
			cbPista.setSelected(false);
		}
	}

	/**
	 * Manejo de eventos del usuario.
	 * Pre: Solo los botones generan eventos.
	 * @param e Evento del usuario. e != null.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(INGRESAR))
		{
			ventana.ingresarNumero();
		}
		else if(comando.equals(BORRAR))
		{
			ventana.borrar();
		}
		else
		{
			ventana.mover(comando);
		}
	}
}