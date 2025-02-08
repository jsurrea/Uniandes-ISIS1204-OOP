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

import java.io.*;
import java.util.*;
import uniandes.cupi2.sudoku.interfaz.PanelMovimiento;

/**
 * Juego de Sudoku.
 * @author JuanUrrea
 */
public class Sudoku {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Cantidad de filas que hay en una zona.
	 */
	private int cantidadFilasZona;

	/**
	 * Cantidad de columnas que hay en una zona.
	 */
	private int cantidadColumnasZona;

	/**
	 * Fila actual que se encuentra seleccionada.
	 */
	private int filaActual;

	/**
	 * Columna actual que se encuentra seleccionada.
	 */
	private int columnaActual;

	/**
	 * Cantidad de pistas por zona.
	 */
	private int numeroPistas;

	/**
	 * Matriz de casillas del Sudoku.
	 */
	private Casilla casillas[][];


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------


	/**
	 * Crea un nuevo Sudoku en base al archivo que llega por parámetro.
	 * Post: Los atributos cantidadFilasZona, cantidadColumnasZona y cada elemento de casillas
	 * se inicializaron en base al archivo que llega por parámetro. filaActual y columnaActual
	 * se inicializaron en 0. numeroPistas se inicializó en 1 para un tablero 2x2, en 2 para
	 * tableros 2x3 o 3x2 y en 3 para un tablero 3x3. En cada zona del Sudoku se cambió a pista
	 * un número de casillas igual a numeroPistas.
	 * @param file El archivo que contiene las propiedades del Sudoku. f != null
	 * @throws Exception En caso de que ocurra un error al leer el archivo.
	 */
	public Sudoku(File file) throws Exception
	{
		FileInputStream fs = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fs);

		cantidadColumnasZona = Integer.parseInt(prop.getProperty("sudoku.N"));
		cantidadFilasZona = Integer.parseInt(prop.getProperty("sudoku.M"));
		filaActual = 0 ;
		columnaActual = 0;
		numeroPistas = (int) cantidadColumnasZona*cantidadFilasZona/3;

