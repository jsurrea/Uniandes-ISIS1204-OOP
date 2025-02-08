/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiCode
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiCode.cliente.mundo;

/**
 * Representa un código de colores de un jugador. <br>
 * <b>inv:</b> <br>
 * colores != null <br>
 * color[i] != null <br>
 */
public class Codigo
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Color vacío.
     */
    public static final String SIN_COLOR = "SIN_COLOR";

    /**
     * Color amarillo.
     */
    public static final String AMARILLO = "Y";

    /**
     * Color azul.
     */
    public static final String AZUL = "B";

    /**
     * Color rojo.
     */
    public static final String ROJO = "R";

    /**
     * Color verde.
     */
    public static final String VERDE = "G";

    /**
     * Cantidad de colores en un código.
     */
    public static final int CANT_COLORES = 6;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Arreglo de colores.
     */
    private String[] colores;

    /**
     * Cantidad de colores correctos con respecto al código del oponente.
     */
    private int cantidadColoresCorrectos;

    /**
     * Cantidad de posiciones correctas con respecto al código del oponente.
     */
    private int cantidadPosicionesCorrectas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo código con los colores inicializados en SIN_COLOR.
     * <b>post: </b> El código quedó inicializado. cantidadColoresCorrectos y cantidadPosicionesCorrectas inicializados en -1<br>
     */
    public Codigo( )
    {
        colores = new String[CANT_COLORES];
        for( int i = 0; i < CANT_COLORES; i++ )
        {
            colores[ i ] = SIN_COLOR;
        }
        cantidadColoresCorrectos = -1;
        cantidadPosicionesCorrectas = -1;
        verificarInvariante( );
    }

    /**
     * Construye un nuevo código con los colores dados.
     * <b>post: </b> El código quedó inicializado.<br>
     * @param pColores Vector de colores. pColores != null.
     */
    public Codigo( String[] pColores )
    {
        cantidadColoresCorrectos = -1;
        cantidadPosicionesCorrectas = -1;
        colores = pColores;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el vector de colores.
     * @return Vector de colores.
     */
    public String[] darColores( )
    {
        return colores;
    }

    /**
     * Retorna la cantidad de colores correctos de este código.
     * @return Cantidad de colores correctos.
     */
    public int darCantidadColoresCorrectos( )
    {
        return cantidadColoresCorrectos;
    }

    /**
     * Retorna la cantidad de posiciones con colores correctos de este código.
     * @return Cantidad de posiciones correctas.
     */
    public int darCantidadPosicionesCorrectas( )
    {
        return cantidadPosicionesCorrectas;
    }

    /**
     * Cambia la cantidad de colores correctos de este código.
     * <b>post: </b> Cambió la cantidad de colores correctos por el valor dado por parámetro.<br>
     * @param pCantidadColores Nueva cantidad de colores correctos. pCantidadColores >=0.
     */
    public void cambiarCantidadColoresCorrectos( int pCantidadColores )
    {
        cantidadColoresCorrectos = pCantidadColores;
    }

    /**
     * Cambia la cantidad de posiciones correctas de este código.
     * <b>post: </b> Cambió la cantidad de posiciones correctas por el valor dado por parámetro.<br>
     * @param pCantidadPosiciones Nueva cantidad de posiciones correctas. pCantidadPosiciones >=0.
     */
    public void cambiarCantidadPosicionesCorrectas( int pCantidadPosiciones )
    {
        cantidadPosicionesCorrectas = pCantidadPosiciones;
    }

    /**
     * Cambia el arreglo de colores de este código por el arreglo dado.
     * <b>post: </b> Cambió el arreglo de colores por el valor dado por parámetro.<br>
     * @param pColores Nuevo arreglo de colores. pColores!=null.
     */
    public void cambiarColores( String[] pColores )
    {
        colores = pColores;
    }

    /**
     * Calcula la cantidad de colores que se encuentran en posiciones correctas entre el código dado y este. <br>
     * <b> pre: </b> El arreglo de colores está inicializado <br>
     * <b> post: </b> Se calculó la cantidad de posiciones correctas y se asignó al correspondiente atributo.<br>
     * @param pCodigo Código a comparar. pCodigo != null.
     * @return Cantidad de colores que se encuentran en posiciones correctas.
     */
    public int calcularCantidadPosicionesCorrectas( Codigo pCodigo )
    {
        int cant = 0;
        String[] vector = pCodigo.colores;
        for( int i = 0; i < CANT_COLORES; i++ )
        {
            if( vector[ i ].equals( colores[ i ] ) )
            {
                cant++;
            }
        }
        cantidadPosicionesCorrectas = cant;
        verificarInvariante( );
        return cant;
    }

    /**
     * Calcula la cantidad de colores que el código tiene igual al código dado. <br>
     * <b> pre: </b> El arreglo de colores está inicializado. <br>
     * <b> post: </b> Se calculó la cantidad de colores correctos y se asignó al correspondiente atributo.<br>
     * @param pCodigo Código a comparar. pCodigo != null.
     * @return Cantidad de colores correctos.
     */
    public int calcularCantidadColoresCorrectos( Codigo pCodigo )
    {
        int cant = 0;
        Codigo temp = clonar( pCodigo );
        String[] coloresTemp = temp.darColores( );

        String[] coloresTemp2 = colores.clone( );

        for( int i = 0; i < CANT_COLORES; i++ )
        {
            for( int j = 0; j < CANT_COLORES; j++ )
            {
                if( coloresTemp2[ i ] != null && coloresTemp[ j ] != null && coloresTemp2[ i ].equals( coloresTemp[ j ] ) )
                {
                    cant++;
                    coloresTemp2[ i ] = null;
                    coloresTemp[ j ] = null;
                }
            }
        }
        cantidadColoresCorrectos = cant;
        verificarInvariante( );
        return cant;
    }

    

    // -----------------------------------------------------------------
    // Métodos auxiliares
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo código con los colores del código dado.
     * <b>post: </b> Se creó un código idéntico al original.<br>
     * @param pCodigo Código que se desea clonar. pCodigo != null
     * @return Nuevo código con la información del ingresado por parámetro.
     */
    private Codigo clonar( Codigo pCodigo )
    {
        String[] temp = pCodigo.darColores( );
        String[] colores = new String[temp.length];
        for( int i = 0; i < temp.length; i++ )
        {
            colores[ i ] = new String( temp[ i ].toString( ) );
        }
        return new Codigo( colores );
    }

    // -----------------------------------------------------------------
    // Invariantes
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * colores != null <br>
     * color[i] != null <br>
     */
    private void verificarInvariante( )
    {
        assert ( colores != null ) : "El arreglo de colores no puede ser nulo.";
        assert ( !hayColoresNulos( ) ) : "Ninguna posición del vector de colores puede ser nula.";
    }

    /**
     * Determina si hay posiciones nulas en el vector de colores de este código.
     * @return True si hay al menos una posición nula o false de lo contrario.
     */
    private boolean hayColoresNulos( )
    {
        boolean hay = false;

        for( int i = 0; i < CANT_COLORES && !hay; i++ )
        {
            if( colores[ i ] == null )
            {
                hay = true;
            }
        }

        return hay;
    }
}
