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

package uniandes.cupi2.sudoku.mundo;

/**
 * Clase que representa una casilla del Sudoku.
 * @author JuanUrrea
 */
public class Casilla 
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Indica si la casilla es una casilla pista (casilla escogida al comenzar el juego para 
	 * que su número correcto sea visible a lo largo de todo el juego).
	 */
	private boolean pista;

	/**
	 * El número que debería ir en la casilla para que sea la solución del Sudoku.
	 */
	private int numeroCorrecto;

	/**
	 * El número que ingresó el usuario en la casilla.
	 */
	private int numeroIngresado;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Construye una casilla. 
	 * Post: Se creó una casilla que no es una casilla pista. 
	 * numeroIngresado se inicializó en 0 y numeroCorrecto en el valor que llega por parámetro.
	 * @param pNumeroCorrecto El número correspondiente a la solución del Sudoku.
	 */
	public Casilla(int pNumeroCorrecto)
	{
		pista = false;
		numeroCorrecto = pNumeroCorrecto;
		numeroIngresado = 0;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el número correcto de la casilla.
	 * @return Número correcto de la casilla.
	 */
	public int darNumeroCorrecto()
	{
		return numeroCorrecto;
	}

	/**
	 * Devuelve el número ingresado en la casilla.
	 * @return Número ingresado en la casilla.
	 */
	public int darNumeroIngresado()
	{
		return numeroIngresado;
	}

	/**
	 * Indica si la casilla es una casilla pista (casilla escogida al comenzar el juego para 
	 * que su número correcto sea visible a lo largo de todo el juego).
	 * @return true si la casilla es una casilla pista y false de lo contrario.
	 */
	public boolean esPista( )
	{
		return pista;
	}

	/**
	 * Cambia el valor de numeroIngresado al que llega por parámetro.
	 * @param pNumero El número ingresado por el usuario. pNúmero => 0
	 */
	public void ingresarNumero(int pNumero) 
	{
		numeroIngresado = pNumero;
	}

	/**
	 * Cambia la casilla a una casilla pista.
	 * Post: La casilla ahora es una casilla pista. 
	 */
	public void cambiarAPista( )
	{
		pista = true;
	}
}