		int T = cantidadColumnasZona*cantidadFilasZona;
		casillas = new Casilla[T][T];
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				int fila = i+1;
				char doc = prop.getProperty("sudoku.fila"+fila).charAt(j);
				int pNumeroCorrecto = Integer.parseInt(""+ doc);
				casillas[i][j] = new Casilla(pNumeroCorrecto);
			}
		}

		fs.close();

		for(int x = 0; x < T; x+=cantidadFilasZona)
		{
			for(int y = 0; y < T; y+=cantidadColumnasZona)
			{
				int contador = 0;
				while(contador < numeroPistas)
				{
					int q = generarNumeroAleatorio(x, x+cantidadFilasZona);
					int r = generarNumeroAleatorio(y, y+cantidadColumnasZona);
					if(!casillas[q][r].esPista())
					{
						casillas[q][r].cambiarAPista();
						casillas[q][r].ingresarNumero(casillas[q][r].darNumeroCorrecto());
						contador++;
					}
				}
			}
		}

	}

	// ----------------------------------------------------------------
	// Métodos
	// ----------------------------------------------------------------

	/**
	 * Retorna la cantidad de filas que hay por zona.
	 * @return Cantidad de filas por zona.
	 */
	public int darCantidadFilasZona()
	{
		return cantidadFilasZona;
	}

	/**
	 * Retorna la cantidad de columnas que hay por zona.
	 * @return Cantidad de columnas por zona.
	 */
	public int darCantidadColumnasZona()
	{
		return cantidadColumnasZona;
	}

	/**
	 * Retorna la fila actualmente seleccionada.
	 * @return Fila actualmente seleccionada.
	 */
	public int darFilaActual()
	{
		return filaActual;
	}

	/**
	 * Retorna la columna actualmente seleccionada.
	 * @return Columna actualmente seleccionada.
	 */
	public int darColumnaActual()
	{
		return columnaActual;
	}

	/**
	 * Retorna las casillas del Sudoku.
	 * @return Matriz de casillas.
	 */
	public Casilla[][] darCasillas()
	{
		return casillas;
	}

	/**
	 * Retorna el número de casillas pista al cargar el Sudoku. Este se calcula como el número
	 * total de zonas multiplicado por el número de pistas en cada zona.
	 * @return Un número mayor a 0 y menor que el número de casillas.
	 */
	public int contarPistasIniciales() 
	{
		int T = cantidadColumnasZona*cantidadFilasZona;
		return numeroPistas*T;
	}

	/**
	 * Cuenta la cantidad de casillas sin número ingresado.
	 * @return Número de casillas en blanco.
	 */
	public int contarCasillasEnBlanco()
	{
		int contador = 0;
		int T = cantidadFilasZona*cantidadColumnasZona;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				if(!casillas[i][j].esPista() && casillas[i][j].darNumeroIngresado() == 0)
				{
					contador++;
				}
			}
		}
		return contador;
	}

	/**
	 * Reinicia a 0 los valores de filaActual y columnaActual. Ingresa el número 0 en todos los
	 * elementos de casillas que no sean pista.
	 * Pre: casillas se encuentra inicializado.
	 * Post:La fila actual y la columna actual se reiniciaron en 0. Los elementos de casillas
	 * se reiniciaron a como estaban al cargar el Sudoku.
	 */
	public void reiniciar()
	{
		filaActual = 0;
		columnaActual = 0;
		int T = cantidadFilasZona*cantidadColumnasZona;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				if(!casillas[i][j].esPista())
				{
					casillas[i][j].ingresarNumero(0);
				}
			}
		}
	}

	/**
	 * Revisa la matriz de casillas comprobando que los números ingresados sean consistentes con
	 * las reglas de juego. Revisa por fila, columna y zona.
	 * Pre: Casillas se encuentra inicializado.
	 * @return True en caso de que no haya errores y False en caso de que los haya.
	 */
	public boolean noHayErrores()
	{
		int T = cantidadFilasZona*cantidadColumnasZona;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				int a = casillas[i][j].darNumeroIngresado();
				for(int g = 0; g < T; g++)
				{
					for(int h = 0; h < T; h++)
					{
						int b = casillas[i][h].darNumeroIngresado();
						int c = casillas[g][j].darNumeroIngresado();
						if(a != 0 && b != 0 && j!=h && a == b)
						{
							return false;
						}
						if(a != 0 && c != 0 && i!=g && a == c)
						{
							return false;
						}
					}
				}				
				int d = i/cantidadFilasZona*cantidadFilasZona;
				int e = j/cantidadColumnasZona*cantidadColumnasZona;
				for(int m = d; m < d+cantidadFilasZona; m++)
				{
					for(int n = e; n < e+cantidadColumnasZona; n++)
					{
						int f = casillas[m][n].darNumeroIngresado();
						if(a != 0 && f != 0 && i!=m && j!=n && a == f)
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Revisa la matriz de casillas comprobando que en todos sus elementos se haya ingresado un 
	 * número.
	 * Pre: Casillas se encuentra inicializado
	 * @return True en caso de que todos los elementos de casillas tengan un número ingresado
	 * distinto a 0, y false en caso contrario.
	 */
	public boolean todosLlenos()
	{
		int T = cantidadFilasZona*cantidadColumnasZona;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				if(casillas[i][j].darNumeroIngresado() == 0)
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Ingresa el número que llega por parámetro a la casilla actual si este es distinto al
	 * que ya se encontraba. Devuelve si la operación fue exitosa.
	 * Pre: La casilla se encuentra inicializada.
	 * Post: La casilla ha quedado con pNumero como valor de numeroIngresado.
	 * @param pNumero Número que se ingresará a la casilla. pNumero => 0.
	 * @return True si el número ingresado es distinto al previo. False en caso contrario.
	 */
	public boolean ingresarNumero(int pNumero) 
	{
		if(casillas[filaActual][columnaActual].darNumeroIngresado()!=pNumero)
		{
			casillas[filaActual][columnaActual].ingresarNumero(pNumero);
			return true;
		}
		return false;
	}

	/**
	 * Cambia el valor de numeroIngresado a 0 en la casilla actual.
	 * Post: Ahora el valor de numeroIngresado en la casilla actual es 0.
	 * @return True si la casilla revisada contenía un número. False en caso contrario.
	 */
	public boolean borrarNumero()
	{
		if(casillas[filaActual][columnaActual].darNumeroIngresado() == 0)
		{
			return false;
		}
		casillas[filaActual][columnaActual].ingresarNumero(0);
		return true;
	}

	/**
	 * Cambia los valores de filaActual y columnaActual según la dirección que llega por 
	 * parámetro.
	 * Pre: La dirección que llega por parámetro es válida.
	 * Post: Si no lanza error, los valores de filaAtual y columnaActual cambiaron en un valor
	 * de uno o cero.
	 * @param direccion La dirección a la que debe moverse en la matriz.
	 * @throws Exception Si se intenta mover a una dirección que se sale del tablero.
	 */
	public void mover(String direccion) throws Exception
	{
		int T = cantidadColumnasZona*cantidadFilasZona;

		if(direccion.equals(PanelMovimiento.ABAJO) && filaActual<T-1)
		{
			filaActual += 1;
		}
		else if(direccion.equals(PanelMovimiento.ARRIBA) && filaActual>0)
		{
			filaActual -= 1;
		}
		else if(direccion.equals(PanelMovimiento.DERECHA) && columnaActual<T-1)
		{
			columnaActual += 1;
		}
		else if(direccion.equals(PanelMovimiento.IZQUIERDA) && columnaActual>0)
		{
			columnaActual -= 1;
		}
		else if(direccion.equals(PanelMovimiento.IZQARR) && columnaActual>0 && filaActual>0)
		{
			columnaActual -= 1;
			filaActual -= 1;
		}
		else if(direccion.equals(PanelMovimiento.DERARR) && columnaActual<T-1 && filaActual>0)
		{
			columnaActual += 1;
			filaActual -= 1;
		}
		else if(direccion.equals(PanelMovimiento.IZQAB) && filaActual<T-1 && columnaActual>0)
		{
			filaActual += 1;
			columnaActual -= 1;
		}
		else if(direccion.equals(PanelMovimiento.DERAB) && filaActual<T-1 && columnaActual<T-1)
		{
			filaActual += 1;
			columnaActual += 1;
		}
		else throw new Exception("No te puedes mover en esa dirección.");
	}

	/**
	 * Genera un número entero aleatorio entre pLimiteInferior y pLimiteSuperior - 1
	 * @param pLimiteInferior Límite inferior para el cual se va a generar el número aleatorio. 
	 * @param pLimiteSuperior Límite superior para el cual se va a generar el número aleatorio.
	 * @return Número entero entre pLimiteInferior y pLimiteSuperior - 1
	 */
	private int generarNumeroAleatorio( int pLimiteInferior, int pLimiteSuperior )
	{
		return new Random().nextInt(pLimiteSuperior-pLimiteInferior) + pLimiteInferior;
	}

	/**
	 * Método para la extensión 1.
	 * @return Respuesta 1.
	 */
	public String metodo1( )
	{
		return "Respuesta 1.";
	}

	/**
	 * Método para la extensión 2.
	 * @return Respuesta 2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2.";
	}
}