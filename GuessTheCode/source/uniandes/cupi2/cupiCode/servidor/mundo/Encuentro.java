package uniandes.cupi2.cupiCode.servidor.mundo;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * Clase que administra los encuentros, maneja el protocolo y mantiene en comunicación a los clientes<br>
 * Cada encuentro se maneja como un Hilo diferente.<br> 
 * @author JuanUrrea<br>
 * <b>inv:</b><br>
 * !juegoTerminado => jugador1 != null <br>
 * !juegoTerminado => out1 != null <br>
 * !juegoTerminado => in1 != null <br>
 * !juegoTerminado => jugador2 != null <br>
 * !juegoTerminado => out2 != null <br>
 * !juegoTerminado => in2 != null <br>
 * jugador1 != null <br>
 * jugador2 != null <br>
 * admin != null <br>
 */
public class Encuentro extends Thread
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Flujo de lectura del jugador 1.
	 */
	private BufferedReader in1;

	/**
	 * Flujo de lectura del jugador 2.
	 */
	private BufferedReader in2;

	/**
	 * Flujo de escritura del jugador 1.
	 */
	private PrintWriter out1;

	/**
	 * Flujo de escritura del jugador 2.
	 */
	private PrintWriter out2;

	/**
	 * Objeto con la información del jugador 1.
	 */
	private Jugador jugador1;

	/**
	 * Objeto con la información del jugador 2.
	 */
	private Jugador jugador2;

	/**
	 * Canal jugador 1.
	 */
	private Socket socket1;

	/**
	 * Canal jugador 2.
	 */
	private Socket socket2;

	/**
	 * Administrador de la base de datos.
	 */
	private AdministradorResultados admin;

	/**
	 * Indica si el juego ha terminado. True: Sí; False: No.
	 */
	private boolean juegoTerminado;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Establece la comunicación entre los dos jugadores.
	 * @param canal1 Canal de comunicación del jugador1.
	 * @param canal2 Canal de comunicación del jugador2.
	 * @param pAdmin Administrador de la base de datos.
	 * @throws IOException Si ocurre un error al leer/escribir.
	 */
	public Encuentro(Socket canal1, Socket canal2, AdministradorResultados pAdmin) throws IOException
	{
		admin = pAdmin;
		juegoTerminado = false;
		socket1 = canal1;
		socket2 = canal2;
		in1 = new BufferedReader(new InputStreamReader(canal1.getInputStream()));
		in2 = new BufferedReader(new InputStreamReader(canal2.getInputStream()));
		out1 = new PrintWriter(canal1.getOutputStream(), true);
		out2 = new PrintWriter(canal2.getOutputStream(), true);
		verificarInvariante();
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el canal del jugador 1.
	 * @return socket1.
	 */
	public Socket darSocketJugador1()
	{
		return socket1;
	}

	/**
	 * Devuelve el canal del jugador 2.
	 * @return socket2.
	 */
	public Socket darSocketJugador2()
	{
		return socket2;
	}

	/**
	 * Devuelve el administrador de la base de datos.
	 * @return admin.
	 */
	public AdministradorResultados darAdministrador()
	{
		return admin;
	}

	/**
	 * Devuelve el estado del juego.
	 * @return True, si ya se acabó; False en caso contrario.
	 */
	public boolean juegoTerminado()
	{
		return juegoTerminado;
	}

	/**
	 * Método toString() de la clase.
	 */
	@Override
	public String toString()
	{
		return jugador1.darAlias() + " - " + jugador2.darAlias();
	}

	/**
	 * Método start() del hilo.
	 */
	public void run()
	{
		try
		{
			boolean pista = false;
			boolean siguiente = true;
			iniciarEncuentro();
			int turnoActual = asignarTurno();
			while(!juegoTerminado)
			{
				try
				{
					pista = procesarJugada(turnoActual);
					if(siguiente)
					{
						turnoActual = (turnoActual==1)?2:1;
					}
					if(pista)
					{
						siguiente = false;
					}
					else
					{
						siguiente = true;
					}
				}
				catch(Exception e)
				{
					if(!e.getMessage().equals("Jugador ya usó todas sus pistas."))
					{
						throw e;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			juegoTerminado = true;
			try
			{
				in1.close();
				in2.close();
				out1.close();
				out2.close();
				socket1.close();
				socket2.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
		}
	}

	/**
	 * Busca el usuario según el mensaje que llega. Si no existe, lo crea.
	 * @param mensaje recibido en el protocolo.
	 * @return Jugador con los datos que llegan por mensaje.
	 * @throws Exception Si ocurre un error.
	 */
	private Jugador obtenerInfo(String mensaje) throws Exception
	{
		if(mensaje.startsWith("JUGADOR"))
		{
			String[] split = mensaje.split(":");
			String alias = split[1];
			String nombre = split[2];
			String codigo = split [3];
			Jugador buscado = admin.consultarUsuario(alias);
			if(buscado!=null)
			{
				if(!buscado.darNombre().equals(nombre))
				{
					throw new Exception("El alias no corresponde al nombre. Alias: " + alias);
				}
				else
				{
					buscado.establecerCodigo(codigo);
					return buscado;
				}
			}
			else
			{
				admin.registrarUsuario(alias, nombre);
				buscado = new Jugador(nombre, alias, 0, 0);
				buscado.establecerCodigo(codigo);
				return buscado;
			}
		}
		else
		{
			throw new Exception("El mensaje no sigue el protocolo esperado.");
		}
	}

	/**
	 * Inicia el juego.
	 * @throws Exception Si ocurre un error.
	 */
	private void iniciarEncuentro() throws Exception
	{
		String mensajeOut1 = "";
		String mensajeOut2 = "";
		try
		{
			String msjJug1 = in1.readLine();
			jugador1 = obtenerInfo(msjJug1);
			String msjJug2 = in2.readLine();
			jugador2 = obtenerInfo(msjJug2);
			if(jugador1.darAlias().equals(jugador2.darAlias()))
			{
				throw new Exception("Los dos jugadores ingresaron el mismo alias.");
			}
			mensajeOut1 = "INFO:" + jugador2.darAlias() + "-" + jugador2.darNumeroGanadas() + "-" + jugador2.darNumeroPerdidas();
			mensajeOut2 = "INFO:" + jugador1.darAlias() + "-" + jugador1.darNumeroGanadas() + "-" + jugador1.darNumeroPerdidas();
		}
		catch(Exception e)
		{
			String error = e.getMessage();
			if(error.startsWith("El alias no corresponde al nombre. Alias: "))
			{
				e.printStackTrace();
				String alias = e.getMessage().replace("El alias no corresponde al nombre. Alias: ", "");
				if(alias.equals(jugador1.darAlias()))
				{
					mensajeOut1 = "ERROR:El nombre que ingresó no corresponde al usuario con ese alias.";
					mensajeOut2 = "ERROR:Su oponente ingresó los datos incorrectamente, reinicie el juego.";
					throw e;
				}
				else 
				{
					mensajeOut2 = "ERROR:El nombre que ingresó no corresponde al usuario con ese alias.";
					mensajeOut1 = "ERROR:Su oponente ingresó los datos incorrectamente, reinicie el juego.";
					throw e;
				}
			}
			else if(error.equals("Los dos jugadores ingresaron el mismo alias."))
			{
				e.printStackTrace();
				mensajeOut1 = "ERROR:Su oponente se identificó con el mismo usuario, reinicie el juego.";
				mensajeOut2 = mensajeOut1;
				throw e;
			}
			else
			{
				throw e;
			}
		}
		finally
		{
			out1.println(mensajeOut1);
			out2.println(mensajeOut2);
		}
	}

	/**
	 * Asigna el turno. Y=0, B=1, R=2, G=3.
	 * @return el número del jugador que empieza.
	 */
	private int asignarTurno() throws IOException
	{
		int rta = -1;
		boolean asignado = false;
		while(!asignado)
		{
			Random random = new Random();
			int num = random.nextInt(4);
			String cod = "";
			if(num==0)
			{
				cod = "Y";
			}
			else if(num==1)
			{
				cod = "B";
			}
			else if(num==2)
			{
				cod = "R";
			}
			else if(num==3)
			{
				cod = "G";
			}	
			out1.println("ESCOGER_COLOR");
			String rta1 = in1.readLine().replace("COLOR:", "");
			if(rta1.equals(cod))
			{
				out1.println("TURNO:1");
				out2.println("TURNO:2");
				asignado = true;
				rta = 1;
			}
			else
			{
				out2.println("ESCOGER_COLOR");
				String rta2 = in2.readLine().replace("COLOR:", "");
				if(rta2.equals(cod))
				{
					out2.println("TURNO:1");
					out1.println("TURNO:2");
					asignado = true;
					rta = 2;
				}
			}
		}
		return rta;
	}

	/**
	 * Procesa las jugadas.
	 * @param numJugAct número del jugador actual.
	 * @throws Exception Si ocurre un error.
	 */
	@SuppressWarnings("resource")
	private boolean procesarJugada(int numJugAct) throws Exception
	{
		PrintWriter jugandoOut = ( numJugAct == 1 ) ? out1 : out2;
		PrintWriter enEsperaOut = ( numJugAct == 1 ) ? out2 : out1;
		BufferedReader jugandoIn = ( numJugAct == 1 ) ? in1 : in2;
		BufferedReader enEsperaIn = ( numJugAct == 1 ) ? in2 : in1;
		String jugada = jugandoIn.readLine();
		if(jugada.startsWith("PISTA"))
		{
			String mensaje = pedirPista(numJugAct);
			jugandoOut.println(mensaje);
			if(!mensaje.equals("PISTA:ERROR"))
			{
				enEsperaOut.println("PASO:");
				return true;
			}
			else
			{
				throw new Exception("Jugador ya usó todas sus pistas.");
			}
		}
		else if(jugada.startsWith("JUGADA:"))
		{
			enEsperaOut.println(jugada);
			String respuesta = enEsperaIn.readLine();
			if(respuesta.equals("FIN_JUEGO"))
			{
				jugandoOut.println("FIN_JUEGO");
				terminarJuego(numJugAct);
				return false;
			}
			else if(respuesta != null && respuesta.startsWith("RESULTADO:"))
			{
				jugandoOut.println(respuesta);
				return false;
			}
			else
			{
				throw new Exception("Formato inválido");
			}
		}
		else
		{
			throw new Exception("Formato inválido: " + jugada);
		}
	}

	/**
	 * Pide una pista.
	 * @param numJugAct jugador que pide la pista.
	 * @return Mensaje con la posición y la pista o error.
	 */
	private String pedirPista(int numJugAct)
	{
		Jugador contraincante = (numJugAct == 1)? jugador2: jugador1;
		String pista = contraincante.generarPista();
		if(pista == null)
		{
			return "PISTA:ERROR";
		}
		else
		{
			String mensaje = "PISTA:" + pista;
			return mensaje;
		}
	}

	/**
	 * Termina el juego.
	 * @param numJugGanador número del jugador ganador.
	 */
	private void terminarJuego(int numJugGanador) throws Exception
	{
		juegoTerminado = true;
		String mensaje = "";
		if(numJugGanador == 1)
		{
			mensaje = "GANADOR:" +  jugador1.darAlias();
			admin.registrarVictoria(jugador1.darAlias());
			admin.registrarDerrota(jugador2.darAlias());
		}
		else if(numJugGanador == 2)
		{
			mensaje = "GANADOR:" +  jugador2.darAlias();
			admin.registrarVictoria(jugador2.darAlias());
			admin.registrarDerrota(jugador1.darAlias());
		}
		out1.println(mensaje);
		out2.println(mensaje);
	}


	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase. <br>
	 * <b>inv:</b><br>
	 * !juegoTerminado => jugador1 != null <br>
	 * !juegoTerminado => out1 != null <br>
	 * !juegoTerminado => in1 != null <br>
	 * !juegoTerminado => jugador2 != null <br>
	 * !juegoTerminado => out2 != null <br>
	 * !juegoTerminado => in2 != null <br>
	 * jugador1 != null <br>
	 * jugador2 != null <br>
	 * admin != null <br>
	 */
	private void verificarInvariante()
	{
		if(!juegoTerminado)
		{
			assert socket1 != null: "Canal inválido";
			assert out1 != null : "Flujo inválido";
			assert in1 != null : "Flujo inválido";
			assert socket2 != null : "Canal inválido";
			assert out2 != null : "Flujo inválido";
			assert in2 != null : "Flujo inválido";
		}
		assert jugador1 != null : "Jugador nulo";
		assert jugador2 != null : "Jugador nulo";
	}

}
