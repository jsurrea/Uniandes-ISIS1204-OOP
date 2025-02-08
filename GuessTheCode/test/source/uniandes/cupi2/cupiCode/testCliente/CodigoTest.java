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
package uniandes.cupi2.cupiCode.testCliente;

import junit.framework.TestCase;
import uniandes.cupi2.cupiCode.cliente.mundo.Codigo;

/**
 * Clase que verifica la implementación de los métodos de la clase Codigo.
 */
public class CodigoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se realizan las pruebas.
     */
    private Codigo codigo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo código.
     */
    private void setupEscenario1( )
    {
        String[] colores = { Codigo.ROJO, Codigo.ROJO, Codigo.AMARILLO, Codigo.AZUL, Codigo.AMARILLO, Codigo.AMARILLO};
        codigo = new Codigo( colores );
    }

    /**
     * Prueba 1: Verifica el constructor. <br>
     * <b> Métodos a probar: </b> <br>
     * Codigo (constructor). <br>
     * darColores<br>
     * darCantColores<br>
     * darCantPosiciones<br>
     * <b> Caso de prueba 1: </b> <br>
     * 1. El código fue inicializado correctamente.
     */
    public void testCodigo1( )
    {
        codigo = new Codigo( );
        assertNotNull( "El arreglo de colores no puede ser nulo.", codigo.darColores( ) );

        String[] colores = codigo.darColores( );
        for( int i = 0; i < Codigo.CANT_COLORES; i++ )
        {
            assertEquals( "Los colores deben inicializarse en SIN_COLOR en el constructor sin parámetros.", Codigo.SIN_COLOR, colores[ i ] );
        }
        assertEquals( "La cantidad de colores está mal inicializada.", -1, codigo.darCantidadColoresCorrectos( ) );
        assertEquals( "La cantidad de posiciones está mal inicializada.", -1, codigo.darCantidadPosicionesCorrectas( ) );
    }

    /**
     * Prueba 2: Verifica el constructor con parámetro. <br>
     * <b> Métodos a probar: </b> <br>
     * Codigo (constructor). <br>
     * darColores<br>
     * <b> Caso de prueba 1: </b> <br>
     * 1. El código fue inicializado correctamente.
     */
    public void testCodigo2( )
    {
        String[] lista = { Codigo.AMARILLO, Codigo.AZUL, Codigo.ROJO, Codigo.VERDE, Codigo.AMARILLO, Codigo.AMARILLO };
        codigo = new Codigo( lista );

        assertNotNull( "El arreglo de colores no puede ser nulo.", codigo.darColores( ) );

        String[] colores = codigo.darColores( );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.AMARILLO, colores[ 0 ] );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.AZUL, colores[ 1 ] );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.ROJO, colores[ 2 ] );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.VERDE, colores[ 3 ] );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.AMARILLO, colores[ 4 ] );
        assertEquals( "Los colores deben inicializarse con el valor dado por parámetro.", Codigo.AMARILLO, colores[ 5 ] );
    }

    /**
     * Prueba 3: Verifica el método calcularCantidadPosicionesIguales. <br>
     * <b> Métodos a probar: </b> <br>
     * calcularCantidadPosicionesIguales. <br>
     * <b> Caso de prueba 1: </b> <br>
     * 1. La cantidad de posiciones iguales es el esperado.
     */
    public void testCalcularCantidadPosicionesIguales( )
    {
        setupEscenario1( );
        String[] unCodigo = { Codigo.AMARILLO, Codigo.VERDE, Codigo.ROJO, Codigo.AZUL, Codigo.ROJO, Codigo.ROJO };
        Codigo temp = new Codigo( unCodigo );
        assertEquals( "La cantidad de posiciones iguales es incorrecta", 1, codigo.calcularCantidadPosicionesCorrectas( temp ) );
    }

    /**
     * Prueba 4: Verifica el método calcularCantidadColoresIguales. <br>
     * <b> Métodos a probar: </b> <br>
     * calcularCantidadColoresIguales. <br>
     * <b> Caso de prueba 1: </b> <br>
     * 1. La cantidad de colores iguales es el esperado.
     */
    public void testCalcularCantidadColoresIguales( )
    {
        setupEscenario1( );
        String[] unCodigo = { Codigo.AMARILLO, Codigo.VERDE, Codigo.ROJO, Codigo.AZUL, Codigo.AZUL, Codigo.AZUL };
        Codigo temp = new Codigo( unCodigo );
        assertEquals( "La cantidad de colores iguales es incorrecta", 3, codigo.calcularCantidadColoresCorrectos( temp ) );
    }
}
