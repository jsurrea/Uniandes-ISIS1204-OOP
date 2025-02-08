package uniandes.cupi2.cupiCode.servidor.mundo;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * Clase que administra la base de datos y se encarga de registrar en esta los resultados de los encuentros. <br>
 * @author JuanUrrea
 * <b>inv:</b><br>
 * conexion != null<br>
 * propiedades != null <br>
 */
public class AdministradorResultados 
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Conexión con la base de datos.
	 */
	private Connection conexion;

	/**
	 * Propiedades con la configuración de la aplicación.
	 */
	private Properties propiedades;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye la clase para que se conecte a la base de datos cuyo archivo de propiedades llega por parámetro.<br>
	 * @param pPropiedades Archivo de propiedades que contiene la configuración de la base de datos.<br>
	 * El archivo debe contener: "admin.db.path", "admin.db.driver", "admin.db.url" y "admin.db.shutdown".<br>
	 */
	public AdministradorResultados(Properties pPropiedades)
	{
		propiedades = pPropiedades;
		File data = new File(propiedades.getProperty("admin.db.path"));
		System.setProperty("derby.system.home", data.getAbsolutePath());
		verificarInvariante();
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Conecta la clase a la base de datos.<br>
	 * @throws SQLException Excepción asociada al manejo de la base de datos.<br>
	 * @throws Exception Excepciones asociadas al driver.<br>
	 */
	@SuppressWarnings("deprecation")
	public void conectarBD() throws SQLException, Exception
	{
		String driver = propiedades.getProperty("admin.db.driver");
		Class.forName(driver).newInstance();
		String url = propiedades.getProperty("admin.db.url");
		conexion = DriverManager.getConnection(url);
		verificarInvariante();
	}

	/**
	 * Desconecta la clase de la base de datos.<br>
	 * @throws SQLException Excepción asociada al método close() de conexion.
	 */
	public void desconectarBD() throws SQLException
	{
		conexion.close();
		String down = propiedades.getProperty("admin.db.shutdown");
		try
		{
			DriverManager.getConnection(down);
		}
		catch(SQLException e) { }
		verificarInvariante();
	}

	/**
	 * Si no existe la tabla resultados, la crea y la agrega a la base de datos.
	 * Post: La tabla quedó creada con alias, nombre, ganadas, perdidas y llave primaria alias.
	 * @throws SQLException Si hay problemas con la base de datos.
	 */
	public void crearTabla() throws SQLException
	{
		Statement s = conexion.createStatement();
		try
		{
			s.executeQuery("SELECT * FROM resultados WHERE 1=2");
		}
		catch (SQLException e)
		{
			s.execute("CREATE TABLE resultados (alias varchar(32), nombre varchar(32), ganadas int, perdidas int, PRIMARY KEY (alias))");
		}
		s.close();
		verificarInvariante();
	}

	/**
	 * Registra un usuario nuevo a la Base de Datos.
	 * Pre: El usuario no existe anteriormente.
	 * @param alias del usuario.
	 * @param nombre del usuario
	 * @return Clase Jugador con instancia creada usando los parámetros.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	public Jugador registrarUsuario(String alias, String nombre) throws SQLException
	{
		Jugador nuevo = new Jugador(alias,nombre,0,0);
		String sql = "INSERT INTO resultados (alias, nombre, ganadas, perdidas) VALUES ('" + alias + "', '" + nombre + "', 0, 0)";
		Statement st = conexion.createStatement();
		st.execute(sql);
		st.close();
		verificarInvariante();
		return nuevo;
	}

	/**
	 * Consulta la información del usuario cuyo alias llega por parámetro.
	 * @param alias del jugador.
	 * @return Jugador con los datos guardados. SI no existe, null.
	 * @throws SQLException Si ocurre un error con la base de datos.
	 */
	public Jugador consultarUsuario(String alias) throws SQLException
	{
		Jugador nuevo = null;
		String sql = "SELECT nombre, ganadas, perdidas FROM resultados WHERE alias ='" + alias + "'"; 
		Statement st = conexion.createStatement();
		ResultSet resultado = st.executeQuery(sql);
		if(resultado.next())
		{
			String nombre = resultado.getString(1);
			int ganadas = resultado.getInt(2);
			int perdidas = resultado.getInt(3);
			nuevo = new Jugador(nombre, alias, ganadas, perdidas);
			resultado.close();
		}
		st.close();
		verificarInvariante();
		return nuevo;
	}

	/**
	 * Devuelve todos los jugadores en la base de datos en una colección.
	 * @return Colección con los Jugadores guardados en la BD.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection consultarTodos() throws SQLException
	{
		Collection jugadores = new Vector();
		String sql = "SELECT * FROM resultados";
		Statement st = conexion.createStatement();
		ResultSet resultado = st.executeQuery(sql);
		while(resultado.next())
		{
			String alias = resultado.getString(1);
			String nombre = resultado.getString(2);
			int ganadas = resultado.getInt(3);
			int perdidas = resultado.getInt(4);
			Jugador jugador = new Jugador(nombre, alias, ganadas, perdidas);
			jugadores.add(jugador);

		}
		resultado.close();
		st.close();
		verificarInvariante();
		return jugadores;
	}

	/**
	 * Elimina el usuario cuyo alias llega por parámetro.
	 * Pre: El usuario existe en la base de datos.
	 * @param alias del jugador.
	 * @throws SQLException Si ocurre un error con la base de datos.
	 */
	public void eliminarUsuario(String alias) throws SQLException
	{
		String sql = "DELETE FROM resultados WHERE alias = '" + alias + "'";
		Statement st = conexion.createStatement();
		st.execute(sql);
		st.close();
		verificarInvariante();
	}

	/**
	 * Suma uno al valor de ganadas en el registro con el alias dado.
	 * Pre: El usuario existe.
	 * @param alias del jugador a sumarle victorias.
	 * @throws SQLException Si ocurre un error con la base de datos.
	 */
	public void registrarVictoria(String alias) throws SQLException
	{
		String sql = "UPDATE resultados SET ganadas = ganadas+1 WHERE alias = '" + alias + "'";
		Statement st = conexion.createStatement();
		st.executeUpdate(sql);
		st.close();
		verificarInvariante();
	}

	/**
	 * Suma uno al valor de perdidas en el registro con el alias dado.
	 * Pre: El usuario existe.
	 * @param alias del jugador a sumarle derrotas.
	 * @throws SQLException Si ocurre un error con la base de datos.
	 */
	public void registrarDerrota(String alias) throws SQLException
	{
		String sql = "UPDATE resultados SET perdidas = perdidas+1 WHERE alias = '" + alias + "'";
		Statement st = conexion.createStatement();
		st.executeUpdate(sql);
		st.close();
		verificarInvariante();
	}


	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase.<br>
	 * <b>inv:</b><br>
	 * conexion != null<br>
	 * propiedades != null <br>
	 */
	private void verificarInvariante()
	{
		assert conexion != null : "No se creó una conexión válida.";
		assert propiedades != null : "No se inicializaron correctamente las propiedades.";
	}

}
