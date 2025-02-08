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
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import uniandes.cupi2.sudoku.mundo.*;

/**
 * Clase que representa la ventana principal del juego de Sudoku.
 * @author JuanUrrea
 */
public class InterfazSudoku extends JFrame 
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
	 * Clase principal del mundo.
	 */
	private Sudoku mundo;

	/**
	 * Panel superior que contiene la foto banner.
	 */
	private PanelBanner panelBanner;

	/**
	 * Panel de la izquierda que contiene la información del Sudoku.
	 */
	private PanelInformacion panelInformacion;

	/**
	 * Panel inferior que contiene los botones de opción.
	 */
	private PanelOpciones panelOpciones;

	/**
	 * Panel de la derecha que se encarga del movimiento.
	 */
	private PanelMovimiento panelMovimiento;

	/**
	 * Panel central que contiene al tablero.
	 */
	private PanelTablero panelTablero;


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea una nueva interfaz para el sudoku.
	 * Se estableció el título, el tamaño, la distribución y las configuraciones básicas de la
	 * ventana. Se inicializaron los distintos páneles y se añadieron en zonas diferentes.
	 */
	public InterfazSudoku()
	{
		setTitle("CupiSudoku");
		setSize(1000, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		panelBanner = new PanelBanner();
		add(panelBanner, BorderLayout.NORTH);

		panelInformacion = new PanelInformacion();
		add(panelInformacion, BorderLayout.WEST);

		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);

		panelTablero = new PanelTablero();
		add(panelTablero, BorderLayout.CENTER);

		panelMovimiento = new PanelMovimiento(this);
		add(panelMovimiento, BorderLayout.EAST);
	}

	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Carga un nuevo tablero.
	 * Pre: Los páneles y el mundo se encuentran inicializados.
	 * Post: Se ha cargado un nuevo juego según el archivo de propiedades escogido por el 
	 * usuario. Si el formato no es correcto, manda un mensaje de error.
	 */
	public void cargar()
	{
		JFileChooser fc = new JFileChooser("data");
		int selected = fc.showOpenDialog(this);
		if(selected == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			try
			{
				mundo = new Sudoku(file);

				TitledBorder borde = new TitledBorder( "Tablero" ); 
				int width = panelTablero.getWidth();
				int height = panelTablero.getHeight();
				int max = Math.max(width, height);
				max = mundo.darCasillas().length*45;
				max= Math.min(max, width);
				max=Math.min(max, height);
				int hMargin = Math.abs((max - height)/2);
				int wMargin = Math.abs((max-width)/2); 

				panelTablero.setBorder(new CompoundBorder(borde, BorderFactory.
						createEmptyBorder(hMargin, wMargin, hMargin, wMargin)));
				panelTablero.actualizar(mundo.darCantidadFilasZona(), mundo.
						darCantidadColumnasZona(), mundo.darCasillas());
				panelTablero.moverse(mundo.darFilaActual(), mundo.darColumnaActual());

				panelInformacion.cargarInformacion("Sudoku "+mundo.darCantidadColumnasZona()+
						"x"+mundo.darCantidadFilasZona(), mundo.contarPistasIniciales(), 
						mundo.contarCasillasEnBlanco());

				panelMovimiento.activarMovimiento();
				panelOpciones.activarBotones();
				panelMovimiento.actualizarMovimiento(0,0, mundo.darCasillas());
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this, "Error al cargar el archivo: formato no "
						+ "válido.", "Cargar tablero", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Reinicia el tablero a como estaba cuando se cargó.
	 * Pre: Ya se ha cargado el tablero.
	 * Post: Los páneles quedaron como cuando se cargó el juego.
	 */
	public void reiniciar()
	{
		mundo.reiniciar();
		panelMovimiento.activarMovimiento();
		panelOpciones.activarBotones();
		panelTablero.reiniciar(mundo.darCasillas());
		panelTablero.moverse(0, 0);
		panelMovimiento.actualizarMovimiento(0,0, mundo.darCasillas());
		panelInformacion.reiniciarInformacion(mundo.contarCasillasEnBlanco());
	}

	/**
	 * Valida el tablero.
	 * Pre: Ya se ha cargado el tablero.
	 * Post: Las casillas incorrectas han quedado pintadas de rojo. El resto sigue igual.
	 */
	public void validar()
	{
		if(mundo.noHayErrores())
		{
			if(mundo.todosLlenos())
			{
				panelTablero.mostrarSolucion(mundo.darCasillas());
				JOptionPane.showMessageDialog(this, "Has completado correctamente el Sudoku.",
						"Validar", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "No hay errores en el Sudoku.",
						"Validar", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			panelTablero.validar(mundo.darCantidadFilasZona(), mundo.darCantidadColumnasZona(),
					mundo.darCasillas());	
		}
	}

	/**
	 * Soluciona el tablero.
	 * Pre: Ya se ha cargado el tablero.
	 * Post: Todas las casillas han quedado en verde y muestran el número solución. 
	 */
	public void solucionar()
	{
		panelMovimiento.desactivarMovimiento();
		panelOpciones.desactivarBotones();
		panelTablero.mostrarSolucion(mundo.darCasillas());
		panelInformacion.actualizarSolucion();
		JOptionPane.showMessageDialog(this, "El Sudoku ha sido solucionado correctamente.", 
				"Solucionado", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método para la extensión 1.
	 */
	public void reqFuncOpcion1()
	{
		JOptionPane.showMessageDialog(this, "Respuesta 1.", "Respuesta", JOptionPane.
				INFORMATION_MESSAGE);
	}

	/**
	 * Método para la extensión 2.
	 */
	public void reqFuncOpcion2()
	{
		JOptionPane.showMessageDialog(this, "Respuesta 2.", "Respuesta", JOptionPane.
				INFORMATION_MESSAGE);
	}

	/**
	 * Ingresa un número al tablero. Si el usuario no ingresa nada, ingresa algo distinto a un
	 * número o ingresa un número fuera de los límites permitidos, muestra un mensaje de error.
	 * Pre: Ya se ha cargado el tablero.
	 * Post: Si el número ingresado por el usuario es válido, este se agrega a la casilla 
	 * actual. Los páneles se actualizan.
	 */
	public void ingresarNumero()
	{
		int fila = mundo.darFilaActual()+1;
		int columna = mundo.darColumnaActual()+1;
		String entrada = JOptionPane.showInputDialog("Ingresar número en la casilla " + fila + 
				"," + columna);
		if(entrada == null||entrada.equals(""))
		{
			JOptionPane.showMessageDialog(this, "Ingrese un valor.", "Error", JOptionPane.
					ERROR_MESSAGE);
		}
		else try
		{
			int T = mundo.darCantidadColumnasZona()*mundo.darCantidadFilasZona();
			int numero = Integer.parseInt(entrada);
			if(numero<1 || numero>T)
			{
				JOptionPane.showMessageDialog(this, "El número ingresado es inválido. Debe ser"
						+ " un valor entre 1 y "+T+".", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				panelInformacion.agregarNuevoMovimiento(mundo.ingresarNumero(numero));
				panelInformacion.actualizarCasillasEnBlanco(mundo.contarCasillasEnBlanco());
				panelTablero.ingresarNumero(mundo.darFilaActual(), mundo.darColumnaActual(),
						numero);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Debe ingresar un número.", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Si hay un número en la casilla actual, lo elimina y agrega un movimiento realizado.
	 * Pre: Ya se ha cargado el tablero.
	 * Post: Los páneles se actualizan si la operación fue exitosa.
	 */
	public void borrar()
	{
		panelInformacion.agregarNuevoMovimiento(mundo.borrarNumero());
		panelInformacion.actualizarCasillasEnBlanco(mundo.contarCasillasEnBlanco());
		panelTablero.borrarNumero(mundo.darFilaActual(),mundo.darColumnaActual());
	}

	/**
	 * Intenta cambiar la fila actual y la columna actual según la dirección que llega por
	 * parámetro. Si no se puede mover en esa dirección, muestra un mensaje de error.
	 * Pre: La dirección es válida.
	 * Post: El mundo ha cambiado su fila y columna actuales. Los páneles se han actualizado
	 * basados en este cambio.
	 * @param direccion La dirección a la cual se debe mover.
	 */
	public void mover(String direccion)
	{
		try 
		{
			mundo.mover(direccion);
			int pFila = mundo.darFilaActual();
			int pColumna = mundo.darColumnaActual();
			panelTablero.moverse(pFila, pColumna);
			panelMovimiento.actualizarMovimiento(pFila, pColumna, mundo.darCasillas());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.
					ERROR_MESSAGE);
		}
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------
	/**
	 * Ejecuta el programa creando una nueva interfaz.
	 * Post: Se ha cargado la ventana.
	 * @param args.
	 */
	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazSudoku ventana = new InterfazSudoku();
			ventana.setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}