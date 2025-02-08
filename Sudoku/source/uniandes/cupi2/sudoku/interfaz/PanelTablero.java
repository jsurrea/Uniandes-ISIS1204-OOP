/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.sudoku.interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import uniandes.cupi2.sudoku.mundo.*;

/**
 * Panel con las casillas del sudoku organizadas en un tablero cuadrado.
 * @author JuanUrrea
 */
public class PanelTablero extends JPanel 
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Default Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Representa el color que toman las casillas cuando el Sudoku es resuelto.
	 */
	private final static Color COLOR_EXITO = new Color( 87, 150, 38 );

	/**
	 * Representa el color que toman las casillas cuando el Sudoku tiene errores.
	 */
	private final static Color COLOR_ERROR = new Color( 200, 1, 1 );

	/**
	 * Representa el color que toman las casillas cuando están vacías.
	 */
	private final static Color COLOR_VACIO = new Color( 229, 132, 15 );

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Matriz con las zonas del tablero.
	 */
	private JPanel[][] zonas;

	/**
	 * Matriz que contiene los campos de texto que conforman el tablero del sudoku.
	 */
	private JTextField[][] tablero;


	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	/**
	 * Constructor de la clase.
	 * Post: Se ha creado el borde del panel.
	 */
	public PanelTablero() 
	{
		setBorder(BorderFactory.createTitledBorder("Tablero"));
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Actualiza el tablero con la información de las casillas dadas por parámetro. 
	 * Este método quita todos los elementos del panel y los vuelve a agregar según corresponda de acuerdo a la información dada por parámetro.
	 * @param pFilasZona Cantidad de filas que tiene una zona. pFilasZona => 2.
	 * @param pColumnasZona Cantidad de columnas que tiene una zona. pColumnasZona => 2.
	 * @param pCasillas Casillas del sudoku. pCasillas != null.
	 */
	public void actualizar(int pFilasZona, int pColumnasZona, Casilla[][] pCasillas)
	{
		removeAll();
		setLayout(new GridLayout(pColumnasZona, pFilasZona));
		int T = pCasillas[0].length;
		zonas = new JPanel[pColumnasZona][pFilasZona];
		tablero = new JTextField[T][T];		
		for (int i = 0; i < pColumnasZona; i++) 
		{
			for (int j = 0; j < pFilasZona; j++) 
			{
				zonas[i][j] = new JPanel();
				crearZona(zonas[i][j], i,j, pFilasZona, pColumnasZona, pCasillas);
				add(zonas[i][j], BorderLayout.CENTER);
			}
		}	
		revalidate();
		repaint();
	}

	/**
	 * Crea los campos de textos correspondientes a la zona que inicia en pFila y pColumna. 
	 * Los campos de texto son agregados al panel pPanelZona en la ubicación que le corresponde. 
	 * @param pPanelZona panel donde se deben ubicar los campos de texto de la zona. pPanelZona != null.
	 * @param pFila Fila donde inicia la zona (fila de la casilla superior izquierda de la zona). pFila => 0.
	 * @param pColumna Columna donde inicia la zona (columna de la casilla superior izquierda de la zona). pColumna => 0.
	 * @param pFilasZona Cantidad de filas que hay en una zona. pFilasZona => 2.
	 * @param pColumnasZona Cantidad de columnas que hay en una zona. pColumnasZona => 2.
	 * @param pCasillas Casillas del sudoku. pCasilla != null.
	 */
	public void crearZona(JPanel pPanelZona, int pFila, int pColumna, int pFilasZona, int pColumnasZona, Casilla[][] pCasillas)
	{
		pPanelZona.setLayout(new GridLayout(pFilasZona, pColumnasZona));
		for(int i=0; i< pFilasZona; i++){
			for(int j=0; j<pColumnasZona; j++){
				JTextField txt = new JTextField();
				txt.setEditable(false);
				txt.setHorizontalAlignment(JLabel.CENTER);
				int fila = pFila*pFilasZona + i + 1;
				int columna = pColumna*pColumnasZona + j +1;
				Casilla casillaActual = pCasillas[fila-1][columna-1];
				tablero[fila-1][columna-1] = txt;
				if(casillaActual.esPista())
				{
					tablero[fila-1][columna-1].setText(pCasillas[fila-1][columna-1].darNumeroCorrecto()+"");
					tablero[fila-1][columna-1].setBackground(Color.WHITE);
					Font tipoLetra = txt.getFont( );
					Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, tipoLetra.getSize( ) + 3 );
					txt.setHorizontalAlignment( JTextField.CENTER );  
					txt.setFont( nuevoTipoLetra );
				}
				else
				{
					tablero[fila-1][columna-1].setBackground(COLOR_VACIO);
				}
				pPanelZona.add(txt);			
			}
		}
		pPanelZona.setBorder(new BevelBorder( BevelBorder.RAISED));
	}


	/**
	 * Mueve el indicador de la casilla actual 
	 * @param pFila la fila de la casilla actual. pFila => 0.
	 * @param pColumna la columna de la casilla actual. pColumna => 0.
	 * Post: La casilla actual ha quedado con un borde azul, el resto con un borde gris oscuro.
	 */
	public void moverse(int pFila, int pColumna) {

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				tablero[i][j].setBorder(new LineBorder(Color.DARK_GRAY));
			}
		}

		tablero[pFila][pColumna].setBorder(new LineBorder(Color.BLUE,2));
	}

	/**
	 * Se aplica el estilo del tablero solucionado.
	 * Post: Todas las casillas han quedado de color verde y con el número de la solución.
	 * @param pCasillas Matriz de casillas del mundo. pCasillas != null.
	 */
	public void mostrarSolucion(Casilla[][] pCasillas)
	{
		for( int i = 0; i < tablero.length; i++ )
		{
			for( int j = 0; j < tablero[i].length; j++ )
			{
				tablero[ i ][ j ].setText(pCasillas[i][j].darNumeroCorrecto()+"");
				tablero[ i ][ j ].setBackground( COLOR_EXITO );
			}
		}
	}

	/**
	 * Cambia el texto de la casilla seleccionada a aquel número que llega por parámetro.
	 * Post: La caja de texto correspondiente ahora muestra el número ingresado por el usuario
	 * y es de color blanco.
	 * @param filaActual La coordenada en las filas de la casilla actual. filaActual => 0.
	 * @param columnaActual La coordenada en las columnas de la casilla actual. columnaActual => 0.
	 * @param pNumero El número a ingresar en la casilla. Está entre 1 y T.
	 */
	public void ingresarNumero(int filaActual, int columnaActual, int pNumero) 
	{
		tablero[filaActual][columnaActual].setText(pNumero+"");
		tablero[filaActual][columnaActual].setBackground(Color.WHITE);
	}

	/**
	 * Elimina el contenido de la caja de texto de la casilla actual.
	 * Post: La caja de texto está vacía si antes había un número ingresado por el usuario.
	 * @param filaActual La coordenada en las filas de la casilla actual. filaActual => 0.
	 * @param columnaActual La coordenada en las columnas de la casilla actual. columnaActual => 0.
	 */
	public void borrarNumero(int filaActual, int columnaActual) 
	{
		tablero[filaActual][columnaActual].setText("");
		tablero[filaActual][columnaActual].setBackground(COLOR_VACIO);
	}

	/**
	 * Reinicia el tablero al estado inicial cuando se cargó.
	 * @param pCasillas Matriz de casillas.
	 * Pre: El tablero ya había sido cargado.
	 * Post: Las casillas pista muestran su número y se pintan de color blanco. El resto de
	 * casillas se pinta del color predeterminado y se les quita el texto.
	 */
	public void reiniciar(Casilla[][] pCasillas) 
	{
		int T = tablero[0].length;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				if(pCasillas[i][j].esPista())
				{
					tablero[i][j].setBackground(Color.WHITE);
				}
				else
				{
					tablero[i][j].setText("");
					tablero[i][j].setBackground(COLOR_VACIO);
				}
			}
		}
	}

	/**
	 * Revisa si los números introducidos por el usuario hasta el momento incumplen alguna de
	 * las reglas. Este método revisa que en cada casilla en la que exista un número, este no
	 * se repita en la fila, columna y zona correspondiente.
	 * @param filasZona Cantidad de filas que hay en una zona. pFilasZona => 2.
	 * @param columnasZona Cantidad de columnas que hay en una zona. pColumnasZona => 2.
	 * @param pCasillas Matriz de casillas.
	 * Pre: El tablero ya ha sido cargado.
	 * Post: Las cajas de texto que no cumplan las reglas de juego han quedado pintadas de rojo,
	 * siempre y cuando estas no corresponan a casillas pista. 
	 */
	public void validar(int filasZona, int columnasZona, Casilla[][] pCasillas)
	{
		int T = tablero[0].length;
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < T; j++)
			{
				String a = tablero[i][j].getText();
				for(int g = 0; g < T; g++)
				{
					for(int h = 0; h < T; h++)
					{
						String b = tablero[i][h].getText();
						String c = tablero[g][j].getText();
						if(!a.equals("") && a!=null && !b.equals("") && b!=null && j!=h && a.equals(b))
						{
							if(!pCasillas[i][j].esPista())
							{
								tablero[i][j].setBackground(COLOR_ERROR);
							}
							if(!pCasillas[i][h].esPista())
							{
								tablero[i][h].setBackground(COLOR_ERROR);
							}
						}
						if(!a.equals("") && a!=null && !c.equals("") && c!=null && i!=g && a.equals(c))
						{
							if(!pCasillas[i][j].esPista())
							{
								tablero[i][j].setBackground(COLOR_ERROR);
							}
							if(!pCasillas[g][j].esPista())
							{
								tablero[g][j].setBackground(COLOR_ERROR);
							}
						}
					}
				}				
				int d = i/filasZona*filasZona;
				int e = j/columnasZona*columnasZona;
				for(int m = d; m < d+filasZona; m++)
				{
					for(int n = e; n < e+columnasZona; n++)
					{
						String f = tablero[m][n].getText();
						if(!a.equals("") && a!=null && !f.equals("") && f!=null && i!=m && j!=n && a.equals(f))
						{
							if(!pCasillas[i][j].esPista())
							{
								tablero[i][j].setBackground(COLOR_ERROR);
							}	
							if(!pCasillas[m][n].esPista())
							{
								tablero[m][n].setBackground(COLOR_ERROR);
							}
						}
					}
				}
			}
		}
	}
}
