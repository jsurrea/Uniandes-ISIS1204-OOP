package uniandes.cupi2.cupiCode.servidor.mundo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa a un usuario.<br>
 * @author JuanUrrea<br>
 * <b>inv:</b><br>
 * nombre != null && !nombre.equals("") <br>
 * alias != null && !alias.equals("")<br>
 * numGanadas>=0<br>
 * numPerdidas>=0<br>
 * efectividad>=0 && efectividad<=100<br>
 */
public class Jugador 
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Nombre del jugador.
	 */
	private String nombre;

	/**
	 * Alias con el que se identifica el jugador.
	 */
	private String alias;

	/**
	 * NÃºmero de partidas ganadas.
	 */
	private int numGanadas;

	/**
	 * NÃºmero de partidas perdidas.
	 */
	private int numPerdidas;

	/**
	 * Efectividad del jugador: porcentaje de victorias.
	 */
	private double efectividad;

	/**
	 * CÃ³digo secreto del jugador.
	 */
	private String codigo;

	/**
	 * Pistas enviadas al jugador.
	 */
	@SuppressWarnings("rawtypes")
	private ArrayList pistas;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * MÃ©todo constructor de la clase.
	 * Post: Se inicializaron los atributos por los valores que llegan por parÃ¡metro. Se calculÃ³ la efectividad.
	 * @param pNombre Nombre del jugador.
	 * @param pAlias Alias del jugador.
	 * @param pGanadas NÃºmero de partidas ganadas.
	 * @param pPerdidas NÃºmero de partidas perdidas.
	 * @param pCodigo cÃ³digo secreto del jugador.
	 */
	@SuppressWarnings("rawtypes")
	public Jugador(String pNombre, String pAlias, int pGanadas, int pPerdidas)
	{
		nombre = pNombre;
		alias = pAlias;
		numGanadas = pGanadas;
		numPerdidas = pPerdidas;
		calcularEfectividad();
		pistas = new ArrayList();
	}


	// -----------------------------------------------------------------
	// MÃ©todos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre.
	 * @return nombre.
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * Retorna el alias.
	 * @return alias.
	 */
	public String darAlias()
	{
		return alias;
	}

	/**
	 * Retorna el nÃºmero de partidas ganadas.
	 * @return numGanadas.
	 */
	public int darNumeroGanadas()
	{
		return numGanadas;
	}

	/**
	 * Retorna el nÃºmero de partidas perdidas.
	 * @return numPerdidas.
	 */
	public int darNumeroPerdidas()
	{
		return numPerdidas;
	}

	/**
	 * Retorna la efectividad.
	 * @return efectividad.
	 */
	public double darEfectividad()
	{
		return efectividad;
	}

	/**
	 * Devuelve el cÃ³digo secreto del jugador.
	 * @return codigo.
	 */
	public String darCodigo()
	{
		return codigo;
	}

	/**
	 * Aumenta en 1 la cantidad de partidas ganadas.
	 */
	public void aumentarGanadas()
	{
		numGanadas++;
		calcularEfectividad();
	}

	/**
	 * Aumenta en 1 la cantidad de partidas perdidas.
	 */
	public void aumentarPerdidas()
	{
		numPerdidas++;
		calcularEfectividad();
	}

	/**
	 * Calcula la efectividad.
	 */
	private void calcularEfectividad()
	{
		efectividad = (numGanadas+numPerdidas!=0)?(double) 100*numGanadas/(numPerdidas+numGanadas):0;
		verificarInvariante();
	}

	/**
	 * Genera una pista dependiendo de los colores del jugador.
	 * <b>post: </b> 
	 * GeneroÌ un pista y la agregoÌ en el arreglo de pistas.<br> 
	 * @return Pista generada. Null en caso de haber pedido maÌs de dos pistas. 
	 */
	@SuppressWarnings("unchecked")
	public String generarPista() 
	{
		String pista = null; 
		if(pistas.size( )<2) 
		{
			boolean encontro = false;
			while (!encontro) 
			{
				Random random = new Random( );
				int num = random.nextInt( 6 );
				pista = (num+1)+"-"+codigo.replaceAll("-", "").charAt(num); 
				if(!pistas.contains( pista ))
				{
					pistas.add( pista );
					encontro = true; 
				}
			} 
		}
		return pista; 
	}

	/**
	 * Establece el cÃ³digo que llega por parÃ¡metro.
	 * @param pCodigo cÃ³digo a usar.
	 */
	public void establecerCodigo(String pCodigo)
	{
		codigo = pCodigo;
	}

	/**
	 * MÃ©todo toString().
	 */
	@Override
	public String toString()
	{
		return alias + ": " + numGanadas + " ganados / " +  numPerdidas + " perdidos (" + efectividad + "%).";
	}


	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase. <br>
	 * <b>inv:</b><br>
	 * nombre != null && !nombre.equals("") <br>
	 * alias != null && !alias.equals("")<br>
	 * numGanadas>=0<br>
	 * numPerdidas>=0<br>
	 * efectividad>=0 && efectividad<=100
	 */
	private void verificarInvariante()
	{
		assert(nombre != null && !nombre.equals("")) : "Problema con el nombre.";
		assert(alias != null && !alias.equals("")) : "Problema con el alias.";
		assert(numGanadas>=0) : "NÃºmero de partidas ganadas no puede ser menor que 0.";
		assert(numPerdidas>=0) : "NÃºmero de partidas perdidas no puede ser menor que 0.";
		assert(efectividad>=0 && efectividad<=100) : "Efectividad debe estar entre 0 y 100.";
	}

}
