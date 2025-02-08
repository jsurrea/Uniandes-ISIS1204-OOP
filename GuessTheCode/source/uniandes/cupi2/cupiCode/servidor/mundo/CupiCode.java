package uniandes.cupi2.cupiCode.servidor.mundo;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Clase que recibe las conexiones de los clientes y establece los encuentros. <br>
 * @author JuanUrrea <br>
 * <b>inv:</b><br>
 * receptor != null <br>
 * propiedades != null <br>
 * admin != null <br>
 * encuentros != null <br>
 */
public class CupiCode 
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Configura el puerto y recibe la conexiÃ³n inicial.
	 */
	private ServerSocket receptor;

	/**
	 * Se encarga de leer el archivo servidor.properties para configurar la clase.
	 */
	private Properties propiedades;

	/**
	 * Administra la base de datos.
	 */
	private AdministradorResultados admin;

	/**
	 * Canal de comunicaciÃ³n con un jugador en espera.
	 */
	private Socket enEspera;

	/**
	 * ColecciÃ³n de encuentros.
	 */
	@SuppressWarnings("rawtypes")
	private Collection encuentros;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * MÃ©todo constructor de la clase.
	 * @param archivo de propiedades con la configuraciÃ³n del servidor.
	 * @throws Exception Si ocurre un error.
	 */
	@SuppressWarnings("rawtypes")
	public CupiCode(String archivo) throws Exception
	{
		encuentros = new Vector();
		FileInputStream fis = new FileInputStream(archivo);
		propiedades = new Properties();
		propiedades.load(fis);
		fis.close();
		admin = new AdministradorResultados(propiedades);
		admin.conectarBD();
		admin.crearTabla();
		verificarInvariante();
	}


	// -----------------------------------------------------------------
	// MÃ©todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el administrador de resultados de la clase.
	 * @return admin.
	 */
	public AdministradorResultados darAdministradorResultados()
	{
		return admin;
	}

	/**
	 * Recibe las conexiones y crea los encuentros.
	 */
	@SuppressWarnings("unchecked")
	public void recibirConexiones()
	{
		try
		{
			receptor = new ServerSocket(Integer.parseInt(propiedades.getProperty("servidor.puerto")));
			while(true)
			{
				Socket jugadorNuevo = receptor.accept();
				if(enEspera == null)
				{
					enEspera = jugadorNuevo;
				}
				else
				{
					Encuentro enc = new Encuentro(enEspera, jugadorNuevo, admin);
					enEspera = null;
					encuentros.add(enc);
					enc.start();
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				verificarInvariante();
				receptor.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza la lista de encuentros y la devuelve.
	 * Pre: encuentros != null.
	 * @return ColecciÃ³n de encuentros actualizada.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection darListaActualizadaEncuentros()
	{
		Collection lista = new Vector();
		Iterator iter = encuentros.iterator();
		while(iter.hasNext())
		{
			Encuentro enc = (Encuentro) iter.next();
			if(!enc.juegoTerminado())
			{
				lista.add(enc);
			}
		}
		encuentros = lista;
		return encuentros;
	}


	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase.
	 * <b>inv:</b><br>
	 * receptor != null <br>
	 * propiedades != null <br>
	 * admin != null <br>
	 * encuentros != null <br> 
	 */
	private void verificarInvariante()
	{
		assert receptor != null : "Problema con el canal.";
		assert propiedades != null : "Problema manejando las propiedades";
		assert admin != null : "No se inicializÃ³ correctamente el administrador.";
		assert encuentros != null : "No se inicializÃ³ correctamente la colecciÃ³n de encuentros.";
	}


	// -----------------------------------------------------------------
	// Puntos de ExtensiÃ³n
	// -----------------------------------------------------------------

	/**
	 * MÃ©todo para la extensiÃ³n 1.
	 */
	public String metodo1()
	{
		return "MÃ©todo 1";
	}

	/**
	 * MÃ©todo para la extensiÃ³n 2.
	 */
	public String metodo2()
	{
		return "MÃ©todo 2";
	}

}
